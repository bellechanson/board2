package com.example.board2.service;

import com.example.board2.entity.Board;
import com.example.board2.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board create(Board board) {
        return boardRepository.save(board);
    }

    public Board update(Long id, Board updated) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board not found: " + id));
        board.setCategory(updated.getCategory());
        board.setSubCategory(updated.getSubCategory());
        return boardRepository.save(board);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);

    }
}
