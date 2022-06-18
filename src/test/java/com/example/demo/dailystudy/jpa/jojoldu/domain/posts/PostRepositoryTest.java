package com.example.demo.dailystudy.jpa.jojoldu.domain.posts;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @AfterAll
    public void cleanup() {
        postRepository.deleteAll();
    }

    @Test
    public void post_save_and_call() {

        String title = "test 게시글";
        String content = "테스트 본문 content";

        postRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("sunwook1218@naver.com")
                .build());

        List<Posts> postAll = postRepository.findAll();

        assertThat(postAll.stream().anyMatch(posts -> posts.getTitle().equals(title)));
        assertThat(postAll.stream().anyMatch(posts -> posts.getContent().equals(content)));

    }

}
