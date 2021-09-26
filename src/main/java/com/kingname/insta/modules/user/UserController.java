package com.kingname.insta.modules.user;

import com.kingname.insta.infra.jwt.JwtToken;
import com.kingname.insta.infra.jwt.JwtTokenProvider;
import com.kingname.insta.modules.post.Post;
import com.kingname.insta.modules.post.PostRepository;
import com.kingname.insta.modules.up.Up;
import com.kingname.insta.modules.up.UpRepository;
import com.kingname.insta.modules.utils.Generator;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@GraphQLApi
@RestController
@RequiredArgsConstructor
@Transactional
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final UpRepository upRepository;
    private final PostRepository postRepository;

    private final Generator generator;
    private final JwtTokenProvider tokenProvider;

    @GraphQLQuery(name = "getAllUser", description = "테스트용")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GraphQLMutation(name = "createAccount")
    public User createAccount(User user) throws Exception {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 이메일입니다.");
        }
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
        System.out.println(saveUser.toString());
        return userService.sendSecretMail(saveUser.getEmail(), saveUser.getLoginSecret());
    }

    @GraphQLMutation(name = "confirmSecret")
    public JwtToken confirmSecret(String secret, String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("유효하지 않은 이메일입니다.");
        }

        System.out.println(user.getLoginSecret());
        if (!secret.equals(user.getLoginSecret())) {
            throw new IllegalArgumentException("시크릿코드가 다릅니다.");
        }
        // token 발급
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, secret);
        return tokenProvider.generateTokenDto(authenticationToken);
    }

    @GraphQLQuery(name = "likes")
    public List<Up> likes(@GraphQLContext User user) {
        return upRepository.findAllByUser(user);
    }

    @GraphQLQuery(name = "posts")
    public List<Post> posts(@GraphQLContext User user) {
        return postRepository.findAllByUser(user);
    }
}
