package com.kingname.insta.infra.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class JwtToken {

    private String grantType;
    private String token;
    private String refreshToken;
    private Long accessTokenExpiresIn;
}
