package com.kingname.insta;

import com.kingname.insta.post.Post;
import com.kingname.insta.post.PostService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class InstaApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstaApplication.class, args);
    }
}
