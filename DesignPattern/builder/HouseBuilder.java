package DesignPattern.builder;

public class HouseBuilder extends House {
   private  final House house;
    public HouseBuilder() {
        house = new House();
    }

    public  HouseBuilder setHasSimpleWalls(boolean hasSimpleWalls) {
        house.hasSimpleWalls = hasSimpleWalls;
        return this;
    }

    public  HouseBuilder setHasCloth(boolean hasCloth) {
        house.hasCloth =hasCloth;
        return this;
    }

    public HouseBuilder  setHasGlass(boolean hasGlass){
        house.hasGlass =hasGlass;

         return this;
    }

    public  House build(){
        return this.house;
    }

}
