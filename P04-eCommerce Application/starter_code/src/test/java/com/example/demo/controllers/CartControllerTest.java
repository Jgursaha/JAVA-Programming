package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartControllerTest {

    private CartController cartController;
    private UserRepository userRepository = mock(UserRepository.class);
    private CartRepository cartRepository = mock(CartRepository.class);
    private ItemRepository itemRepository = mock(ItemRepository.class);


    @Before
    public void setUp(){
        cartController = new CartController();
        TestUtils.injectObject(cartController, "userRepository", userRepository);
        TestUtils.injectObject(cartController, "cartRepository", cartRepository);
        TestUtils.injectObject(cartController, "itemRepository", itemRepository);

        Cart cart = new Cart();
        User user = new User();
        user.setId(0);
        user.setUsername("test");
        user.setPassword("password");
        user.setCart(cart);


        when(userRepository.findByUsername("test")).thenReturn(user);

        Item item = new Item();
        item.setId(0L);
        item.setName("Item1");
        item.setPrice(BigDecimal.valueOf(7.50));
        when(itemRepository.findById(0L)).thenReturn(java.util.Optional.of(item));
    }

    @Test
    public void verify_add_to_cart(){
        ModifyCartRequest cartRequest = new ModifyCartRequest();
        cartRequest.setItemId(0L);
        cartRequest.setUsername("test");
        cartRequest.setQuantity(2);

        ResponseEntity<Cart> response = cartController.addTocart(cartRequest);
        assertEquals(200, response.getStatusCodeValue());

        Cart cart = response.getBody();
        assertEquals(BigDecimal.valueOf(15.00), cart.getTotal());
    }

    @Test
    public void verify_add_to_cart_user_not_found(){
        ModifyCartRequest cartRequest = new ModifyCartRequest();
        cartRequest.setItemId(0L);
        cartRequest.setUsername("test2");
        cartRequest.setQuantity(2);

        ResponseEntity<Cart> response = cartController.addTocart(cartRequest);
        assertEquals(404, response.getStatusCodeValue());
    }


}
