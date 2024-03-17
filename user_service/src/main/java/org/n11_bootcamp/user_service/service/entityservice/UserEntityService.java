package org.n11_bootcamp.user_service.service.entityservice;

import org.n11_bootcamp.user_service.dao.UserRepository;
import org.n11_bootcamp.user_service.entity.User;
import org.n11_bootcamp.user_service.enums.EnumStatus;
import org.n11_bootcamp.user_service.general.BaseEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityService extends BaseEntityService<User, UserRepository> {

    protected UserEntityService(UserRepository repository) {
        super(repository);
    }

    public User findUserByName(String name) {
        return getRepository().findUserByName(name);
    }

    public List<User> findByNameAndSurnameAndStatus(String name, String surname, EnumStatus status) {
        return getRepository().findAllByNameAndSurnameAndStatus(name, surname, status);
    }
}
