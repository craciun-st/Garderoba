/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wardrobe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Adamaltar
 */
public class Wardrobe {

    private List<Hanger> hangers;
    private int maxAllowed;

    public Wardrobe(int maxAllowed) {
        this.maxAllowed = maxAllowed;
        this.hangers = new ArrayList<>();
    }



    public int getMaxAllowed() {
        return maxAllowed;
    }

    public void setMaxAllowed(int maxAllowed) {
        this.maxAllowed = maxAllowed;
    }

    public List<Hanger> getHangers() {
        return List.copyOf(hangers);
    }

    public boolean put(Hanger hanger) {
        if (!isWardrobeFull()) {
            hangers.add(hanger);
            return true;
        }
        return false;
    }

    public Clothes takeOutOfWardrobe(int clothesId) {
        Hanger hangerWithClothes = hangers.stream()
                .filter(hanger -> hanger.isClothesOnHanger(clothesId))
                .findFirst().orElse(null);
        if (hangerWithClothes == null) { return null; }
        else { return hangerWithClothes.takeOffOne(clothesId); }
    }

    public boolean hasRoomFor(ClothesType type) {
        if (!isWardrobeFull()) { return true; }
        else {
            // wardrobe is full, but there may be room on individual hangers
            Hanger hangerWithRoom = hangers.stream()
                    .filter(hanger -> hanger.isRoomFor(type))
                    .findFirst().orElse(null);
            return (hangerWithRoom != null);
        }
    }

    public void clear() {
        hangers.clear();
    }

    public boolean isWardrobeFull() {
        return (hangers.size() >= maxAllowed);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
