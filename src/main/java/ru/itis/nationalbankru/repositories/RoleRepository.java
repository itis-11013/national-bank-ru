package ru.itis.nationalbankru.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.nationalbankru.entity.Role;

import java.util.List;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByName(@Param("name") String name);
    @Query("select r from User u join u.roles r where u.id = :user_id")
    List<Role> findByUser(@Param("user_id") UUID user_id);
}
