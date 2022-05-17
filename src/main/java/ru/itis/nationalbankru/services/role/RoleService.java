package ru.itis.nationalbankru.services.role;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.nationalbankru.entity.Role;

public interface RoleService extends JpaRepository<Role, Long> {
}
