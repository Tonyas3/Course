package com.gmail.antonovich.tonya.morionkot.repository;

import com.gmail.antonovich.tonya.morionkot.entity.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin, Long> {

    Optional<Admin> findByLogin(String login);
}
