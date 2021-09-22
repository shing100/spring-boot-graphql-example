package com.kingname.insta.modules.user;

import com.kingname.insta.modules.utils.Generator;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@GraphQLApi
@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final Generator generator;
    private final PasswordEncoder passwordEncoder;

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
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }
        user.setLoginSecret(generator.secretGenerator());
        User saveUser = userRepository.save(user);
        return userService.sendSecretMail(saveUser.getEmail(), saveUser.getLoginSecret());
    }

    @GraphQLMutation(name = "confirmSecret")
    public User confirmSecret(String secret, String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("유효하지 않은 이메일입니다.");
        }
        
        if (!secret.equals(user.getLoginSecret())) {
            throw new IllegalArgumentException("시크릿코드가 다릅니다.");
        }
        // token 발급
        return null;
    }
}
