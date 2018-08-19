package org.bonitasoft.connectors.chain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Asset implements Serializable {

    private String color;
    private String model;
    private String weight;

    public Asset() {
    }

    /**
     *
     * @param color
     * @param model
     * @param weight
     */
    public Asset(String color, String model, String weight) {
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }



}
