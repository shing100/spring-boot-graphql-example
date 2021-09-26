package com.kingname.insta.modules.up;

import com.kingname.insta.modules.post.Post;
import com.kingname.insta.modules.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpRepository  extends JpaRepository<Up, Long> {
    boolean existsByUserAndPost(User user, Post post);

    Up findByUserAndPost(User user, Post post);

    List<Up> findAllByUser(User user);

    List<Up> findAllByPost(User post);
}
