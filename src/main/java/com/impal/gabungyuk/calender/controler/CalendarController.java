package com.impal.gabungyuk.calender.controler;

import com.impal.gabungyuk.calender.model.request.MarkAsDoneRequest;
import com.impal.gabungyuk.calender.model.response.CalendarResponse;
import com.impal.gabungyuk.calender.service.CalendarService;
import com.impal.gabungyuk.core.model.SuccessResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    // GET semua deadline
    @GetMapping(
            value = "/api/v1/calendar",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SuccessResponse<List<CalendarResponse>> getMyCalendar(
            @RequestHeader("Authorization") String authorizationHeader) {
        return SuccessResponse.<List<CalendarResponse>>builder()
                .status(200)
                .message("Calendar retrieved successfully")
                .data(calendarService.getMyCalendar(authorizationHeader))
                .build();
    }

    // GET filter by bulan & tahun
    @GetMapping(
            value = "/api/v1/calendar/filter",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SuccessResponse<List<CalendarResponse>> getMyCalendarByMonthAndYear(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestParam Integer month,
            @RequestParam Integer year) {
        return SuccessResponse.<List<CalendarResponse>>builder()
                .status(200)
                .message("Calendar retrieved successfully")
                .data(calendarService.getMyCalendarByMonthAndYear(authorizationHeader, month, year))
                .build();
    }

    // PATCH mark as done
    @PatchMapping(
            value = "/api/v1/calendar/done",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SuccessResponse<Void> markAsDone(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody MarkAsDoneRequest request) {
        calendarService.markAsDone(authorizationHeader, request.getProjectId());
        return SuccessResponse.<Void>builder()
                .status(200)
                .message("Event marked as done successfully")
                .data(null)
                .build();
    }
}