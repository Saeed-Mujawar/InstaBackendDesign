package com.geekster.Insta.repository;

import com.geekster.Insta.model.AuthenticationToken;
import com.geekster.Insta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenRepo extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findByUser(User user);

    AuthenticationToken findFirstByToken(String token);
}
