package com.guilhermediniz.roomie_tabs.repository;


import com.guilhermediniz.roomie_tabs.entity.Role;
import com.guilhermediniz.roomie_tabs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
