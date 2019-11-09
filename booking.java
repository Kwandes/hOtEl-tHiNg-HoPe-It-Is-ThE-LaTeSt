import java.util.*;

public class booking {
   
      //attributes 
      
   private String startDate;
   private String endDate;
   private int duration;
   private int roomID;
   private int guestID;

      //Constructor 
      
   public booking () {} 
   public booking (String startDate, String endDate, int duration, int roomID, int guestID ) {
      this.startDate = startDate;
      this.endDate = endDate;
      this.duration = duration;
      this.roomID = roomID;
      this.guestID = guestID;
   }
   
      //Methods
            
   public void START_DATE (Scanner in) {
      in = new Scanner (System.in);
   }
   public void END_DATE (Scanner out) {
      out = new Scanner (System.in); 
   } 
   public int date(int a, int b) {
      return (a - 1) * 31 + b;
   }  
      
      //Getters 
      
   public String getStartDate () {
      return startDate;
   }
   public String getEndDate () {
      return endDate;
   }
   public int getDuration () {
      return duration;
   }
   public int getRoomID () {
      return roomID;
   }
   public int getGuestID () {
      return guestID;
   }
      
      //Setters
   
   public void setStarDate (String startDate) {
      this.startDate = startDate;
   }
   public void SetEndDate (String endDate) {
      this.endDate = endDate;
   }
   public void setDuration (int duration) {
      this.duration = duration;
   }
   public void setRoomID (int roomID) {
      this.roomID = roomID;
   }
   public void setGuestID (int guestID) {
      this.guestID = guestID;
   }
   
      //toString
   
   public String toString() {
      return "Start Date    : " + startDate +
             "\nEnd Date      : " + endDate +
             "\nDays in total : " + duration +
             "\nRoom ID       : " + roomID +
             "\nGuest ID      : " + guestID;
   }
   
}