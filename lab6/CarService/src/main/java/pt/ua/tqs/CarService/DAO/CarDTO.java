package pt.ua.tqs.CarService.DAO;


public class CarDTO {
    private static long idCache = 1;


    public long getCardId() {
        return cardId;
    }
    public void setCardId(long cardId) {
        this.cardId = cardId;
    }
    public String getMaker() {
        return maker;
    }
    public void setMaker(String maker) {
        this.maker = maker;
    }
    public String getModel() {
        return Model;
    }
    public void setModel(String model) {
        Model = model;
    }
    public CarDTO() {
        cardId = idCache;
        idCache +=1;
    }
    public CarDTO(String maker, String model) {
        cardId = idCache;
        idCache +=1;
        this.maker = maker;
        Model = model;
    }



    long cardId;
    String maker;
    String Model;
    }