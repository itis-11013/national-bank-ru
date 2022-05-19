package ru.itis.nationalbankru.reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.nationalbankru.entity.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select r from User u join u.roles r where u.id = :user_id")
    List<Role> findByUser(@Param("user_id") Long user_id);
}
