package ru.itis.nationalbankru.reposistory;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.nationalbankru.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
