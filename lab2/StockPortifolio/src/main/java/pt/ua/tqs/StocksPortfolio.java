package pt.ua.tqs;
import java.util.List;
import java.util.ArrayList;
import pt.ua.tqs.*;

public class StocksPortfolio {
    List<Stock> stocks = new ArrayList<Stock>();
    IStockmarketService stockmarket;

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket; 
    }

    public void addStock(Stock stock) {
        this.stocks.add(stock); 
    }

    public double getTotalValue() {
        double value = 0;
        for (Stock stock : this.stocks){
            value = value + stockmarket.lookUpPrice(stock.getLabel()) * stock.getQuantity();
        }
        return value;
    }
}
