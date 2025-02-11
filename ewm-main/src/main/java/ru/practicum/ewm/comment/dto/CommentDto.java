package ru.practicum.ewm.comment.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private String text;
    private Long eventId;
    private Long authorId;
    private String authorName;
    private LocalDateTime createdOn;
}
