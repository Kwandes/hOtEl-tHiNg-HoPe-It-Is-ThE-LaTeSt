// Placeholder class

import java.util.ArrayList;

public class FileManagement
{
   public void appendToFile(String logMessage)
   {
      System.out.println(logMessage);
   }
   
   ////////// Save X From File //////////
   public void saveUserToFile(ArrayList<User> array)
   {
      
   }
   
   public void saveRoomToFile(ArrayList<Room> array)
   {
   
   }
   
   public void saveBookingToFile(ArrayList<Booking> array)
   {
   
   }
   
   public void saveToFile(ArrayList<Object> array)
   {
      
   }
   
   ////////// Load X From File //////////
   public ArrayList<User> loadUsersFromFile(String filePath)
   {
      return new ArrayList<User>();
   }
   
   public ArrayList<Room> loadRoomsFromFile(String filePath)
   {
      return  new ArrayList<Room>();
   }
   
   public ArrayList<Booking> loadBookingsFromFile(String filePath)
   {
      return new ArrayList<Booking>();
   }
}