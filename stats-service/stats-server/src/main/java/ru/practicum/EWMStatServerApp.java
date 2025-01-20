package ru.practicum;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class EWMStatServerApp {

    public static void main(String[] args) {
        SpringApplication.run(EWMStatServerApp.class, args);
        log.info("Приложение EWMStatServerApp запущено");
    }
}