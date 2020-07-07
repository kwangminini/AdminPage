package com.example.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String name;
    private String title;
    private String content;
    private Integer price;
    private String brandName;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private Long partnerId;






    //1:N
    //LAZY = 지연로딩, EAGER = 즉시로딩
    //LAZY = SELECT * FROM ITEM where id =?
    //EAGER = 연관관계가 설정된 모든 조인을 실행한다.  따라서 1:1에서만 EAGER 사용하자!
//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "item")
//    private List<OrderDetail> orderDetailList;
}
