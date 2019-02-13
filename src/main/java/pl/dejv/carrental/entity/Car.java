package pl.dejv.carrental.entity;


import javax.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private boolean isAvailAble;
    private double price;

    @ManyToOne
    @JoinColumn(name ="office_id")
    private Office office;

    public Car() {
    }

    public Car(String brand, String model, boolean isAvailAble, double price, Office office) {
        this.brand = brand;
        this.model = model;
        this.isAvailAble = isAvailAble;
        this.price = price;
        this.office = office;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAvailAble() {
        return isAvailAble;
    }

    public void setAvailAble(boolean availAble) {
        isAvailAble = availAble;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
