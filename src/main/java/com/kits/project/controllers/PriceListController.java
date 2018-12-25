package com.kits.project.controllers;

import com.kits.project.DTOs.PricelistDTO;
import com.kits.project.model.Pricelist;
import com.kits.project.services.interfaces.PricelistServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api")
@RestController
public class PriceListController {

    @Autowired
    private PricelistServiceInterface pricelistService;

    //------------------create pricelist--------------------------------------------------

    @RequestMapping(value = "/pricelist/create",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pricelist> create(@RequestBody PricelistDTO pricelist){
            Pricelist newPricelist = pricelistService.addPricelist(pricelist);
        return new ResponseEntity<>(newPricelist, HttpStatus.OK);
    }


}
