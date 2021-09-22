package com.kingname.insta.modules.user;

import com.kingname.insta.modules.utils.Generator;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@GraphQLApi
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final Generator generator;

    @GraphQLQuery(name = "getAllUser", description = "테스트용")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GraphQLMutation(name = "createAccount")
    public User createAccount(User user) {
        return userRepository.save(user);
    }

    @GraphQLMutation(name = "requestSecret")
    public Boolean requestSecret(String email) {
        User account = userRepository.findByEmail(email);
        if (account == null) {
            return false;
        }
        String loginSecret = generator.secretGenerator();
        account.setLoginSecret(loginSecret);
        User saveAccount = userRepository.save(account);
        log.info(saveAccount.getLoginSecret());
        return true;
    }
}
