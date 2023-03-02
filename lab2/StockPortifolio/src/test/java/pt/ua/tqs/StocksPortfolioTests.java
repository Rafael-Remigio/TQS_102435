package pt.ua.tqs;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ua.tqs.*;



import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.*;



@ExtendWith(MockitoExtension.class)
public class StocksPortfolioTests {

    @Mock
    IStockmarketService market;


    @InjectMocks
    StocksPortfolio portfolio;



    @Test
    void getTotalValueAmount(){
        
        when(market.lookUpPrice("IBM")).thenReturn(2.5);
        when(market.lookUpPrice("FSF")).thenReturn(0.2);


        portfolio.addStock(new Stock("IBM",2));
        portfolio.addStock(new Stock("FSF",10));


        assertEquals(7,portfolio.getTotalValue(),"Portifolio get total value should return 7 but returns something else");
        

    }
    
    @Test
    void getTotalValueHamcrest(){
        
        when(market.lookUpPrice("IBM")).thenReturn(2.5);
        when(market.lookUpPrice("FSF")).thenReturn(0.5);


        portfolio.addStock(new Stock("IBM",2));
        portfolio.addStock(new Stock("FSF",10));


        assertThat(portfolio.getTotalValue(),equalTo(10.0));
        

    }


}