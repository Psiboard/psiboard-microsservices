package com.psiboard.patients_service.framework.adapters.in;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.psiboard.patients_service.application.dto.SchedulingRequestDto;
import com.psiboard.patients_service.application.dto.SchedulingResponseDto;
import com.psiboard.patients_service.application.ports.in.SchedulingServiceInputPort;

import java.util.List;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("scheduling")
public class SchedulingRestController {

    private final SchedulingServiceInputPort schedulingServiceInputPort;

    public SchedulingRestController(SchedulingServiceInputPort schedulingServiceInputPort) {
        this.schedulingServiceInputPort = schedulingServiceInputPort;
    }

    @GetMapping()
    public List<SchedulingResponseDto> findAll() {
        return schedulingServiceInputPort.findAll();
    }

    @GetMapping("/{userId}/date")
    public List<SchedulingResponseDto> findSchedules(@PathVariable String userId, @RequestParam LocalDate date) {
        return schedulingServiceInputPort.findSchedules(userId, date);
    }

    @GetMapping("/{userId}/available")
    public List<String> findAvailableHours(@PathVariable String userId, @RequestParam LocalDate date) {
        return schedulingServiceInputPort.findAvailableHours(date,userId);
    }

    @PostMapping()
    public ResponseEntity<SchedulingResponseDto> create(@RequestBody SchedulingRequestDto scheduling) {
        SchedulingResponseDto newScheduling = schedulingServiceInputPort.create(scheduling);
        return ResponseEntity.ok(newScheduling);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchedulingResponseDto> update(@PathVariable String id,
            @RequestBody SchedulingRequestDto updateScheduling) {
        return ResponseEntity.status(HttpStatus.CREATED).body(schedulingServiceInputPort.update(id, updateScheduling));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        schedulingServiceInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }

}
