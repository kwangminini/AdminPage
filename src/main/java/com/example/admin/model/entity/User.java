package com.example.admin.model.entity;

import com.example.admin.model.enumclass.UserStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@ToString(exclude = {"orderGroupList"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
//@Table(name="user") 테이블 이름이 Class이름과 동일하다면 생략 가능
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String email;
    private String phoneNumber;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;


    //User 1:N OrderGroup
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<OrderGroup> orderGroupList;
    //1:N
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user") // mappedBy : OrderDetail에 있는 user변수와 매칭
//    private List<OrderDetail> orderDetailList;
}
