public class Room {

   //Defining attributes
   private int beds;
   private int floor;
   private int roomID;
   private int price;
   private boolean isBooked;
   private boolean requiresCleaning;
   
   private static int basePrice;
   private static int pricePerBed;
   private static double floorMultiplier;
   
   
   //Blank constructor
   public Room ()
   {
   }

   //Cool Constructor
   public Room (int roomID, int beds)
   {
      this.floor = roomID/100;
      this.roomID = roomID;
      this.beds = beds;
      this.isBooked = false;
      this.price = countPrice(floor, beds);
      this.requiresCleaning = false;
   }

   //Method to determine room price. Based on a base room price + beds + floor hight + internet (higher floors have better view)
   public static int countPrice(int floor, int beds)
   {  
      double totalPrice = 0;    
      totalPrice += basePrice;
      totalPrice += beds * pricePerBed;
      totalPrice =  totalPrice * (Math.pow(floorMultiplier, floor));
      
      return (int)totalPrice;
   } 

   //you know what this is
   public String toString() 
   {
      return "Floor :\t\t\t\t" + floor 
         + "\nRoom ID :\t\t\t" + roomID
         + "\nNumber of Beds :\t" + beds
         + "\nRequiresCleaning? " + requiresCleaning
         + "\nPrice :\t\t\t\t" + price;  
   }
   
   public String fileFortmatString()
   {
      return roomID +" "+ floor +" "+ beds +" "+ price;
   }
   
//________________________________________________________GETTERS_AND_SETTERS__________________________________________________

   //private static int basePrice;
   //private static int pricePerBed;
   //private static double floorMultiplier;
   
   public int getBasePrice()
   {
      return basePrice;
   }   

   public static void setBasePrice(int basePrice2)
   {
      basePrice = basePrice2;//this wont work without this.
   }

   public int getPricePerBed()
   {
      return pricePerBed;
   }
   
   public static void setPricePerBed(int pricePerBed2)
   {
      pricePerBed = pricePerBed2;//this wont work without this.
   }
   
   public double getFloorMultiplier()
   {
      return floorMultiplier;
   }


   public static void setFloorMultiplier(double floorMultiplier2)
   {
      floorMultiplier = floorMultiplier2;//this wont work without this.
   }

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
      this.price = countPrice(floor, beds);
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
      this.price = countPrice(floor, beds);
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