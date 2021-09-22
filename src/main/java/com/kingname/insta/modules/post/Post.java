package com.kingname.insta.modules.post;

import com.kingname.insta.modules.comment.Comment;
import com.kingname.insta.modules.file.File;
import com.kingname.insta.modules.up.Up;
import com.kingname.insta.modules.user.Account;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor @ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GraphQLQuery(name = "id")
    private Long id;

    @GraphQLQuery(name = "location")
    private String location;

    @Nonnull
    @GraphQLQuery(name = "caption")
    private String caption;

    @Nonnull
    @GraphQLQuery(name = "user")
    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    private Account account;

    @GraphQLQuery(name = "files")
    @ManyToMany(targetEntity = File.class, fetch = FetchType.LAZY)
    private List<File> files;

    @GraphQLQuery(name = "likes")
    @OneToMany(targetEntity = Up.class, fetch = FetchType.LAZY)
    private List<Up> ups;

    @GraphQLQuery(name = "comments")
    @ManyToMany(targetEntity = Comment.class, fetch = FetchType.LAZY)
    private List<Comment> comments;
}
