package com.mwahdin.library.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class BookRequest {

    @NotNull(message = "{BOOK.NAME.IS.NULL}")
    @NotBlank(message = "{BOOK.NAME.IS.BLANK}")
    private final String name;

    @NotNull(message = "{BOOK.PRICE.IS.NULL}")
    @Min(value = 0, message = "{BOOK.PRICE.IS.MIN}")
    private final long price;

}
