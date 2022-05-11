package ru.itis.nationalbankru.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.itis.nationalbankru.dto.OrganizationResponseDto;

/**
 * 12.05.2021
 * 04. REST API
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Component
@Aspect
public class TimeAspect {
    @Around(value = "execution(org.springframework.http.ResponseEntity<*> ru.itis.nationalbankru.controllers.OrganizationController.*(..))")
    public ResponseEntity<OrganizationResponseDto> addTimeToResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        long before = System.currentTimeMillis();
        Object responseEntity = joinPoint.proceed();
        long after = System.currentTimeMillis();
        ResponseEntity response = (ResponseEntity) responseEntity;
        return ResponseEntity.ok(OrganizationResponseDto.builder()
                .time(after - before)
                .data(response.getBody())
                .build());
    }
}

