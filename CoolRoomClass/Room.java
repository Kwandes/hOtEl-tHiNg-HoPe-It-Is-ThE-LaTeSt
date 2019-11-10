public class Room {

   //Defining attributes
   private int beds;
   private int floor;
   private int roomID;
   private int price;
   private boolean hasInternet;
   private boolean isBooked;
   private boolean requiresCleaning;
   
   //Blank constructor
   public Room ()
   {
   }

   //Cool Constructor
   public Room (int floor, int roomID, int beds, boolean hasInternet)
   {
      this.floor = floor;
      this.roomID = roomID;
      this.beds = beds;
      this.hasInternet = hasInternet;
      isBooked = true;
      this.price = countPrice(floor, beds, hasInternet);
      requiresCleaning=false;
   }

   //Method to determine room price. Based on a base room price + beds + floor hight + internet (higher floors have better view)
   private static int countPrice(int floor, int beds, boolean hasInternet)
   {
      double totalPrice = 0;
      int pricePerBed = 300;
      double floorMultiplier = 1.05;
      int basePrice = 100;
      
      totalPrice += basePrice;
      totalPrice += beds * pricePerBed;
      totalPrice =  totalPrice * (Math.pow(floorMultiplier, floor));
      
      if (hasInternet)
      {
         totalPrice += 100;
      }
      
      return (int)totalPrice;
   } 

   //you know what this is
   public String toString() 
   {
      return "Floor :\t\t\t\t" + floor 
         + "\nRoom ID :\t\t\t" + roomID
         + "\nNumber of Beds :\t" + beds
         + "\nInternet :\t\t\t" + hasInternet
         + "\nRequiresCleaning? " + requiresCleaning
         + "\nPrice :\t\t\t\t" + price;  
   }
   
   
//________________________________________________________GETTERS_AND_SETTERS__________________________________________________


//________________________________________________________PRICE___________________________________________
   
   public int getPrice()
   {
      return this.price;
   }

   public void setPrice(int price)
   {
      this.price = price;
   }


//________________________________________________________BEDS____________________________________________
  
   public int getBeds()
   {
      return this.beds;
   }
   
   public void setBeds(int beds)
   {
      this.beds = beds;
   }


//________________________________________________________HAS_INTERNET____________________________________  

   public boolean getHasInternet()
   {
      return hasInternet;
   }
   //have to have countPrice here to make sure that if we add or remove internet its price is counted
   public void setHasInternet(boolean hasInternet) 
   {
      this.hasInternet = hasInternet;
      this.price = countPrice(floor, beds, hasInternet);
   }


//________________________________________________________ROOM_ID_________________________________________

   public int getRoomID()
   {
      return roomID;
   }

   public void setRoomID(int roomID)
   {
      this.roomID = roomID;
   }


//________________________________________________________FLOOR___________________________________________

   public int getFloor()
   {
      return floor;
   }
   //have to add countPrice so that the floor multiplier is added to the price
   public void setFloor(int floor)
   {
      this.floor = floor;
      this.price = countPrice(floor, beds, hasInternet);
   }


//________________________________________________________REQUIRES_CLEANING_______________________________

   public boolean getRequiresCleaning()
   {
      return requiresCleaning;
   }

   public void setRequiresCleaning(boolean condition)
   {
      this.requiresCleaning = condition;
   }
//________________________________________________________IS_BOOKED_______________________________________
   public boolean getIsBooked()
   {
      return isBooked;
   }   
   
   public void setIsBooked(boolean isBooked)
   {
      this.isBooked = isBooked;   
   }   
}