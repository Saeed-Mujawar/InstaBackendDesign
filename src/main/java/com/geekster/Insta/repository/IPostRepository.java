package com.geekster.Insta.repository;

import com.geekster.Insta.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepository extends JpaRepository<Post,Long> {

}
