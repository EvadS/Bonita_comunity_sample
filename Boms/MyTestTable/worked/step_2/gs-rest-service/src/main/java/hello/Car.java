package hello;

public class Car {

    private  int id;
    private String color;
    private String model;
    private int weight;

    public Car() {

    }

    public Car(String color, String model, int weight) {
        this.color = color;
        this.model = model;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
