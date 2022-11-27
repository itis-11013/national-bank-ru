package ru.itis.nationalbankru.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.nationalbankru.entity.Unit;

import java.util.Optional;

/**
 * @author : Escalopa
 * @created : 02.06.2022, Thu
 * @time : 20:17
 **/
public interface UnitRepository extends JpaRepository<Unit, Long> {
    Optional<Unit> findUnitByCode(Long code);
}
