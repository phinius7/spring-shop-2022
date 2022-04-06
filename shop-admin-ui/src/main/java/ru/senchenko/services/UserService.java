package ru.senchenko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.senchenko.dao.UserDao;
import ru.senchenko.entities.User;
import ru.senchenko.repositories.UserRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements CrudInterface<UserDao> {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void create(UserDao dao) {
        User user = new User();
        user.setId(dao.getId());
        user.setUserName(dao.getUserName());
        user.setPassword(passwordEncoder.encode(dao.getPassword()));
        user.setFirstName(dao.getFirstName());
        user.setLastName(dao.getLastName());
        user.setEmail(dao.getEmail());
        user.setRoles(dao.getRoles());
        userRepo.save(user);
    }

    @Override
    public List<UserDao> readAll() {
        return userRepo.findAll().stream().map(UserDao::new).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDao> readById(Integer id) {
        return userRepo.findById(id).map(UserDao::new);
    }

    @Override
    public void delete(Integer id) {
        userRepo.deleteById(id);
    }
}
