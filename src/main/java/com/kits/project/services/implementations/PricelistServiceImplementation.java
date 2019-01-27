package com.kits.project.services.implementations;

import com.kits.project.DTOs.PricelistDTO;
import com.kits.project.model.Pricelist;
import com.kits.project.repositories.PricelistRepository;
import com.kits.project.services.interfaces.PricelistServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PricelistServiceImplementation implements PricelistServiceInterface {

    @Autowired
    private PricelistRepository pricelistRepository;

    @Override
    public Pricelist addPricelist(PricelistDTO pricelistDTO){
        Pricelist newPricelist = new Pricelist(pricelistDTO);
        if (!(newPricelist.getStartDate().compareTo(newPricelist.getEndDate()) <= 0)){
            return null;
        }

        //------- dan pre, zbog preklapanja prilikom nadovezivanja cenovnika(e.g. 01.02-01.09 pa 01.09-0.10)
        Calendar cal = Calendar.getInstance();
        cal.setTime(newPricelist.getStartDate());
        cal.add(Calendar.DATE, 1);
        Date dateBefore = cal.getTime();
        System.out.println(dateBefore);

        ArrayList<Pricelist> existing = pricelistRepository.checkIfUnique(dateBefore, newPricelist.getEndDate(),
                newPricelist.getTicketType());

        if (existing==null || existing.size() == 0){
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
        ArrayList<Pricelist> current = new ArrayList<Pricelist>();
        current = pricelistRepository.getCurrent(currentDate);
        return current;
    }

    @Override
    public ArrayList<Pricelist> getAllPricelists(){
        List<Pricelist> all = pricelistRepository.findAll();
        ArrayList<Pricelist> allA = new ArrayList<>();
        for (Pricelist p : all){
            allA.add(p);
        }
        return allA;
    }
}
