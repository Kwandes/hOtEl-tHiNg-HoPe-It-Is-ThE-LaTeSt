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

   public MainFrame()
   {
      file = new FileManagement();
      createLog("New MainFrame has been created", Log.Type.INFO);
   } 
   
   public void init()
   {
      try
      {  
         ////////// Init Log System and Log it //////////
         createLog("MainFrame Init started", Log.Type.INFO);
         ////////// Get config and init arrays //////////
         // get config with filepaths etc
         // populate userList
         userList = new ArrayList<User>();
         //bookingList = new ArrayList<Booking>();
         // populate roomList
         // populate bookingList;
         //ArrayList<String> array = new ArrayList<String>();
         //array.get(1);   // Example invalid array for exception logging debug
         createLog("ArrayList setup complete", Log.Type.INFO);
         ////////// Run CLI/UI/whatever we called it //////////
         // create UI object and run UI
         createLog("MainFrame init has completed successfully", Log.Type.INFO);
      }
      catch (Exception e)
      {
         createLog(e, Log.Type.ERROR);
         createLog("MainFrame init has NOT completed successfully", Log.Type.WARNING);
      }
      
   }
   
   ////////// UI API //////////
   
   // Returns a valid user for a given login. If none is found, returns null;
   public User validateLogin(String phoneNumber, String password)
   {  
      try
      {
         for(int i = 0; i < userList.size(); i++)
         {
            if(userList.get(i).getPhoneNumber().equals(phoneNumber) && userList.get(i).getPassword().equals(password))
            {
               createLog("User " + userList.get(i).getUserID() + " has logged in", Log.Type.INFO);
               return userList.get(i);
            }
         }
      }
      catch (Exception e) {  createLog(e, Log.Type.ERROR); } 
      return null;   // If the login info is invalid return null
   }
   
   public ArrayList<Room> getAvailableRooms()
   {
      ArrayList<Room> availableRooms = new ArrayList<Room>();
      try
      {
         for(int i = 0; i < roomList.size(); i++)
         {
            if(!roomList.get(i).isBooked())
            {
               availableRooms.add(roomList.get(i));
            }
         } 
         createLog("No available rooms found", Log.Type.WARNING);
      }
      catch (Exception e) {  createLog(e, Log.Type.ERROR); }     
      return availableRooms;  // If no rooms found it will be null
   }
   
   public ArrayList<Room> getAvailableRooms(int startDate, int endDate)
   {
      ArrayList<Room> availableRooms = new ArrayList<Room>();
      try
         {
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
      }
      catch (Exception e) {  createLog(e, Log.Type.ERROR); }
      
      createLog("No available rooms found", Log.Type.WARNING);
      return availableRooms;  // If no rooms found it will be null
   }
   
   public Booking bookRoom(int roomID, int userID, int startDate, int endDate)
   {
      try
      {
         bookingList.add(new Booking(roomID, userID, startDate, endDate));
         //file.saveBookingToFile(bookingList);
         file.saveBookingToFile(bookingList);
         createLog("New Booking created, id: " + bookingList.get(bookingList.size()-1) + " by " + userID, Log.Type.INFO);
         return bookingList.get(bookingList.size()-1);
      }
      catch (Exception e) {  createLog(e, Log.Type.ERROR); }
      return null;
   }
   
   public Booking modifyBooking(int bookingID, int startDate, int endDate)
   {
      try
      {
         for(int i = 0; i < bookingList.size(); i++)
         {
            if(bookingList.get(i).getBookingID() == bookingID)
            {
               bookingList.get(i).setStartDate(startDate);
               bookingList.get(i).setEndDate(endDate);
               createLog("Booking " + bookingID + "has been modified", Log.Type.INFO);
               return bookingList.get(i);
            }
         }
      }
      catch (Exception e) {  createLog(e, Log.Type.ERROR); }
      return null;
   }   
   
   public boolean removeBooking(int bookingID)
   {
      try
      {
         for(int i = 0; i < bookingList.size(); i++)
         {
            if(bookingList.get(i).getBookingID() == bookingID)
            {
               archivedBookingList.add(bookingList.get(i));
               bookingList.remove(i);
               createLog("Booking " + bookingID + "has been removed", Log.Type.INFO);
               return true;
            }
         }
      }
      catch (Exception e) {  createLog(e, Log.Type.ERROR); }
      return false;
   }
   
   public ArrayList<Booking> getUsersBookings(int userID)
   {
      ArrayList<Booking> userBookings = new ArrayList<Booking>();
      try
      {
         for(int i = 0; i < bookingList.size(); i++)
         {
            if(bookingList.get(i).getUserID() == userID)
            {
               userBookings.add(bookingList.get(i));
            }
         }
      }
      catch (Exception e) {  createLog(e, Log.Type.ERROR); }   
      return userBookings;
   }   
   
   ////////// Room management //////////
   
   public void requestRoomCleaning(int roomID)
   {
      try
      {
         for(int i = 0; i < roomList.size(); i++)
         {
            if(roomList.get(i).getRoomID() == roomID)
            {
               roomList.get(i).setRequiresCleaning(true);
               file.saveRoomToFile(roomList);
               createLog("Room " + roomID + "has changed status to : requires cleaning", Log.Type.INFO);
            }
         }
      }
      catch (Exception e) {  createLog(e, Log.Type.ERROR); } 
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
   
   ////////// testing purpose code //////////
   public void addUser(User user)
   {
      userList.add(user);
   }
}