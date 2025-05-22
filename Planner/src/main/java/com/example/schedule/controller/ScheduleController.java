package main.java.com.example.schedule.controller;

import com.example.schedule.Dto.CheckDto;
import com.example.schedule.Dto.ScheduleRequestDto;
import com.example.schedule.Dto.ScheduleResponseDto;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {

        return new ResponseEntity<>(scheduleService.saveSchedule(dto),HttpStatus.CREATED);
    }

    // 전체 일정 조회
    @GetMapping
    public List<ScheduleResponseDto> findAllSchedules() {

        return scheduleService.findAllSchedules();
    }

    // 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {

      return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    // 일정 전체 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateScheduleById(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto dto
    ) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, dto.getTitle(), dto.getContents(), dto.getAuthor()), HttpStatus.OK);
    }


    // 일정 제목 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateTitle(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto dto
    ) {
        return new ResponseEntity<>(scheduleService.updateTitle(id, dto.getTitle(), dto.getContents()), HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestBody CheckDto checkDto) {
        scheduleService.deleteSchedule(id, checkDto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
