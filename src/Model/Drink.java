package Model;

import java.io.Serializable;

public class Drink implements Serializable {
    private String Id;
    private String name;
    private String type;
    private Boolean status;
    private Double price;

    public Drink(String ID, String name, String type, Boolean status, Double price) {
        this.Id = ID;
        this.name = name;
        this.type = type;
        this.status = status;
        this.price = price;
    }

    public String getID() {
        return Id;
    }

    public void setID(String ID) {
        this.Id = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
