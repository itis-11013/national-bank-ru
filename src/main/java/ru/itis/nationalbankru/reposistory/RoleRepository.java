package ru.itis.nationalbankru.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.nationalbankru.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
