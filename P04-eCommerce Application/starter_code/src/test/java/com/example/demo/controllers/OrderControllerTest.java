package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderControllerTest {

    private OrderController orderController;
    private UserRepository userRepository = mock(UserRepository.class);
    private OrderRepository orderRepository = mock(OrderRepository.class);


    @Before
    public void setUp(){
        orderController = new OrderController();
        TestUtils.injectObject(orderController, "userRepository", userRepository);
        TestUtils.injectObject(orderController, "orderRepository", orderRepository);

        User user = new User();
        user.setId(0);
        user.setUsername("test");
        user.setPassword("password");

        Item item = new Item();
        item.setId(0L);
        item.setName("Item1");
        item.setPrice(BigDecimal.valueOf(3.89));
        item.setDescription("description of item");

        List<Item> itemList = new ArrayList<Item>();
        itemList.add(item);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setItems(itemList);
        cart.setUser(user);
        cart.setTotal(BigDecimal.valueOf(3.89));

        user.setCart(cart);
        when(userRepository.findByUsername("test")).thenReturn(user);

    }

    @Test
    public void verify_submit_order(){
        ResponseEntity<UserOrder> response = orderController.submit("test");
        assertEquals(200, response.getStatusCodeValue());

        UserOrder order = response.getBody();
        assertEquals(1, order.getItems().size());
        assertEquals(BigDecimal.valueOf(3.89), order.getTotal());
    }

    @Test
    public void verify_submit_order_userNotFound(){
        ResponseEntity<UserOrder> response = orderController.submit("test1");
        assertEquals(404, response.getStatusCodeValue());
    }
}
