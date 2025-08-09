package com.mwahdin.library.service.shoppingcart;

import com.mwahdin.library.dto.request.ShoppingCartRequest;
import com.mwahdin.library.dto.response.ShoppingCartResponse;

public interface ShoppingCartService {
    ShoppingCartResponse addShoppingCart(ShoppingCartRequest shoppingCartRequest);
}
