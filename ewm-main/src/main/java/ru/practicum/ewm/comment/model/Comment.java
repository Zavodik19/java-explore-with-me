package ru.practicum.ewm.comment.model;


import jakarta.persistence.*;
import lombok.*;
import ru.practicum.ewm.event.model.Event;
import ru.practicum.ewm.user.model.User;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @Column(nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @Column(nullable = false)
    private User author;

    @Column(nullable = false, name = "created_on")
    private LocalDateTime createdOn;
}
