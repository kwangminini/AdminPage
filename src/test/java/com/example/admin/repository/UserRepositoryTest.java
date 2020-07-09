package com.example.admin.repository;


import com.example.admin.AdminApplicationTests;
import com.example.admin.model.entity.Item;
import com.example.admin.model.entity.User;
import org.junit.Assert;
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
//        User user = new User();
//        user.setAccount("TestUser03");
//        user.setEmail("TestUser03@gmail.com");
//        user.setPhoneNumber("010-1111-3333");
//        user.setCreatedAt(LocalDateTime.now());
//        user.setCreatedBy("TestUser3");
//
//        User newUser = userRepository.save(user);
//        System.out.println("newUser::::"+newUser);
        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "TEST01@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);
        //User u = User.builder().account(account).password(password).build(); builder - create할 때 편

        User newUser = userRepository.save(user);
        Assert.assertNotNull(newUser);
    }
    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");


        //user.setEmail("").setPhoneNumber("").setStatus(""); chaining - update할 때 편함
        //User u = new User().setAccount("").setPassword(""); chaining
        user.getOrderGroupList().stream().forEach(orderGroup -> {
            System.out.println("--------------주문묶음--------------");
            System.out.println("수령인 : "+orderGroup.getRevName());
            System.out.println("수령지 : "+orderGroup.getRevAddress());
            System.out.println("총금액 : "+orderGroup.getTotalPrice());
            System.out.println("총수량 : "+orderGroup.getTotalQuantity());

            System.out.println("--------------주문상세--------------");
            orderGroup.getOrderDetailList().stream().forEach(orderDetail -> {
                System.out.println("파트너사 이름 : "+orderDetail.getItem().getPartner().getName());
                System.out.println("파트너사 카테고리 : "+orderDetail.getItem().getPartner().getCategory().getTitle());
                System.out.println("주문 상품 : "+orderDetail.getItem().getName());
                System.out.println("고객센터 번호 :"+orderDetail.getItem().getPartner().getCallCenter());
                System.out.println("주문의 상태 : "+orderDetail.getStatus());
                System.out.println("도착예정일자 : "+orderDetail.getArrivalDate());
            });
        });

        Assert.assertNotNull(user);
        //Optional<User> user = userRepository.findById(1L);
       /* Optional<User> user = userRepository.findByAccount("TestUser03");
        user.ifPresent(selectUser->{
            selectUser.getOrderDetailList().stream().forEach(detail->{
                Item item = detail.getItem();

                System.out.println(item);
            });
        });*/
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