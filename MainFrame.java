import java.util.ArrayList;

// If you wish to print out logs, add sysout in FileManagement

public class MainFrame // MF or motherFucker for short
{
   private ArrayList<User> userList;
   private ArrayList<Room> roomList;
   private ArrayList<Booking> bookingList;
   private ArrayList<Booking> archivedBookingList;
   private ArrayList<Config> configData;
   private FileManagement file;

   public void init()
   {
      try
      {  
         ////////// Init Log System and Log it //////////
         file = new FileManagement();
         createLog("Test message", Log.Type.INFO);
         ////////// Get config and init arrays //////////
         // get config with filepaths etc
         // populate userList
         //bookingList = new ArrayList<Booking>();
         // populate roomList
         // populate bookingList;
         ArrayList<String> array = new ArrayList<String>();
         //array.get(1);   // Example invalid array for exception logging debug
         createLog("ArrayList setup complete", Log.Type.INFO);
         ////////// Run CLI/UI/whatever we called it //////////
         // create UI object and run UI
      }
      catch (Exception e)
      {
         //file.appendToFIle(new Log(e, Log.Type.ERROR));
         createLog(e, Log.Type.ERROR);
      }
      createLog("Init ok", Log.Type.INFO);
   }
   
   ////////// UI API //////////
   
   // Returns a valid user for a given login. If none is found, returns null;
   public User validateLogin(String phoneNumber, String password)
   {
      for(int i = 0; i < userList.size(); i++)
      {
         if(userList.get(i).getPhoneNumber().equals(phoneNumber) && userList.get(i).getPassword().equals(password))
         {
            return userList.get(i);
         }
      }
      return null;   // If the login info is invalid return null
   }
   
   public ArrayList<Room> getAvailableRooms()
   {
      ArrayList<Room> availableRooms = new ArrayList<Room>();
      
      for(int i = 0; i < roomList.size(); i++)
      {
         if(!roomList.get(i).isBooked())
         {
            availableRooms.add(roomList.get(i));
         }
      }      
      return availableRooms;
   }
   
   public ArrayList<Room> getAvailableRooms(int startDate, int endDate)
   {
      ArrayList<Room> availableRooms = new ArrayList<Room>();
      
      for(int i = 0; i < roomList.size(); i++)
      {
         if(!roomList.get(i).isBooked())
         {
            for(int j = 0; i < roomList.size(); j++)
            {
               if(!bookingList.get(j).isBookable(roomList.get(i).getRoomID(), startDate, endDate))
               {
                  availableRooms.add(roomList.get(i));
               }
            }
         }
      }
      return availableRooms;  // If no rooms found, return null;
   }
   
   public Booking bookRoom(int roomID, int userID, int startDate, int endDate)
   {
      bookingList.add(new Booking(roomID, userID, startDate, endDate));
      //file.saveBookingToFile(bookingList);
      file.saveBookingToFile(bookingList);
      return bookingList.get(bookingList.size()-1);
   }
   
   public Booking modifyBooking(int bookingID, int startDate, int endDate)
   {
      for(int i = 0; i < bookingList.size(); i++)
      {
         if(bookingList.get(i).getBookingID() == bookingID)
         {
            bookingList.get(i).setStartDate(startDate);
            bookingList.get(i).setEndDate(endDate);
            return bookingList.get(i);
         }
      }
      return null;
   }   
   
   public boolean removeBooking(int bookingID)
   {
      for(int i = 0; i < bookingList.size(); i++)
      {
         if(bookingList.get(i).getBookingID() == bookingID)
         {
            archivedBookingList.add(bookingList.get(i));
            bookingList.remove(i);
            return true;
         }
      }
      return false;
   }
   
   public ArrayList<Booking> getUsersBookings(int userID)
   {
      ArrayList<Booking> userBookings = new ArrayList<Booking>();
      
      for(int i = 0; i < bookingList.size(); i++)
      {
         if(bookingList.get(i).getUserID() == userID)
         {
            userBookings.add(bookingList.get(i));
         }
      }      
      return userBookings;
   }   
   
   ////////// User management //////////
   // contact interface : populate array
   public void setUserList()
   {
      userList = file.loadUsersFromFile("userList path");
   }
   
   public ArrayList<User> getUserList()
   {
      return userList;
   }
   
   ////////// Room management //////////
   // contact interface : populate array
   public void setRoomList()
   {
      roomList = file.loadRoomsFromFile("userList path");
   }
   
   public ArrayList<Room> getRoomList()
   {
      return roomList;
   }
   
   ////////// Booking management //////////
   // contact interface : populate array
   public void steBookingList()
   {
      bookingList = file.loadBookingsFromFile("userList path");
   }
   
   public ArrayList<Booking> getBookingList()
   {
      return bookingList;
   }
   
   ////////// Overall Management //////////
   // Doesn't work, I wanna make it work
   public void saveToFile(ArrayList<Object> array)
   {
      file.saveToFile(array);
   }
   
   ////////// Config //////////
   // read Config
   // assign config values to stuff
   
   ////////// Logging //////////
   public void createLog(String message, Log.Type logType)
   {
      file.appendToFile((new Log(message, logType)).toString());
   }
   public void createLog(Exception e, Log.Type logType)
   {
      file.appendToFile(new Log(e, logType).toString());
   }
}