package ru.itis.nationalbankru.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.entity.User;

import java.util.UUID;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {

    Page<Organization> findOrganizationsByUser(User user, Pageable pageable);

}
