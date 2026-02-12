package com.example.memo.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemoResponseDto {

    private final Long id;
    private final String title;
    private final String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public MemoResponseDto(Long id, String title, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
