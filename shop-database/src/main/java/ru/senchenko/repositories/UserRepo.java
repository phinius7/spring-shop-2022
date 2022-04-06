package ru.senchenko.repositories;

import ru.senchenko.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    Optional<User> findUserByUserName(String userName);

    @Query("SELECT u FROM User u WHERE u.userName = :userName")
    User findByUserName(@Param("userName") String userName);
}
