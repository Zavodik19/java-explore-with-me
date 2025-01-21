package ru.practicum.mapper;


import org.springframework.stereotype.Component;
import ru.practicum.EndpointHitDto;
import ru.practicum.model.EndpointHit;

@Component
public class EndpointHitMapper {
    // Метод для преобразования EndpointHitDto в сущность EndpointHit
    public static EndpointHit toHitEntity(EndpointHitDto hitDto) {
        return new EndpointHit(
                hitDto.getId(),
                hitDto.getApp(),
                hitDto.getUri(),
                hitDto.getIp(),
                hitDto.getTimestamp()
        );
    }

    // Метод для преобразования сущности EndpointHit в EndpointHitDto
    public static EndpointHitDto toDto(EndpointHit endpointHit) {
        return new EndpointHitDto(
                endpointHit.getId(),
                endpointHit.getApp(),
                endpointHit.getUri(),
                endpointHit.getIp(),
                endpointHit.getTimestamp()
        );
    }
}
