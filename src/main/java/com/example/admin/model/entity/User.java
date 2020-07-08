package com.example.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@ToString(exclude = {"orderGroupList"})
//@Table(name="user") 테이블 이름이 Class이름과 동일하다면 생략 가능
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String password;
    private String status;
    private String email;
    private String phoneNumber;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    //User 1:N OrderGroup
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<OrderGroup> orderGroupList;
    //1:N
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user") // mappedBy : OrderDetail에 있는 user변수와 매칭
//    private List<OrderDetail> orderDetailList;
}
