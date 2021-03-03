/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wardrobe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            System.out.printf(
                    "Taken out clothing with id=%d of brand %s from top part of double hanger \n",
                    id,
                    temp.getBrand()
            );
            return temp;
        }
        else {
            if (isClothesOnBottomPart(id)) {
                temp = bottom;
                bottom = null;
                System.out.printf(
                        "Taken out clothing with id=%d of brand %s from bottom part of hanger \n",
                        id,
                        temp.getBrand()
                );
                return temp;
            }
            else {
                System.out.printf("No clothing with id=%d found on double hanger! \n", id);
                return null;
            }
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
        if (clothesOnHanger.size() > 0) {
            String joinedListDescriptor = clothesOnHanger.stream()
                    .map(Clothes::toString)
                    .collect(Collectors.joining(";\n"));
            System.out.println("Retrieved the following list of clothes from double hanger: \n"+joinedListDescriptor);
            return clothesOnHanger;
        }
        else {
            System.out.println("No clothing found on double hanger!");
            return null;
        }
    }

    @Override
    public boolean put(Clothes clothes) {
        if (top == null || bottom == null) {
            if (top == null && clothes.getType().isUpperClothes()) {
                top = clothes;
                System.out.printf(
                        "Put clothing with id=%d of brand %s on top part of double hanger \n",
                        clothes.getId(),
                        clothes.getBrand()
                );
                return true;
            }
            if (bottom == null && !(clothes.getType().isUpperClothes())) {
                bottom = clothes;
                System.out.printf(
                        "Put clothing with id=%d of brand %s on bottom part of double hanger \n",
                        clothes.getId(),
                        clothes.getBrand()
                );
                return true;
            }
        }
        System.out.printf("Could not put clothing with id=%d of brand %s on double hanger \n",
                clothes.getId(),
                clothes.getBrand()
        );
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
