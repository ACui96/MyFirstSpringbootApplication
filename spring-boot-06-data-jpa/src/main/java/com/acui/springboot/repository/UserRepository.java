package com.acui.springboot.repository;

import com.acui.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//继承JpaRepository
public interface UserRepository extends JpaRepository<User,Integer> {

}
