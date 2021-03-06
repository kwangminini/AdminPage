package com.example.admin.repository;

import com.example.admin.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
    //select * from user where account = ?
//    Optional<User> findByAccount(String account);
//
//    Optional<User> findByEmail(String email);
//
//    Optional<User> findByAccountAndEmail(String account, String email);
}
