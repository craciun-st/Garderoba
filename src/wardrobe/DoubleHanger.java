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
public class DoubleHanger extends Hanger {

    private Clothes top;
    private Clothes bottom;

    public DoubleHanger() {

    }

    @Override
    public Clothes takeOffOne(int id) {
        Clothes temp;
        if (isClothesOnTopPart(id)) {
            temp = top;
            top = null;
            return temp;
        }
        else {
            if (isClothesOnBottomPart(id)) {
                temp = bottom;
                bottom = null;
                return temp;
            }
            else { return null; }
        }
    }

    @Override
    public List<Clothes> takeOffAll() {
        List<Clothes> clothesOnHanger = new ArrayList<>();
        if (top != null) {
            clothesOnHanger.add(takeOffOne(top.getId()));
        }
        if (bottom != null) {
            clothesOnHanger.add(takeOffOne(bottom.getId()));
        }
        if (clothesOnHanger.size() > 0) { return clothesOnHanger; }
        else { return null; }
    }

    @Override
    public boolean put(Clothes clothes) {
        if (top == null || bottom == null) {
            if (top == null && clothes.getType().isUpperClothes()) {
                top = clothes;
                return true;
            }
            if (bottom == null && !(clothes.getType().isUpperClothes())) {
                bottom = clothes;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isRoomFor(ClothesType type) {
        if ( type.isUpperClothes() ) {
            return (top == null);
        }
        else { return (bottom == null); }
    }

    @Override
    protected boolean isClothesOnHanger(int clothesId) {
        if (top == null && bottom == null) { return false; }
        else {
            if (isClothesOnTopPart(clothesId)) { return true; }
            else {
                if (isClothesOnBottomPart(clothesId)) { return true; }
                else { return false; }
            }
        }
    }

    private boolean isClothesOnTopPart(int id) {
        return (top != null && top.getId() == id);
    }

    private boolean isClothesOnBottomPart(int id) {
        return (bottom != null && bottom.getId() == id);
    }


}
