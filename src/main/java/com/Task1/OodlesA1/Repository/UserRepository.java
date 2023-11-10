package com.Task1.OodlesA1.Repository;

import com.Task1.OodlesA1.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User>findByEmail(String email);
}
