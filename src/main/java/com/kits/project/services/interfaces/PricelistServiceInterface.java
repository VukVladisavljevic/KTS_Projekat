package com.kits.project.services.interfaces;

import com.kits.project.DTOs.PricelistDTO;
import com.kits.project.model.Pricelist;
import org.springframework.stereotype.Service;

public interface PricelistServiceInterface {

    Pricelist addPricelist(PricelistDTO pricelistDTO);

    Pricelist updatePricelist(Long pricelistId, PricelistDTO pricelistDTO);
}
