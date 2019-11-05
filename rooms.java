
public class rooms {
   
      //Room attributes
  
   private int roomID;
   private int beds;
   private boolean internet;
   private double price; 
   private int floor;
   
      //Room constructor 
   
   public rooms () {}
   public rooms ( int roomID, int beds, boolean internet, double price, int floor ) {
      this.roomID = roomID;
      this.beds = beds;
      this.internet = internet;
      this.price = price;
      this.floor = floor;
   }
   
      //Method to get internet availability 
   
   public void INTERNET( boolean a ) {
      System.out.print( a ? "Available" : "Not available" );
   }
   
      //Getters
      
   public int getRoomID () {
      return roomID;
   }
   public int getBeds () {
      return beds;
   }
   public boolean getInternet () {
      return internet;
   }
   public double getPrice () {
      return price; 
   }
   public int getFloor () {
      return floor; 
   }
   
      //Setters
      
   public void setRoomID (int roomID) {
      this.roomID = roomID;
   }
   public void getBeds (int beds) {
      this.beds = beds;
   }
   public void setInternet (boolean internet) {
      this.internet = internet; 
   }
   public void setPrice (int price) {
      this.price = price; 
   }
   public void setFloor (int floor) {
      this.floor = floor; 
   }
   
      //toString Method 
   
   public String toString() {
      return "ROOM ID  : " + roomID +
             "\nBeds     : " + beds +
             "\nInternet : " + (internet ? "Internet available!" : "Internet not available!") +
             "\nPrice    : " + price +
             "\nFloor    : " + floor; 
      
   }
}
