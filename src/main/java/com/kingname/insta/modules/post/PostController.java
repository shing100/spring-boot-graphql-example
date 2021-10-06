package com.kingname.insta.modules.post;

import com.kingname.insta.infra.jwt.JwtTokenRequired;
import com.kingname.insta.modules.up.Up;
import com.kingname.insta.modules.up.UpRepository;
import com.kingname.insta.modules.user.User;
import com.kingname.insta.modules.user.UserRepository;
import com.kingname.insta.modules.utils.SecurityUtil;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@GraphQLApi
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UpRepository upRepository;

    @JwtTokenRequired
    @GraphQLMutation(name = "createPost")
    public Post createPost(Post post) {
        String email = SecurityUtil.getCurrentUserEmail();
        User user = userRepository.findByEmail(email);
        post.setUser(user);
        return postRepository.save(post);
    }

    @GraphQLQuery(name = "posts")
    public List<Post> getPosts(){
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @GraphQLQuery(name = "post")
    public Optional<Post> getPostById(Long id){
        return postRepository.findById(id);
    }

    @JwtTokenRequired
    @GraphQLMutation(name = "toggleLike")
    public Boolean toggleLike(Long postId) {
        String userEmail = SecurityUtil.getCurrentUserEmail();
        User user = userRepository.findByEmail(userEmail);
        System.out.println(user);

        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 포스트 ID 입니다.");
        }

        Up like = Up.builder().post(post.get()).user(user).build();
        try {
            if (upRepository.existsByUserAndPost(user, post.get())) {
                Up up = upRepository.findByUserAndPost(user, post.get());
                upRepository.deleteById(up.getId());
            } else {
                upRepository.save(like);
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
