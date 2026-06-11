package com.cyberwatch.cyberwatch.service;

import com.cyberwatch.cyberwatch.model.Log;
import org.springframework.stereotype.Service;
import com.cyberwatch.cyberwatch.repository.LogRepository;

import java.util.List;

@Service
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public Log save(Log log) {
        return logRepository.save(log);
    }
    public List<Log> find() {
        return logRepository.findAll();
    }
}
