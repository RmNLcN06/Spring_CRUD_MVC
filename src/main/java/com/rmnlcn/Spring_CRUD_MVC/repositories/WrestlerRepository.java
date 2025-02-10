package com.rmnlcn.Spring_CRUD_MVC.repositories;

import com.rmnlcn.Spring_CRUD_MVC.entities.Wrestler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WrestlerRepository extends JpaRepository<Wrestler, Integer> {

    // add method to sort by last name
    public List<Wrestler> findAllByOrderByLastNameAsc();
}
