public class Booking {
   
      // Attributes
   
   private int bookingID;
   private int roomID;
   private String userID;
   private boolean isExtended;
   private int startDate;
   private int endDate;
   private int price;
   private boolean hasInternet;
   
      // Constructors
   
   public Booking ()
   {  
   
   }
   
   public Booking ( int bookingID, int roomID, String userID, int startDate, int endDate, int roomPrice, boolean hasInternet ) 
   {
      this.roomID = roomID;
      this.userID = userID;
      this.startDate = startDate;
      this.endDate = endDate;
      this.bookingID = bookingID;
      this.isExtended = false;
      this.hasInternet = hasInternet;
      this.price = ( endDate - startDate ) * roomPrice;
      if ( hasInternet ) 
      {
         this.price += 100;
      }
    }
   
   
      // Methods
   
   //public boolean isBookable ( int startDate, int endDate ); - this should be done before the creation of the booking ?
   // perhaps add smth called isExtendable ?
      
      // Getters
   
   public int getBookingID ()
   {
      return bookingID;
   }
   public String getUserID ()
   {
      return userID;
   }  
   public int getRoomID ()
   {
      return roomID;
   }
   public boolean getIsExtended ()
   {
      return isExtended;
   }
   public int getStartDate ()
   {
      return startDate;
   }
   public int getEndDate ()
   {
      return endDate;
   }
   public int getPrice ()
   {
      return price;
   }
   public boolean getHasInternet ()
   {
      return hasInternet;
   }
   
      // Setters   
      
   public void setBookingID ( int bookingID ) 
   {
      this.bookingID = bookingID;
   }
   public void setUserID ( String userID ) 
   {
      this.userID = userID;
   }
   public void setRoomID ( int roomID ) 
   {
      this.roomID = roomID;
   }
   public void setIsExtended ( boolean isExtended ) 
   {
      this.isExtended = isExtended;
   }
   public void setStartDate ( int startDate ) 
   {
      this.startDate = startDate;
   }
   public void setEndDate ( int endDate ) 
   {
      this.endDate = endDate;
   }
   public void setPrice ( int price ) 
   {
      this.price = price;
   }
   public void setHasInternet ( boolean hasInternet ) 
   {
      this.hasInternet = hasInternet;
   }

      
      // String Methods 
   
   public String toString ()
   {
      return "Booking No. : " + bookingID +
             "\nExtended : " + isExtended +
             "\nInternet included : " + hasInternet +
             "\nUser No. : " + userID + 
             "\nRoom No. : " + roomID +
             "\nBooking Dates : " + startDate + " - " + endDate + 
             "\nPrice : " + price;
   }
   
   public String fileFormatString ()
   {
      return bookingID + " " + userID + " " + roomID + " " + isExtended + " " + startDate + " " + endDate + " " + hasInternet + " " + price; 
   }
}