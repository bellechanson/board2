package com.example.board2.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private Boolean bNotice;
    private LocalDateTime createdAt;
    private Long boardId;
}
