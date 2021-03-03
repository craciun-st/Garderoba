/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wardrobe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Adamaltar
 */
public class SimpleHanger extends Hanger {
    
    
    private Clothes top;

    public SimpleHanger() {

    }

    @Override
    public Clothes takeOffOne(int id) {
        if (isClothesOnHanger(id)){
            Clothes temp=top;
            top=null;
            System.out.printf(
                    "Taken out clothing with id=%d of brand %s and type %s from simple hanger \n",
                    id,
                    temp.getBrand(),
                    temp.getType().toString()
            );
            return temp;
        }
        return null;
    }

    @Override
    public List<Clothes> takeOffAll() {
        if (top==null) {
            System.out.println("No clothing found on simple hanger!");
            return null;
        }
        List<Clothes> clothes=new ArrayList<>();
        clothes.add(takeOffOne(top.getId()));
        String joinedListDescriptor = clothes.stream()
                .map(Clothes::toString)
                .collect(Collectors.joining(";\n"));
        System.out.println("Retrieved the following list of clothes from simple hanger: \n"+joinedListDescriptor);
        return clothes;
    }

    @Override
    public boolean put(Clothes clothes) {
        if (isRoomFor(clothes.getType())) {
            top = clothes;
            System.out.printf(
                    "Put clothing with id=%d of brand %s and type %s on simple hanger \n",
                    clothes.getId(),
                    clothes.getBrand(),
                    clothes.getType().toString()
            );
            return true;
        }
        else {
            System.out.printf("Could not put clothing with id=%d of brand %s and type %s on simple hanger \n",
                    clothes.getId(),
                    clothes.getBrand(),
                    clothes.getType().toString()
            );
            return false;
        }
    }

    @Override
    public boolean isRoomFor(ClothesType type) {
        return (top == null && type.isUpperClothes());
    }

    @Override
    protected boolean isClothesOnHanger(int clothesId) {
        if (top==null)
            return false;

        return top.getId()== clothesId;
    }


}
