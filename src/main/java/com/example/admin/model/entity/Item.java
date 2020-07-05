package com.example.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private String content;

    //1:N
    //LAZY = 지연로딩, EAGER = 즉시로딩
    //LAZY = SELECT * FROM ITEM where id =?
    //EAGER = 연관관계가 설정된 모든 조인을 실행한다.  따라서 1:1에서만 EAGER 사용하자!
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "item")
    private List<OrderDetail> orderDetailList;
}
