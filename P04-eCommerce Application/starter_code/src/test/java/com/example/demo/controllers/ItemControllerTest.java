package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemControllerTest {
    private ItemController itemController;
    private ItemRepository itemRepository = mock(ItemRepository.class);

    @Before
    public void setUp(){
        itemController = new ItemController();
        TestUtils.injectObject(itemController, "itemRepository", itemRepository);


        Item item = new Item();
        item.setId(0L);
        item.setName("Item1");
        when(itemRepository.findById(0L)).thenReturn(java.util.Optional.of(item));

        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        when(itemRepository.findByName("Item1")).thenReturn(itemList);

        Item item2 = new Item();
        item.setName("Item2");
        List<Item> itemList2 = new ArrayList<>();
        itemList2.add(item);
        itemList2.add(item2);
        when(itemRepository.findAll()).thenReturn(itemList2);

    }

    @Test
    public void verify_find_item_by_id(){
        ResponseEntity<Item> response = itemController.getItemById(0L);
        assertEquals(200, response.getStatusCodeValue());

        Item item = response.getBody();
        assertNotNull(item);
    }

    @Test
    public void verify_find_item_by_id_notFound(){
        ResponseEntity<Item> response = itemController.getItemById(1L);
        assertEquals(404, response.getStatusCodeValue());

        Item item = response.getBody();
        assertNull(item);
    }

    @Test
    public void verify_find_items_by_name(){
        ResponseEntity<List<Item>> response = itemController.getItemsByName("Item1");
        assertEquals(200, response.getStatusCodeValue());

        List<Item> items = response.getBody();
        assertNotNull(items);
        assertEquals(1, items.size());
    }

    @Test
    public void verify_find_all_items(){
        ResponseEntity<List<Item>> response = itemController.getItems();
        assertEquals(200, response.getStatusCodeValue());

        List<Item> items = response.getBody();
        assertNotNull(items);
        assertEquals(2, items.size());
    }
}
