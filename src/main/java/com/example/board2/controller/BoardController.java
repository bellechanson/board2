package com.example.board2.controller;

import com.example.board2.entity.Board;
import com.example.board2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Board> create(@RequestBody Board board) {
        return ResponseEntity.ok(boardService.create(board));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> update(@PathVariable Long id, @RequestBody Board board) {
        return ResponseEntity.ok(boardService.update(id, board));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
