package model;

import org.json.JSONObject;
import persistence.Writable;

public class Ingredient implements Writable {
    private final String name;

    public Ingredient(String name) {
        this.name = name;
    }

    public String getIngredientName() {
        return this.name;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
    }
}
