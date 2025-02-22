package com.guilhermediniz.roomie_tabs.repository;


import com.guilhermediniz.roomie_tabs.entity.Expense;
import com.guilhermediniz.roomie_tabs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
