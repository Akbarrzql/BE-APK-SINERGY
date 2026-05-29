package com.impal.gabungyuk.calender.model.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({"eventId", "projectId", "title", "description", "deadline", "isDone"})
public class CalendarResponse {
    private Integer eventId;
    private Integer projectId;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private Boolean isDone;
}