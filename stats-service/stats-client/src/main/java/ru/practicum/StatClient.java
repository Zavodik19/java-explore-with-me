package ru.practicum;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class StatClient extends BaseClient {

    @Autowired
    public StatClient(@Value("${stats-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .build());
    }

    public ResponseEntity<Object> addStats(EndpointHitDto endpointHitDto) {
        log.info("Отправка POST-запроса на /hit с данными: {}", endpointHitDto);
        return post("/hit", endpointHitDto);
    }

    public ResponseEntity<Object> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        Map<String, Object> parameters = createStatsParameters(start, end, uris, unique);
        String uri = buildUri(uris);

        log.info("Отправка GET-запроса на /stats с параметрами: {}", parameters);
        return get(uri, parameters);
    }

    private Map<String, Object> createStatsParameters(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        return Map.of(
                "start", start.toString(),
                "end", end.toString(),
                "uris", uris != null ? String.join(",", uris) : "",
                "unique", unique
        );
    }

    private String buildUri(List<String> uris) {
        return uris != null ?
                "/stats?start={start}&end={end}&uris={uris}&unique={unique}" :
                "/stats?start={start}&end={end}&unique={unique}";
    }
}