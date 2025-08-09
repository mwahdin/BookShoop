package com.mwahdin.library.controller;

import com.mwahdin.library.dto.request.ShoppingCartRequest;
import com.mwahdin.library.dto.response.ShoppingCartResponse;
import com.mwahdin.library.service.shoppingcart.ShoppingCartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(com.mwahdin.library.service.shoppingcart.ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;

    }

    @PostMapping
    public ResponseEntity<ShoppingCartResponse> addBook(@RequestBody @Valid ShoppingCartRequest shoppingCartRequest){
        return ResponseEntity.ok(shoppingCartService.addShoppingCart(shoppingCartRequest));

    }
}
