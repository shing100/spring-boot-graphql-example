package com.kingname.insta.modules.comment;

import com.kingname.insta.modules.post.Post;
import com.kingname.insta.modules.user.Account;
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
    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    private Account account;

    @Nonnull
    @GraphQLQuery(name = "post")
    @ManyToOne(targetEntity = Post.class, fetch = FetchType.LAZY)
    private Post post;

}
