package com.kingname.insta.user;

import com.kingname.insta.utils.Utils;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@GraphQLApi
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final Utils utils;

    @GraphQLMutation(name = "getAllUser", description = "테스트용")
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
        String loginSecret = utils.secretGenerator();
        account.setLoginSecret(loginSecret);
        User saveAccount = userRepository.save(account);
        log.info(saveAccount.getLoginSecret());
        return true;
    }
}
