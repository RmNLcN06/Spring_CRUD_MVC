package com.rmnlcn.Spring_CRUD_MVC.services;

import com.rmnlcn.Spring_CRUD_MVC.entities.Wrestler;
import com.rmnlcn.Spring_CRUD_MVC.repositories.WrestlerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WrestlerServiceImpl implements WrestlerService {

    private final WrestlerRepository wrestlerRepository;

    @Autowired
    public WrestlerServiceImpl(WrestlerRepository theWrestlerRepository) {
        wrestlerRepository = theWrestlerRepository;
    }

    @Override
    public List<Wrestler> findAll() {
        return wrestlerRepository.findAll();
    }

    @Override
    public Wrestler findById(int theId) {
        Optional<Wrestler> result = wrestlerRepository.findById(theId);

        Wrestler theWrestler = null;

        if(result.isPresent()) {
            theWrestler = result.get();
        }
        else
        {
            throw new RuntimeException("Did not find wrestler id - " + theId);
        }
        return theWrestler;
    }

    @Override
    public Wrestler save(Wrestler theWrestler) {
        return wrestlerRepository.save(theWrestler);
    }

    @Override
    public void deleteById(int theId) {
        wrestlerRepository.deleteById(theId);
    }
}
