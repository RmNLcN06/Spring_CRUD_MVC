package com.rmnlcn.Spring_CRUD_MVC.repositories;

import com.rmnlcn.Spring_CRUD_MVC.entities.Wrestler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WrestlerRepository extends JpaRepository<Wrestler, Integer> {
}
