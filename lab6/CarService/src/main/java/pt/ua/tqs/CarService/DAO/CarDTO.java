package pt.ua.tqs.CarService.DAO;


public class CarDTO {


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
        cardId = IdProvider.getInstance().getUniqueId();
    }
    public CarDTO(String maker, String model) {
        cardId = IdProvider.getInstance().getUniqueId();
        this.maker = maker;
        Model = model;
    }



    long cardId;
    String maker;
    String Model;
    }