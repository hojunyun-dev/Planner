package com.example.schedule.service;

import com.example.schedule.Dto.ScheduleRequestDto;
import com.example.schedule.Dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {


    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto findScheduleById(Long id);

    ScheduleResponseDto updateSchedule(Long id, String title, String contents, String author);

    ScheduleResponseDto updateTitle(Long id, String title, String contents);

    void deleteSchedule(Long id, String password);
}
