package com.kingname.insta.modules.up;

import com.kingname.insta.infra.jwt.JwtTokenRequired;
import com.kingname.insta.modules.utils.SecurityUtil;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@GraphQLApi
@RequiredArgsConstructor
public class UpController {

    @JwtTokenRequired
    @GraphQLMutation(name = "toggleLike")
    public Boolean toggleLike(Long postId) {
        String memberEmail = SecurityUtil.getCurrentMemberEmail();
        System.out.println(memberEmail);
        return true;
    }
}
