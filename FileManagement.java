// Placeholder class

import java.util.ArrayList;

public class FileManagement
{
   public void appendToFile(String logMessage)
   {
      System.out.println(logMessage);
   }
   
   public void saveToFile(ArrayList<Object> array)
   {
      
   }
   
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