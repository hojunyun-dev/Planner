package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    // 속성
    private Long id;               // 고유 식별자
    private String title;          // 할일
    private String author;         // 작성자명
    private String contents;       // 내용
    private String password;       // 비밀번호
    private LocalDateTime createdAt;  // 작성일
    private LocalDateTime updatedAt;  // 수정일

    // 생성자

    public Schedule(String title, String contents, String author, String password) {
        this.title = title;
        this.author = author;
        this.contents = contents;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;  // 최초 작성 시, 수정일은 작성일과 동일
    }

    // 기능
    private void updateUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    // 전체 일정 수정
    public void update(String contents, String author) {
        // contents, author, password 값 모두 수정
        this.contents = contents;
        this.author = author;

        updateUpdatedAt(); // 수정일 갱신
    }

    public void updateTitle(String title) {
        this.title = title;
    }



}



