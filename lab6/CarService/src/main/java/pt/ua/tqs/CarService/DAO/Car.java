package pt.ua.tqs.CarService.DAO;


import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    private static long idCache = 1;

    @Id
    Long cardId;

    String maker;
    String Model;

    public Car() {
        cardId = idCache;
        idCache +=1;
    }
    public Car(String maker, String model) {
        cardId = idCache;
        idCache +=1;
        this.maker = maker;
        Model = model;
    }

    public Car(long id,String maker, String model) {
        this.cardId = id;
        this.maker = maker;
        this.Model = model;
    }


    public Car(CarDTO car) {
        cardId = car.cardId;
        idCache +=1;
        this.maker = car.maker;
        Model = car.Model;
    }

    
    public Long getCardId() {
        return cardId;
    }
    public void setCardId(Long cardId) {
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
    
    @Override
    public String toString() {
        return "Car [cardId=" + cardId + ", maker=" + maker + ", Model=" + Model + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cardId == null) ? 0 : cardId.hashCode());
        result = prime * result + ((maker == null) ? 0 : maker.hashCode());
        result = prime * result + ((Model == null) ? 0 : Model.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Car other = (Car) obj;
        if (cardId == null) {
            if (other.cardId != null)
                return false;
        } else if (!cardId.equals(other.cardId))
            return false;
        if (maker == null) {
            if (other.maker != null)
                return false;
        } else if (!maker.equals(other.maker))
            return false;
        if (Model == null) {
            if (other.Model != null)
                return false;
        } else if (!Model.equals(other.Model))
            return false;
        return true;
    }

    
    

    

}
