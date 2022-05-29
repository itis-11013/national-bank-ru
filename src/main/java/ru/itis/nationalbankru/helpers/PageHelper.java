package ru.itis.nationalbankru.helpers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.itis.nationalbankru.dto.PageableDto;

@Component
public class PageHelper {

    public Pageable toPageable(PageableDto pageableDto) {
        return PageRequest.of(
                pageableDto.getPageNo(),
                pageableDto.getPageSize(),
                Sort.by(pageableDto.getSort()));
    }
}
