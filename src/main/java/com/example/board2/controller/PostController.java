package com.example.board2.controller;

import com.example.board2.entity.Post;
import com.example.board2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.board2.dto.PostResponseDto;

import java.util.List;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/{boardId}")
    public ResponseEntity<Post> create(@PathVariable Long boardId, @RequestBody Post post) {
        return ResponseEntity.ok(postService.create(post, boardId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody Post post) {
        return ResponseEntity.ok(postService.update(id, post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllByBoard(@RequestParam Long boardId) {
        return ResponseEntity.ok(postService.getAllByBoard(boardId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getById(id));
    }

}
