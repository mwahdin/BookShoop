package com.mwahdin.library.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ShoppingCartRequest {
    private long userId;
    private long bookId;
    private int bookCount;
}
