package com.kingname.insta.modules.post;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@GraphQLApi
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @GraphQLQuery(name = "posts")
    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    @GraphQLQuery(name = "post")
    public Optional<Post> getPostById(Long id){
        return postRepository.findById(id);
    }

//    @GraphQLMutation(name = "savePost")
//    public Post savePost(Post post) {
//        return postRepository.save(post);
//    }

//    @GraphQLMutation(name = "deletePost")
//    public void deletePost(Long id) {
//        postRepository.deleteById(id);
//    }

//    @GraphQLQuery(name = "isGood")
//    public boolean isGood(@GraphQLContext Post post) {
//        return !post.getComments().equals("title1");
//    }
}
