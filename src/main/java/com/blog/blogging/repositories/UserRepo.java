package com.blog.blogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogging.entity.User;


public interface UserRepo extends JpaRepository<User, Long> {

}
