package com.kingname.insta.modules.post;

import com.kingname.insta.modules.up.Up;
import com.kingname.insta.modules.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long postId);

    List<Post> findAllByUser(User user);
}
