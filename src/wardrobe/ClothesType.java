/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wardrobe;

/**
 *
 * @author Adamaltar
 */
public enum ClothesType {
    
    SHIRT{
        @Override
        public boolean isUpperClothes() {
            return true;
        }                
    },
    BLOUSE{
        @Override
        public boolean isUpperClothes() {
            return true;
        }                
    },
    SKIRT{
        @Override
        public boolean isUpperClothes() {
            return false;
        }                
    },
    TROUSERS{
        @Override
        public boolean isUpperClothes() {
            return false;
        }                
    }
    ;
    
    
    
    
    
    public abstract boolean isUpperClothes();
    
    
    
}
