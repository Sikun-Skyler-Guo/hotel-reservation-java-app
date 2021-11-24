package model;

public class Room implements IRoom{
    String roomNumber;
    Double price;
    RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration){
        super();
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public String getRoomNumber(){
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public boolean isFree() {
        if (price == 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String toString(){
        return "room number: " + roomNumber + " room price: $" + price + " room type: " + enumeration;
    }
}
