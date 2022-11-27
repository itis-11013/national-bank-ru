package ru.itis.nationalbankru.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.nationalbankru.entity.Role;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByName(@Param("name") String name);

    @Query("select r from Organization o join o.roles r where o.id = :org_id")
    List<Role> findByOrganization(@Param("org_id") Long org_id);
}
