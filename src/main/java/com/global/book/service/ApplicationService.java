package com.global.book.service;

import com.global.book.entity.Application;
import com.global.book.repository.ApplicationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepo applicationRepository;

    public Application getDetailsByName(String appName) {
        return applicationRepository.findByName(appName);
    }
}
