/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wardrobe;

/**
 *
 * @author Utilizator
 */
public class Clothes {
    private static int counter=0;
    private final int id;
    private final String brand;
    private final ClothesType type;

    public Clothes(String brand, ClothesType type) {
        this.id=++counter;
        this.brand = brand;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public ClothesType getType() {
        return type;
    }    
    
}
