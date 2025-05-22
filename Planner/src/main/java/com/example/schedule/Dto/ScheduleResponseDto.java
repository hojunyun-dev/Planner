package main.java.com.example.schedule.Dto;


import main.java.com.example.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    //속성
    private Long id;
    private String title;
    private String author;
    private String contents;
    private LocalDateTime updatedAt;

    // 생성자
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.author = schedule.getAuthor();
        this.contents = schedule.getContents();
        this.updatedAt = schedule.getUpdatedAt();
    }
}