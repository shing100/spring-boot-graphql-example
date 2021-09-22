package com.kingname.insta.comment;

import com.kingname.insta.post.Post;
import com.kingname.insta.user.User;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.Nonnull;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GraphQLQuery(name = "id")
    private Long id;

    @Nonnull
    @GraphQLQuery(name = "text")
    private String text;

    @Nonnull
    @GraphQLQuery(name = "user")
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;

    @Nonnull
    @GraphQLQuery(name = "post")
    @ManyToOne(targetEntity = Post.class, fetch = FetchType.LAZY)
    private Post post;

}
