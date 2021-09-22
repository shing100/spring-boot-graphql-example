package com.kingname.insta.infra.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class JWTToken {

    private String grantType;
    private String token;
    private Long accessTokenExpiresIn;
}
