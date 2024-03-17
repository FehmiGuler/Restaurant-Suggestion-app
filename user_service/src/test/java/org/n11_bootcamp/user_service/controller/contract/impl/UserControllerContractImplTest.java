package org.n11_bootcamp.user_service.controller.contract.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.n11_bootcamp.user_service.dto.UserDTO;
import org.n11_bootcamp.user_service.entity.User;
import org.n11_bootcamp.user_service.enums.EnumGender;
import org.n11_bootcamp.user_service.enums.EnumStatus;
import org.n11_bootcamp.user_service.request.UserReviewEditCommentRequest;
import org.n11_bootcamp.user_service.request.UserSaveRequest;
import org.n11_bootcamp.user_service.request.UserUpdateRequest;
import org.n11_bootcamp.user_service.service.entityservice.UserEntityService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserControllerContractImplTest {
    @Mock
    private UserEntityService userEntityService;

    @InjectMocks
    private UserControllerContractImpl userControllerContractImpl;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    void shouldGetAllUsers() {

        // given
        List<User> userList = Collections.emptyList();
        List<UserDTO> expectedUserDTOs = Collections.emptyList();

        // when
        Mockito.when(userEntityService.findAll()).thenReturn(userList);
        List<UserDTO> actualUserDTOs = userControllerContractImpl.getAllUsers();

        // then
        assertEquals(expectedUserDTOs, actualUserDTOs);
    }

    @Test
    void shouldGetUserById() {
        // given
        LocalDate date = LocalDate.now();
        User user = new User();
        user.setId(1L);
        user.setName("name");
        user.setSurname("surname");
        user.setEmail("email");
        user.setGender(EnumGender.MALE);
        user.setStatus(EnumStatus.PASSIVE);
        user.setLatitude(1.0);
        user.setLongitude(1.0);
        user.setBirthDate(date);
        user.setStatus(EnumStatus.PASSIVE);
        UserDTO expectedUserDTO = new UserDTO(1L, user.getName(), user.getSurname(), user.getBirthDate(), user.getEmail(), user.getLatitude(), user.getLongitude(), user.getGender(), user.getStatus());


        // when
        Mockito.when(userEntityService.findByIdWithControl(1L)).thenReturn(user);
        UserDTO actualUserDTO = userControllerContractImpl.getUserById(1L);

        // then
        assertEquals(expectedUserDTO, actualUserDTO);
    }

    @Test
    void shouldGetUserByName() {
        // given
        LocalDate date = LocalDate.now();
        User user = new User();
        user.setId(1L);
        user.setName("name");
        user.setSurname("surname");
        user.setEmail("email");
        user.setGender(EnumGender.MALE);
        user.setStatus(EnumStatus.PASSIVE);
        user.setLatitude(1.0);
        user.setLongitude(1.0);
        user.setBirthDate(date);
        user.setStatus(EnumStatus.PASSIVE);
        UserDTO expectedUserDTO = new UserDTO(1L, user.getName(), user.getSurname(), user.getBirthDate(), user.getEmail(), user.getLatitude(), user.getLongitude(), user.getGender(), user.getStatus());


        // when
        Mockito.when(userEntityService.findUserByName("name")).thenReturn(user);
        UserDTO actualUserDTO = userControllerContractImpl.getUserByName("name");

        // then
        assertEquals(expectedUserDTO, actualUserDTO);
    }

    @Test
    void shouldSaveUser() {
        // given
        String name = "name";

        UserSaveRequest request = Mockito.mock(UserSaveRequest.class);
        User user = Mockito.mock(User.class);

        // when
        Mockito.when(user.getName()).thenReturn(name);

        Mockito.when(request.name()).thenReturn(name);
        Mockito.when(userEntityService.save(Mockito.any())).thenReturn(user);

        UserDTO result = userControllerContractImpl.saveUser(request);

        Mockito.verify(userEntityService).save(Mockito.any());

        // then
        assertEquals(name, result.name());
    }

    @Test
    void shouldUpdateUser() {

        // given
        Long id = 1L;
        Double latitude = 1D;
        Double newLatitude = 2D;


        UserUpdateRequest request = Mockito.mock(UserUpdateRequest.class);
        Mockito.when(request.latitude()).thenReturn(newLatitude);
        Mockito.when(request.id()).thenReturn(id);

        LocalDate date = LocalDate.now();
        User user = new User();
        user.setId(1L);
        user.setName("name");
        user.setSurname("surname");
        user.setEmail("email");
        user.setGender(EnumGender.MALE);
        user.setStatus(EnumStatus.PASSIVE);
        user.setLatitude(latitude);
        user.setLongitude(1.0);
        user.setBirthDate(date);
        user.setStatus(EnumStatus.PASSIVE);

        //when
        Mockito.when(userEntityService.findByIdWithControl(Mockito.anyLong())).thenReturn(user);

        UserDTO result = userControllerContractImpl.updateUser(request);

        //then
        InOrder inOrder = Mockito.inOrder(userEntityService);
        inOrder.verify(userEntityService).findByIdWithControl(request.id());
        inOrder.verify(userEntityService).save(userCaptor.capture());
        inOrder.verifyNoMoreInteractions();

        User userCaptorValue = userCaptor.getValue();

        assertEquals(newLatitude, userCaptorValue.getLatitude());
        assertEquals(id, userCaptorValue.getId());
    }

    @Test
    void shouldActivateUser() {

        // given
        Long id = 1L;
        EnumStatus status = EnumStatus.PASSIVE;
        EnumStatus newStatus = EnumStatus.ACTIVE;

        User user = new User();
        user.setId(id);
        user.setStatus(status);

        //when
        Mockito.when(userEntityService.findByIdWithControl(Mockito.anyLong())).thenReturn(user);

        UserDTO result = userControllerContractImpl.activateUser(id);

        //then
        InOrder inOrder = Mockito.inOrder(userEntityService);
        inOrder.verify(userEntityService).findByIdWithControl(id);
        inOrder.verify(userEntityService).save(userCaptor.capture());
        inOrder.verifyNoMoreInteractions();

        User userCaptorValue = userCaptor.getValue();

        assertEquals(newStatus, userCaptorValue.getStatus());
        assertEquals(id, userCaptorValue.getId());
    }

//    @Test
//    void shouldRecommendRestaurantById() {
//    }
}