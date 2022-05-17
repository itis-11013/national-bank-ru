package ru.itis.nationalbankru.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.itis.nationalbankru.dto.user.UserResponseDto;

@Component
@Aspect
public class TimeAspect {
    @Around(value = "execution(org.springframework.http.ResponseEntity<*> ru.itis.nationalbankru.controllers.UserController.*(..))")
    public ResponseEntity<UserResponseDto> addTimeToResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        long before = System.currentTimeMillis();
        Object responseEntity = joinPoint.proceed();
        long after = System.currentTimeMillis();
        ResponseEntity response = (ResponseEntity) responseEntity;
        return ResponseEntity.ok(UserResponseDto.builder()
                .time(after - before)
                .data(response.getBody())
                .build());
    }
}
