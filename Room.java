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
   private boolean[] calendar; 
   
   
   //Semi-blank constructor
   public Room ()
   {
      this.calendar = new boolean[365];
   }

   //Cool Constructor
   public Room (int roomID, int beds)
   {
      this.calendar = new boolean[365];
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
   
   //raw string data for file processing
   public String fileFortmatString()
   {
      return roomID +" "+ floor +" "+ beds +" "+ price;
   }
   
//________________________________________________________GETTERS_AND_SETTERS__________________________________________________

//________________________________________________________CALENDAR________________________________________ 
   
   public boolean[] getCalendar()
   {
      return calendar;
   }
   
   public void setCalendar(boolean[] calendar)
   {
      this.calendar = calendar;
   }


//________________________________________________________BASE_PRICE______________________________________
   
   public int getBasePrice()
   {
      return basePrice;
   }   

   public static void setBasePrice(int basePrice2)
   {
      basePrice = basePrice2;
   }

//________________________________________________________PRICE_PER_BED___________________________________


   public int getPricePerBed()
   {
      return pricePerBed;
   }
   
   public static void setPricePerBed(int pricePerBed2)
   {
      pricePerBed = pricePerBed2;
   }


//________________________________________________________FLOOR_MULTIPLIER________________________________
   
   public double getFloorMultiplier()
   {
      return floorMultiplier;
   }


   public static void setFloorMultiplier(double floorMultiplier2)
   {
      floorMultiplier = floorMultiplier2;
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