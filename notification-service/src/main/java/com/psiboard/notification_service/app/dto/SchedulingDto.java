package com.psiboard.notification_service.app.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SchedulingDto(
                @JsonProperty("date") LocalDate date,
                @JsonProperty("hour") String hour,
                @JsonProperty("type") SchedulingType type,
                @JsonProperty("user_id") String userId,
                @JsonProperty("patient_id") String patientId) implements Serializable {
}
