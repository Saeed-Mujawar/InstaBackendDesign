package com.geekster.Insta.repository;

import com.geekster.Insta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    User findFirstByEmail(String userEmail);
}
