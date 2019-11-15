// The controller than controls interaction and information transfer inbetween classes

import java.util.ArrayList;
import java.util.Properties;
import java.io.FileInputStream;

// If you wish to print out logs, add sysout in FileManagement

public class MainFrame // MF or motherFucker for short
{
   private ArrayList<Booking> bookingList;
   private ArrayList<Booking> archivedBookingList;
   private ArrayList<Room> roomList;
   private ArrayList<Guest> guestList;
   private ArrayList<Staff> staffList;
   
   private FileManagement file;
   private boolean printLogsToConsole;
   private Properties config;
   private boolean isInitiatedProperly;
   private String appTitle;

   public MainFrame(boolean printLogs)
   {  
      this.printLogsToConsole = printLogs;
   
      file = new FileManagement(this, "Logs");
      createLog("New MainFrame has been created", Log.Type.INFO);
   }    
   
   public MainFrame()
   {  
      this.printLogsToConsole = false;
   
      file = new FileManagement(this, "Logs");
      createLog("New MainFrame has been created", Log.Type.INFO);
   } 
   
   public void init()
   {
      try
      {  
         ////////// Init MainFrame //////////
         createLog("MainFrame Init started", Log.Type.INFO);
         
         ////////// Load Config //////////
         createLog("Loading config", Log.Type.INFO);
         config = new Properties();
         config.load(new FileInputStream("config.properties"));
         createLog("Config loaded", Log.Type.INFO);
         this.appTitle = config.getProperty("appTitle", "YEET");
         
         file.setFilePaths(config);
         
         ////////// Get config and init arrays //////////
         // get config with filepaths etc
         
         // load ALL arrays from file
         Information info = new Information(true, true, true, true, true);
         info = file.loadData(info);
         
         bookingList = info.bookingList;
         archivedBookingList = info.archivedBookingList;
         roomList = info.roomList;
         guestList = info.guestList;
         staffList = info.staffList;
         
         createLog("ArrayList setup complete", Log.Type.INFO);
         ////////// Run CLI/UI/whatever we called it //////////
         // create UI object and run UI
         createLog("MainFrame init has completed successfully", Log.Type.INFO);
         playMusic();
         this.isInitiatedProperly = true;
      }
      catch (Exception e)
      {
         createLog(e, Log.Type.ERROR);
         createLog("MainFrame init has NOT completed successfully", Log.Type.WARNING);
         this.isInitiatedProperly = false;
      }
      
   }
   
   ////////// UI API //////////
   
   // Returns a valid user for a given login. If none is found, returns null;
   public User validateLogin(String phoneNumber, String password)
   {  
      try
      {
         for(int i = 0; i < staffList.size(); i++)
         {
            if(staffList.get(i).getPhoneNumber().equals(phoneNumber) && staffList.get(i).getPassword().equals(password))
            {
               createLog("Staff Member " + staffList.get(i).getID() + " has logged in", Log.Type.INFO);
               return staffList.get(i);
            }
         }
         
         for(int i = 0; i < guestList.size(); i++)
         {
            if(guestList.get(i).getPhoneNumber().equals(phoneNumber) && guestList.get(i).getPassword().equals(password))
            {
               createLog("Guest " + guestList.get(i).getID() + " has logged in", Log.Type.INFO);
               return guestList.get(i);
            }
         }
      }
      catch (Exception e) {  createLog(e, Log.Type.ERROR); } 
      createLog("Failed Login attempt, phone Number: " + phoneNumber + " , password: " + password, Log.Type.INFO);
      return null;   // If the login info is invalid return null
   }
   
   public void createBooking(int roomID, int userID, int startDate, int endDate, int roomPrice, boolean hasInternet)
   {
      try
      {
         int bookingID = 1;
         bookingList.add(new Booking(bookingID, roomID, Integer.toString(userID), startDate, endDate, roomPrice, hasInternet));
         
         file.saveData(new Information(bookingList, null, null, null, null));
         createLog("New Booking created, id: " + bookingList.get(bookingList.size()-1).getBookingID() + " by " + userID, Log.Type.INFO);
      }
      catch (Exception e) {  createLog(e, Log.Type.ERROR); }
   }
   
   public void replaceBooking(int bookingID, Booking newBooking)
   {
      try
      {
         for(int i = 0; i < bookingList.size(); i++)
         {
            if(bookingList.get(i).getBookingID() == bookingID)
            {
               bookingList.set(i, newBooking);
               file.saveData(new Information(bookingList, null, null, null, null));
               createLog("Booking " + bookingID + "has been modified", Log.Type.INFO);
               break;
            }
         }
      }
      catch (Exception e) {  createLog(e, Log.Type.ERROR); }
   }
   
   public ArrayList<Booking> getUsersBookings(int userID)
   {
      ArrayList<Booking> userBookings = new ArrayList<Booking>();
      try
      {
         for(int i = 0; i < bookingList.size(); i++)
         {
            if(bookingList.get(i).getUserID().equals(userID))
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
               file.saveData(new Information(null, null, roomList, null, null));
               createLog("Room " + roomID + "has changed status to : requires cleaning", Log.Type.INFO);
               break;
            }
         }
      }
      catch (Exception e) {  createLog(e, Log.Type.ERROR); } 
   }
   
   ////////// User management //////////
   // contact interface : populate array
   public void setGuestList(ArrayList<Guest> guestList)
   {
       this.guestList = guestList;
       file.saveData(new Information(null, null, null, guestList, null));
       createLog("Guest List modified and saved", Log.Type.INFO);
   }
   
   public ArrayList<Guest> getGuestList()
   {
      return guestList;
   }
   
   public void setStaffList(ArrayList<Staff> staffList)
   {
       this.guestList = guestList;
       file.saveData(new Information(null, null, null, null, staffList));
       createLog("Staff List modified and saved", Log.Type.INFO);
   }
   
   public ArrayList<Staff> getStaffList()
   {
      return staffList;
   }
   
   ////////// Room management //////////
   // contact interface : populate array
   public void setRoomList(ArrayList<Room> roomList)
   {
       this.roomList = roomList;
       file.saveData(new Information(null, null, roomList, null, null));
       createLog("RoomList modified and saved", Log.Type.INFO);
   }
   
   public ArrayList<Room> getRoomList()
   {
      return roomList;
   }
   
   ////////// Booking management //////////
   // contact interface : populate array
   public void setBookingList(ArrayList<Booking> bookingList)
   {
      this.bookingList = bookingList;
      bookingList = file.loadData(new Information(true, false, false, false, false)).bookingList;
      createLog("Booking List modified and saved", Log.Type.INFO);
   }
   
   public ArrayList<Booking> getBookingList()
   {
      return bookingList;
   }
   
   ////////// Config //////////
   // read Config
   // assign config values to stuff
   
   ////////// Logging //////////
   public void createLog(String message, Log.Type logType)
   {
      file.appendToFile((new Log(message, logType)).toString(), this.printLogsToConsole);
   }
   public void createLog(Exception e, Log.Type logType)
   {
      file.appendToFile(new Log(e, logType).toString(), this.printLogsToConsole);
   }
   
   ////////// testing purpose code //////////
//    public void addUser(User user)
//    {
//       userList.add(user);
//    }
   
   ////////// User Interface //////////
   public void openCLI()
   {  
      LoginUI loginUI = new LoginUI(this.appTitle, this);
      loginUI.display();
      
      Staff staffMember = null;
      Guest guest = null;
      StaffUI staffUI;
      GuestUI guestUI;
      
      createLog("Moving to next UI", Log.Type.INFO);
      try
      {
         if( loginUI.getStaff() != null)
         {
            createLog("Creating StaffUI", Log.Type.INFO);
            staffMember = loginUI.getStaff();
            
            staffUI = new StaffUI(staffMember, this.appTitle, this);
            staffUI.setMFRef(this);
            createLog("StaffUi created", Log.Type.INFO);
            staffUI.display();
         }
         else if ( loginUI.getGuest() != null)
         {
            createLog("Creating GuestUI", Log.Type.INFO);
            guest = loginUI.getGuest();
            
            guestUI = new GuestUI(guest, this.appTitle, this);
            createLog("GuestUi created", Log.Type.INFO);
            guestUI.display();
         }
         else
         {
            createLog("No UI openable OR User has exited the application", Log.Type.WARNING);
         }
      }
      catch (Exception e)
      {
         createLog(e, Log.Type.ERROR);
         createLog("User UI failed to create", Log.Type.WARNING);
      }
   }
   
   ////////// Array Element Removal //////////
   
   public void removeBooking(int bookingID)
   {
      for (int i = 0 ; i < bookingList.size(); i++)
      {
         if (bookingList.get(i).getBookingID() == bookingID)
         {
            bookingList.remove(i);
            file.saveData(new Information(bookingList, null, null, null, null));
            createLog("Booking " + bookingID + "has been removed", Log.Type.INFO);
            break;
         }
      }
   }
   
   public void archiveBooking(int bookingID)
   {
      for (int i = 0 ; i < bookingList.size(); i++)
      {
         if (bookingList.get(i).getBookingID() == bookingID)
         {
            archivedBookingList.add(bookingList.get(i));
            bookingList.remove(i);
            file.saveData(new Information(bookingList, archivedBookingList, null, null, null));
            createLog("Booking " + bookingID + "has been archived", Log.Type.INFO);
            break;
         }
      }
   }
   
   ////////// Extra functionality //////////
   
   public void playMusic()
   {
      try
      {
         Musik yes = new Musik(config.getProperty("musicFile"));
         yes.play();
         createLog("Playing " + config.getProperty("musicFile"), Log.Type.INFO);
      }
      catch (Exception e)
      {
         createLog("Unable to play Music", Log.Type.WARNING);
         createLog(e, Log.Type.ERROR);
      }
   }
   
   ////////// Getters and Setter //////////
   
   public boolean getInitStatus()
   {
      return this.isInitiatedProperly;
   }
}