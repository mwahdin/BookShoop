package com.mwahdin.library.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class BookResponse {

    private final String name;
    private final long price;
}
