package main.java.com.example.schedule.service;

import com.example.schedule.Dto.CheckDto;
import com.example.schedule.Dto.ScheduleRequestDto;
import com.example.schedule.Dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import main.java.com.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {
        Schedule schedule = new Schedule(dto.getTitle(), dto.getContents(), dto.getAuthor(), dto.getPassword());

        return scheduleRepository.saveScedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        List<ScheduleResponseDto> allSchedules = scheduleRepository.findAllSchedules();

        return allSchedules;
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        //일정 조회
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        //아래 코드 사용 불가.
        // if (schedule == null) {
        // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, String title, String contents, String author) {
        // 일정 조회
        Optional<Schedule> schedule = scheduleRepository.findScheduleById(id);

        // 일정 수정
        int updatedRow = scheduleRepository.updateSchedule(id, title, contents, author);

        // 수정된 row가 0개라면
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }

        // 필수값 검증
        if (author == null || contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title is a required value.");
        }

        return new ScheduleResponseDto(scheduleRepository.findScheduleById(id).get());

    }

    @Transactional
    @Override
    public ScheduleResponseDto updateTitle(Long id, String title, String contents) {

        // 필수값 검증
        if (title == null || contents != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
        }

        // memo 제목 수정
        int updatedRow = scheduleRepository.updateTitle(id, title);
        // 수정된 row가 0개 라면
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }

        Optional<Schedule> schedule = scheduleRepository.findScheduleById(id);

        // 수정된 메모 조회
        return new ScheduleResponseDto(scheduleRepository.findScheduleById(id).get());
    }


    @Override
    public void deleteSchedule(Long id, String password) {
        // 일정 조회
        int deleteRow = scheduleRepository.deleteSchedule(id, password);

        // 삭제된 row가 0개 라면
        if (deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }
}
