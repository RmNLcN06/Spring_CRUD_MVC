package com.rmnlcn.Spring_CRUD_MVC.services;

import com.rmnlcn.Spring_CRUD_MVC.entities.Wrestler;

import java.util.List;

public interface WrestlerService {

    List<Wrestler> findAll();

    Wrestler findById(int theId);

    Wrestler save(Wrestler theWrestler);

    void deleteById(int theId);

}
