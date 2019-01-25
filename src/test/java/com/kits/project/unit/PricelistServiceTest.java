package com.kits.project.unit;

import com.kits.project.DTOs.PricelistDTO;
import com.kits.project.model.Pricelist;
import com.kits.project.model.TicketType;
import com.kits.project.repositories.PricelistRepository;
import com.kits.project.services.implementations.PricelistServiceImplementation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricelistServiceTest {

    @MockBean
    private PricelistRepository pricelistRepository;

    @Autowired
    private PricelistServiceImplementation priceListService;

    @Before
    public void setUp() {
        Date startDate;
        Date endDate;
        try {
            startDate=new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2018");
            endDate = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2019");
        } catch (Exception e) {
            return;
        }
        PricelistDTO pricelist = new PricelistDTO(startDate, endDate, TicketType.MONTHLY, 12.12);
        Pricelist pricelist1 = new Pricelist(pricelist);
        ArrayList<Pricelist> list = new ArrayList<>();
        list.add(pricelist1);

        Mockito.when(pricelistRepository.checkIfUnique(Mockito.any(Date.class), Mockito.any(Date.class), eq(TicketType.DAILY))).thenReturn(list);
        Mockito.when(pricelistRepository.checkIfUnique(Mockito.any(Date.class), Mockito.any(Date.class), eq(TicketType.MONTHLY))).thenReturn(null);
        Mockito.when(pricelistRepository.checkIfUnique(Mockito.any(Date.class), Mockito.any(Date.class), eq(TicketType.SINGLE))).thenReturn(new ArrayList<>());
        Mockito.when(pricelistRepository.save(Mockito.any(Pricelist.class))).thenReturn(pricelist1);
        Mockito.when(pricelistRepository.getCurrent(Mockito.any(Date.class))).thenReturn(list);
        Mockito.when(pricelistRepository.getAll()).thenReturn(list);
    }

    @Test
    public void addPriceListTest() {
        Date startDate;
        Date endDate;
        try {
            startDate=new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2018");
            endDate = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2019");
        } catch (Exception e) {
            return;
        }
        PricelistDTO pricelist = new PricelistDTO(startDate, endDate, TicketType.MONTHLY, 12.12);
        Pricelist result = priceListService.addPricelist(pricelist);
        assertEquals(result.getEndDate(), endDate);
        assertEquals(result.getStartDate(), startDate);
        assertEquals(result.getTicketType(), TicketType.MONTHLY);

        verify(pricelistRepository, times(1)).checkIfUnique(Mockito.any(Date.class), Mockito.any(Date.class), eq(TicketType.MONTHLY));
        verify(pricelistRepository, times(1)).save(Mockito.any(Pricelist.class));
    }

    @Test
    public void addPriceListExistsTest() {
        Date startDate;
        Date endDate;
        try {
            startDate=new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2018");
            endDate = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2019");
        } catch (Exception e) {
            return;
        }
        PricelistDTO pricelist = new PricelistDTO(startDate, endDate, TicketType.DAILY, 12.12);
        Pricelist result = priceListService.addPricelist(pricelist);
        assertEquals(result, null);

        verify(pricelistRepository, times(1)).checkIfUnique(Mockito.any(Date.class), Mockito.any(Date.class), eq(TicketType.DAILY));
        verify(pricelistRepository, times(0)).save(Mockito.any(Pricelist.class));
    }

    @Test
    public void addPriceListWrongDatesTest() {
        Date startDate;
        Date endDate;
        try {
            endDate=new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2018");
            startDate = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2019");
        } catch (Exception e) {
            return;
        }
        PricelistDTO pricelist = new PricelistDTO(startDate, endDate, TicketType.SINGLE, 12.12);
        Pricelist result = priceListService.addPricelist(pricelist);
        assertEquals(result, null);

        verify(pricelistRepository, times(0)).checkIfUnique(Mockito.any(Date.class), Mockito.any(Date.class), eq(TicketType.SINGLE));
        verify(pricelistRepository, times(0)).save(Mockito.any(Pricelist.class));
    }

    @Test
    public void addPriceListEmptyListTest() {
        Date startDate;
        Date endDate;
        try {
            startDate=new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2018");
            endDate = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2019");
        } catch (Exception e) {
            return;
        }
        PricelistDTO pricelist = new PricelistDTO(startDate, endDate, TicketType.SINGLE, 12.12);
        Pricelist result = priceListService.addPricelist(pricelist);
        assertEquals(result.getEndDate(), endDate);
        assertEquals(result.getStartDate(), startDate);
        assertEquals(result.getTicketType(), TicketType.SINGLE);

        verify(pricelistRepository, times(1)).checkIfUnique(Mockito.any(Date.class), Mockito.any(Date.class), eq(TicketType.SINGLE));
        verify(pricelistRepository, times(1)).save(Mockito.any(Pricelist.class));
    }

    @Test
    public void getCurrentPriceListTest() {
        ArrayList<Pricelist> result = priceListService.getCurrentPricelist();
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getTicketType(), TicketType.MONTHLY);
        verify(pricelistRepository, times(1)).getCurrent(new Date());
    }

    @Test
    public void getAllPriceListTest() {
        ArrayList<Pricelist> result = priceListService.getAllPricelists();
        assertEquals(result.size(), 1);
        verify(pricelistRepository, times(1)).getAll();
    }
}
