package ies.tsds.letscooks;

import java.io.Serializable;

public class Recipe implements Serializable {
    private static final long serialVersionUID = 1L; // Agrega esta línea para asegurar la compatibilidad de versión

    private int id;
    private String name;
    private String description;
    private String ingredients;
    private int image;

    // Constructor
    public Recipe(int id, String name, String description, String ingredients, int image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.image = image;
    }

    public Recipe(int id, String tortillaDePapas, String description, int tortillaDePapa) {
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public int getImage() {
        return image;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

