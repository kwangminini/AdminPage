package com.example.admin.model.entity;

import com.example.admin.model.enumclass.ItemStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"orderDetailList","partner"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ItemStatus status; //등록 , 해지, 검수중
    private String name;
    private String title;
    private String content;
    private BigDecimal price;
    private String brandName;
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

    //Item N : 1 Partner
    @ManyToOne
    private Partner partner;

    //Item 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "item")
    private List<OrderDetail> orderDetailList;




    //1:N
    //LAZY = 지연로딩, EAGER = 즉시로딩
    //LAZY = SELECT * FROM ITEM where id =?
    //EAGER = 연관관계가 설정된 모든 조인을 실행한다.  따라서 1:1에서만 EAGER 사용하자!
//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "item")
//    private List<OrderDetail> orderDetailList;
}
