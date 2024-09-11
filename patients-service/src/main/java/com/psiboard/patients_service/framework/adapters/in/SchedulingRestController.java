package com.psiboard.patients_service.framework.adapters.in;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psiboard.patients_service.application.dto.SchedulingRequestDto;
import com.psiboard.patients_service.application.dto.SchedulingResponseDto;
import com.psiboard.patients_service.application.ports.in.SchedulingServiceInputPort;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping()
    public ResponseEntity<SchedulingResponseDto> create(@RequestBody SchedulingRequestDto scheduling) {
        SchedulingResponseDto newScheduling = schedulingServiceInputPort.create(scheduling);
        return ResponseEntity.ok(newScheduling);
    }

}
