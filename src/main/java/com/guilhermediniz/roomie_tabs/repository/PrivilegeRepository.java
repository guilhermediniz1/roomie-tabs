package com.guilhermediniz.roomie_tabs.repository;


import com.guilhermediniz.roomie_tabs.entity.Privilege;
import com.guilhermediniz.roomie_tabs.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Privilege findByName(String name);
}
