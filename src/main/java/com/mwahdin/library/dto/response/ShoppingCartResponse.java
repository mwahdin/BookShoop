package com.mwahdin.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ShoppingCartResponse {

    private final long shoppingCart;
    private final long factorId;

}

