package com.kits.project.services.implementations;

import com.kits.project.DTOs.PricelistDTO;
import com.kits.project.model.Pricelist;
import com.kits.project.services.interfaces.PricelistServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class PricelistServiceImplementation implements PricelistServiceInterface {

    @Override
    public Pricelist addPricelist(PricelistDTO pricelistDTO){
        return null;
    }

    @Override
    public Pricelist updatePricelist(Long pricelistId, PricelistDTO pricelistDTO){
        return null;
    }
}
