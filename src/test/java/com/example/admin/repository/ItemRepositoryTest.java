package com.example.admin.repository;

import com.example.admin.AdminApplicationTests;
import com.example.admin.model.entity.Item;
import com.example.admin.model.enumclass.ItemStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class ItemRepositoryTest extends AdminApplicationTests{

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void create(){
        Item item = new Item();
        item.setStatus(ItemStatus.UNREGISTERED);
        item.setName("삼성 노트북");
        item.setTitle("삼성 노트북 A100");
        item.setContent("2019년형 노트북 입니다");
        //                                                                                                                                                                                                                                                        item.setPrice(900000);
        item.setBrandName("삼성");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01");
       // item.setPartnerId(1L);

        Item newItem = itemRepository.save(item);
        assertNotNull(newItem);

    }

    @Test
    public void read(){
        Long id = 1L;
        Optional<Item> item = itemRepository.findById(id);

        assertTrue(item.isPresent());

    }
}