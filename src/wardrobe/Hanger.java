/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wardrobe;

import java.util.List;

/**
 *
 * @author Utilizator
 */
public abstract class Hanger {
    
    public abstract Clothes takeOffOne(int id);
    public abstract List<Clothes> takeOffAll();
    public abstract boolean put(Clothes clothes);
    public abstract boolean isRoomFor(ClothesType type);
    
            
}

