package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.ViewStatsDto;
import ru.practicum.model.EndpointHit;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepository extends JpaRepository<EndpointHit, Long> {

    @Query("SELECT new ru.practicum.ViewStatsDto(h.app, h.uri, COUNT(*) ) " +
            "FROM EndpointHit h WHERE h.timestamp BETWEEN :start AND :end " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(*) DESC")
    List<ViewStatsDto> getAllHitsWithoutUris(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.ViewStatsDto(h.app, h.uri, COUNT(*) ) " +
            "FROM EndpointHit h WHERE h.timestamp BETWEEN :start AND :end " +
            "AND h.uri IN :uris GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(*) DESC")
    List<ViewStatsDto> getAllHitsWithUris(List<String> uris, LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.ViewStatsDto(h.app, h.uri, COUNT(DISTINCT h.ip)) " +
            "FROM EndpointHit h WHERE h.timestamp BETWEEN :start AND :end " +
            "GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(DISTINCT h.ip) DESC")
    List<ViewStatsDto> getHitsWithoutUrisWithUniqueIp(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.ViewStatsDto(h.app, h.uri, COUNT(DISTINCT h.ip)) " +
            "FROM EndpointHit h WHERE h.timestamp BETWEEN :start AND :end " +
            "AND h.uri IN :uris GROUP BY h.app, h.uri " +
            "ORDER BY COUNT(DISTINCT h.ip) DESC")
    List<ViewStatsDto> getHitsWithUrisWithUniqueIp(List<String> uris, LocalDateTime start, LocalDateTime end);
}