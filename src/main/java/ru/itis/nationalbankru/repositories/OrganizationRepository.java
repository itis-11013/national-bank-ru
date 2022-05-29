package ru.itis.nationalbankru.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import ru.itis.nationalbankru.entity.Organization;
import ru.itis.nationalbankru.entity.User;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

public interface OrganizationRepository extends PagingAndSortingRepository<Organization, UUID> {

    List<Organization> findAll(Pageable pageable);

    List<Organization> findOrganizationsByUser(User user, Pageable pageable);

}
