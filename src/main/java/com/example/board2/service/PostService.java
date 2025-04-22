package com.example.board2.service;

import com.example.board2.entity.Board;
import com.example.board2.entity.Post;
import com.example.board2.repository.BoardRepository;
import com.example.board2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.board2.dto.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    public Post create(Post post, Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        post.setBoard(board);
        return postRepository.save(post);
    }

    public Post update(Long id, Post updated) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        post.setTitle(updated.getTitle());
        post.setContent(updated.getContent());
        post.setBNotice(updated.getBNotice());

        return postRepository.save(post);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }
    public List<PostResponseDto> getAllByBoard(Long boardId) {
        return postRepository.findAll().stream()
                .filter(post -> post.getBoard().getBId().equals(boardId))
                .map(post -> PostResponseDto.builder()
                        .id(post.getPId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .authorId(post.getAuthorId())
                        .bNotice(post.getBNotice())
                        .createdAt(post.getCreatedAt())
                        .boardId(post.getBoard().getBId())
                        .build()
                ).collect(Collectors.toList());
    }

    public PostResponseDto getById(Long id) {
        return postRepository.findById(id)
                .map(post -> PostResponseDto.builder()
                        .id(post.getPId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .authorId(post.getAuthorId())
                        .bNotice(post.getBNotice())
                        .createdAt(post.getCreatedAt())
                        .boardId(post.getBoard().getBId())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }

    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> PostResponseDto.builder()
                        .id(post.getPId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .authorId(post.getAuthorId())
                        .bNotice(post.getBNotice())
                        .createdAt(post.getCreatedAt())
                        .boardId(post.getBoard().getBId())
                        .build())
                .collect(Collectors.toList());
    }

    public Page<PostResponseDto> getAllPaged(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(post -> PostResponseDto.builder()
                        .id(post.getPId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .authorId(post.getAuthorId())
                        .createdAt(post.getCreatedAt())
                        .boardId(post.getBoard().getBId())
                        .bNotice(post.getBNotice())
                        .build());
    }


}
