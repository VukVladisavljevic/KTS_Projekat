package com.kits.project.services.implementations;

import com.kits.project.DTOs.PricelistDTO;
import com.kits.project.model.Pricelist;
import com.kits.project.repositories.PricelistRepository;
import com.kits.project.services.interfaces.PricelistServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PricelistServiceImplementation implements PricelistServiceInterface {

    @Autowired
    private PricelistRepository pricelistRepository;

    @Override
    public Pricelist addPricelist(PricelistDTO pricelistDTO){
        Pricelist newPricelist = new Pricelist(pricelistDTO);
        Pricelist existing = pricelistRepository.checkIfUnique(newPricelist.getStartDate(), newPricelist.getEndDate(),
                newPricelist.getTicketType());
        if (existing == null){
            pricelistRepository.save(newPricelist);
            return newPricelist;
        }
        return null;
    }

    @Override
    public Pricelist updatePricelist(Long pricelistId, PricelistDTO pricelistDTO){
        return null;
    }

    @Override
    public ArrayList<Pricelist> getCurrentPricelist(){
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date currentDate = new Date();
        ArrayList<Pricelist> current = pricelistRepository.getCurrent(currentDate);
        return current;
    }
}
