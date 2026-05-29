package com.impal.gabungyuk.calender.model.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CalenderRequest {
    private Integer projectId;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private LocalDateTime reminderSettings;
}
