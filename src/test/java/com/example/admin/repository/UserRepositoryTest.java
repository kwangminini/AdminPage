package com.example.admin.repository;


import com.example.admin.AdminApplicationTests;
import com.example.admin.model.entity.Item;
import com.example.admin.model.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


public class UserRepositoryTest extends AdminApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");

        User newUser = userRepository.save(user);
        System.out.println("newUser::::"+newUser);

    }
    @Test
    @Transactional
    public void read(){
        //Optional<User> user = userRepository.findById(1L);
        Optional<User> user = userRepository.findByAccount("TestUser03");
        user.ifPresent(selectUser->{
            selectUser.getOrderDetailList().stream().forEach(detail->{
                Item item = detail.getItem();

                System.out.println(item);
            });
        });
    }
    @Test
    @Transactional
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser->{
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }
    @Test
    @Transactional //db에서 데이터를 실행해도 다시 롤백시켜준다.
    public void delete(){
        Optional<User> user = userRepository.findById(3L);

        assertTrue(user.isPresent());
        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);

        assertFalse(deleteUser.isPresent());
    }
}