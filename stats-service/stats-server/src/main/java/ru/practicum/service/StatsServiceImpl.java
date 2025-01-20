package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.EndpointHitDto;
import ru.practicum.ViewStatsDto;
import ru.practicum.mapper.EndpointHitMapper;
import ru.practicum.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final EndpointHitMapper endpointHitMapper;
    private final StatsRepository statsRepository;

    @Transactional
    @Override
    public EndpointHitDto addStats(EndpointHitDto endpointHitDto) {
        log.info("Получен запрос для сохранения статистики: {}", endpointHitDto);
        return endpointHitMapper.toDto(statsRepository.save(endpointHitMapper.toHitEntity(endpointHitDto)));
    }

    @Transactional
    @Override
    public List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        log.info("Запрос статистики с периодом с {} по {}", start, end);

        if (unique != null && unique) {
            log.info("Запрос статистики с уникальными IP");
            if (uris == null || uris.isEmpty()) {
                log.info("Запрос статистики без фильтра по URI");
                return statsRepository.getHitsWithoutUrisWithUniqueIp(start, end);
            } else {
                log.info("Запрос статистики с фильтром по URI");
                return statsRepository.getHitsWithUrisWithUniqueIp(uris, start, end);
            }
        } else {
            log.info("Запрос статистики без учета уникальных IP");
            if (uris == null || uris.isEmpty()) {
                log.info("Запрос статистики без фильтра по URI");
                return statsRepository.getAllHitsWithoutUris(start, end);
            } else {
                log.info("Запрос статистики с фильтром по URI");
                return statsRepository.getAllHitsWithUris(uris, start, end);
            }
        }
    }
}
