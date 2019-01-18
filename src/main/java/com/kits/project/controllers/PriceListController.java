package com.kits.project.controllers;

import com.kits.project.DTOs.PricelistDTO;
import com.kits.project.model.Pricelist;
import com.kits.project.model.Station;
import com.kits.project.services.interfaces.PricelistServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
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
        //Test
        System.out.println(newPricelist);

        return new ResponseEntity<>(newPricelist, HttpStatus.OK);
    }


    //------------------get current pricelist----------------------------------------------

    @RequestMapping(value = "/pricelist/getcurrent", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<Pricelist>> getCurrentPriceList(){
        ArrayList<Pricelist> pricelistCurrent = pricelistService.getCurrentPricelist();
        return new ResponseEntity<>(pricelistCurrent, HttpStatus.OK);
    }

}
