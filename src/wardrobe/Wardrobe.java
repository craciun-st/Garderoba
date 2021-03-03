/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wardrobe;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            System.out.printf("Successfully put hanger %s in wardrobe %s \n", hanger.toString(), this.toString());
            return true;
        }
        System.out.printf("Could not put hanger %s in wardrobe %s! \n", hanger.toString(), this.toString());
        return false;
    }

    public Clothes takeOutOfWardrobe(int clothesId) {
        Hanger hangerWithClothes = hangers.stream()
                .filter(hanger -> hanger.isClothesOnHanger(clothesId))
                .findFirst().orElse(null);
        if (hangerWithClothes == null) {
            System.out.printf("Could not find clothing with id=%d in wardrobe %s! \n", clothesId, this.toString());
            return null;
        }
        else {
            Clothes removedClothes = hangerWithClothes.takeOffOne(clothesId);
            System.out.printf("Successfuly removed clothing with id=%d of type %s from wardrobe %s \n",
                    clothesId,
                    removedClothes.getType().toString(),
                    this.toString());
            return  removedClothes;
        }
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
        Random randomizer = new Random();
        String[] clothesBrands = new String[]{"Avanti", "Hot Topic", "Zara", "H&M", "Marks&Spencer", "Urban Paces"};
        ClothesType[] orderedClothesType = new ClothesType[]{
                ClothesType.TROUSERS,
                ClothesType.SKIRT,
                ClothesType.SKIRT,
                ClothesType.BLOUSE,
                ClothesType.BLOUSE,
                ClothesType.SHIRT,
                ClothesType.SHIRT
        };

        Wardrobe bigWardrobe = new Wardrobe(10);
        System.out.println("Big wardrobe: "+ bigWardrobe.toString());
        Wardrobe smallWardrobe = new Wardrobe(3);
        System.out.println("Small wardrobe: "+ smallWardrobe.toString()+"\n");

        Clothes currentClothing;
        Hanger currentHanger;

        int indexForBrands;

        for (ClothesType type : orderedClothesType) {
            indexForBrands = randomizer.nextInt(clothesBrands.length);
            currentClothing = new Clothes(clothesBrands[indexForBrands], type);
            if (randomizer.nextInt() % 2 == 0) {
                currentHanger = new SimpleHanger();
                if (currentHanger.put(currentClothing) == false) {
                    currentHanger = new DoubleHanger();
                    currentHanger.put(currentClothing);
                }
            } else {
                currentHanger = new DoubleHanger();
                currentHanger.put(currentClothing);
            }
            if (smallWardrobe.put(currentHanger) == false) {
                bigWardrobe.put(currentHanger);
            }
        }

        indexForBrands = randomizer.nextInt(clothesBrands.length);
        List<Clothes> clothesInSmallWardrobe = smallWardrobe.getHangers().stream()
                .map(Hanger::takeOffAll)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        Clothes newTrousers = new Clothes(clothesBrands[indexForBrands], ClothesType.TROUSERS);

        System.out.println("Can find room in small wardrobe for trousers: "
                +smallWardrobe.hasRoomFor(ClothesType.TROUSERS));
        if (smallWardrobe.hasRoomFor(ClothesType.TROUSERS)) {
            Hanger hangerWithRoom = smallWardrobe.getHangers().stream()
                    .filter(hanger -> hanger.isRoomFor(ClothesType.TROUSERS))
                    .findFirst().orElse(null);
            assert hangerWithRoom != null;
            hangerWithRoom.put(newTrousers);
        }

    }
}
