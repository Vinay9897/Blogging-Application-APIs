package com.blog.blogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.blogging.entity.Category;


public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
