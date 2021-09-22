package com.kingname.insta.post;

import com.kingname.insta.post.Post;
import com.kingname.insta.post.PostRepository;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@GraphQLApi
@RequiredArgsConstructor
public class PostService {

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
