package com.impal.gabungyuk.calender.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "calendar")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "id_pengguna")
    private Integer idPengguna;

    @Column(name = "is_done")
    private Boolean isDone;
}