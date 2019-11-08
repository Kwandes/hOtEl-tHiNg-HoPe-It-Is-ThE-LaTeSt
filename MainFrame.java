import java.util.ArrayList;

// If you wish to print out logs, add sysout in FileManagement

public class MainFrame // MF or motherFucker for short
{
   private ArrayList<User> userList;
   private ArrayList<Room> roomList;
   private ArrayList<Booking> bookingList;
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
   
   public void createLog(String message, Log.Type logType)
   {
      file.appendToFile((new Log(message, logType)).toString());
   }
   public void createLog(Exception e, Log.Type logType)
   {
      file.appendToFile(new Log(e, logType).toString());
   }
}