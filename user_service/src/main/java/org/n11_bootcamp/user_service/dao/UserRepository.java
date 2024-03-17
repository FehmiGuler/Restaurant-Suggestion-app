package org.n11_bootcamp.user_service.dao;

import org.n11_bootcamp.user_service.entity.User;
import org.n11_bootcamp.user_service.enums.EnumStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByName(String name);

    List<User> findAllByNameAndSurnameAndStatus(String name, String surname, EnumStatus status);


}
