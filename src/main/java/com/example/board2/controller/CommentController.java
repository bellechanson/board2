package com.example.board2.controller;

import com.example.board2.entity.Comment;
import com.example.board2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.board2.dto.CommentResponseDto;
import java.util.List;



@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<Comment> create(@PathVariable Long postId, @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.create(comment, postId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable Long id, @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.update(id, comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getByPostId(@RequestParam Long postId) {
        return ResponseEntity.ok(commentService.getByPostId(postId));
    }

}
