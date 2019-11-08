// Placeholder class
public class Booking
{
   private int bookingID;
   private int startDate;
   private int endDate;
   private int userID;

   public Booking(int roomID, int userID, int startDate, int endDate)
   {
   
   }

   public boolean isBookable(int roomID, int startDate, int endDate)
   {
      return true; //or false
   }
   
   public int getBookingID()
   {
      return bookingID;
   }
   
   public void setStartDate(int startDate)
   {
      this.startDate = startDate;
   }
   
   public void setEndDate(int endDate)
   {
      this.endDate = endDate;
   }
   
   public int getUserID()
   {
      return this.userID;
   }
}