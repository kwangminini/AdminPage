package com.example.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
//@Table(name="user") 테이블 이름이 Class이름과 동일하다면 생략 가능
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    //1:N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user") // mappedBy : OrderDetail에 있는 user변수와 매칭
    private List<OrderDetail> orderDetailList;
}
