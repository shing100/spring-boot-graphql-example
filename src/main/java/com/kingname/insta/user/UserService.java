package com.kingname.insta.user;

import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@GraphQLApi
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GraphQLMutation(name = "createAccount")
    public User createAccount(User user) {
        return userRepository.save(user);
    }

    public void requestSecret(String email) {

    }
}
