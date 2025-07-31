package com.mwahdin.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotNull(message = "{USERNAME.IS.NULL}")
    @NotBlank(message = "{USERNAME.IS.BLANK}")
    private String userName;

    @NotNull(message = "{PASSWORD.IS.NULL}")
    @NotBlank(message = "{PASSWORD.IS.BLANK}")
    private String passWord;

}
