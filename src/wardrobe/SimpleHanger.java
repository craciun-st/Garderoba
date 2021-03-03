/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wardrobe;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adamaltar
 */
public class SimpleHanger extends Hanger {
    
    
    private Clothes top;

    @Override
    public Clothes takeOffOne(int id) {
        if (isClothesOnHanger(id)){
            Clothes temp=top;
            top=null;
            return temp;
        }
        return null;
    }

    @Override
    public List<Clothes> takeOffAll() {
        if (top==null)
            return null;
        List<Clothes> clothes=new ArrayList<>();
        clothes.add(takeOffOne(top.getId()));
        return clothes;
    }

    @Override
    public boolean put(Clothes clothes) {
        if (isRoomFor(clothes.getType())) {
            top = clothes;
            return true;
        }
        else { return false; }
    }

    @Override
    public boolean isRoomFor(ClothesType type) {
        return (top == null && type.isUpperClothes());
    }
    
    
    private boolean isClothesOnHanger(int id){
        if (top==null)
            return false;
        
        return top.getId()==id;
    }
    
    
    
    
}
