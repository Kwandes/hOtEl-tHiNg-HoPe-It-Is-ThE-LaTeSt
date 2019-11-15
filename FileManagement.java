import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.Properties;

public class FileManagement
{
      // Attributes
   
   private String filePath;
   private MainFrame mf;
   private Information info;
      
      // Constructors
      
   public FileManagement (MainFrame mfRef) 
   {
      this.mf = mfRef;
   }
   
   public FileManagement (MainFrame mfRef, String filePath) 
   {
      this.filePath = filePath;
      this.mf = mfRef;
   }
   
      // Methods
      
   public void appendToFile ( String log ) // Appends a given line to the bottom of the logs.txt file
   {
      File file = new File ( filePath + "/logs.txt" );
      try
      {
         file.createNewFile();
         FileWriter fw = new FileWriter ( file, true );
         PrintWriter out = new PrintWriter ( fw );
         out.println ( log );
         out.flush();
         out.close();
      }
      catch (Exception e){ System.out.println(e);};
   }
   
   public void appendToFile ( String log, boolean outputToConsole ) // Appends a given line to the bottom of the logs.txt file
   {
      File file = new File ( filePath + "/logs.txt" );
      try
      {
         file.createNewFile();
         FileWriter fw = new FileWriter ( file, true );
         PrintWriter out = new PrintWriter ( fw );
         out.println ( log );
         out.flush();
         out.close();
      }
      catch (Exception e){ System.out.println(e);};
      
      if(outputToConsole) System.out.println(log);
   }
      // Loaders
      
   /*
      Loaders return an array containing the objects of all the data that has been saved in a specific file.
      There is a loadFileName() method for all the files that are being used.
         - In the case of the bookings, they can be either active or archived and the loadBookings
           method takes in a boolean parameter 'isArchived' to help decide where to load the array from.
   */
   
   public Information loadData(Information info)
   {
      try
      {
         if(info.loadBookings)
         {
            mf.createLog("Loading BookingList", Log.Type.INFO);
            info.bookingList = loadBookings(false);
         }
         else info.bookingList = null;
         
         if(info.loadArchive)
         {
            mf.createLog("Loading Archived Bookings", Log.Type.INFO);
            info.archivedBookingList = loadBookings(true);
         }
         else info.archivedBookingList = null;
         
         if(info.loadRooms)
         {
            mf.createLog("Loading RoomList", Log.Type.INFO);
            info.roomList = loadRooms();
         }
         else info.roomList = null;
         
         if(info.loadGuests)
         {
            mf.createLog("Loading GuestList", Log.Type.INFO);
            info.guestList = loadGuests();
         }
         else info.guestList = null;
         
         if(info.loadStaff)
         {
            mf.createLog("Loading StaffList", Log.Type.INFO);
            info.staffList = loadStaff();
         }
         else info.bookingList = null;
      }
      catch (Exception e)
      {
         mf.createLog("Loading Data not finished", Log.Type.WARNING);
         mf.createLog(e, Log.Type.ERROR);
      }
      
      return info;
   }
   
   public void saveData(Information info)
   {
      try
      {
         if(info.bookingList != null)
         {
            mf.createLog("Saving BookingList", Log.Type.INFO);
            saveBookings(info.bookingList, false);
         }
         
         if(info.archivedBookingList != null)
         {
            mf.createLog("Saving archived BookingList", Log.Type.INFO);
            saveBookings(info.archivedBookingList, true);
         }
         
         if(info.roomList != null)
         {
            mf.createLog("Saving RoomList", Log.Type.INFO);
            saveRooms(info.roomList);
         }
         
         if(info.loadGuests)
         {
            mf.createLog("Saving GuestList", Log.Type.INFO);
            saveGuests(info.guestList);
         }
         
         if(info.loadStaff)
         {
            mf.createLog("Saving StaffList", Log.Type.INFO);
            saveStaff(info.staffList);
         }
      }
      catch (Exception e)
      {
         mf.createLog(e, Log.Type.ERROR);
      }
   }
   
     
      // Rooms
   public ArrayList<Room> loadRooms () 
                           throws FileNotFoundException 
   {
      File file = new File ( filePath + "/rooms.txt" );
      File calFile = new File ( filePath + "/calendar.txt" );
      Room room = new Room();
      ArrayList<Room> array = new ArrayList<Room>();
      Scanner in = new Scanner ( file );
      Scanner calIn = new Scanner ( calFile );
      
      while ( in.hasNext () ) 
      {  
         room.setRoomID (in.nextInt());
         room.setFloor ( in.nextInt());
         room.setBeds ( in.nextInt());
         room.setPrice ( in.nextInt()); 
         int[] calendar = new int[365];
         for ( int i = 0; i < calendar.length; i ++ )
         {
            calendar[i] = calIn.nextInt();
         }
         room.setCalendar ( calendar );
         array.add ( room );
      }
            
      return array;
   }
      // Guests 
   public ArrayList<Guest> loadGuests () 
                           throws FileNotFoundException 
   {
      File file = new File ( filePath + "/guests.txt" );
      Guest guest = new Guest();
      ArrayList<Guest> array = new ArrayList<Guest>();
      Scanner in = new Scanner ( file );
      String[] address = new String[3];
      
      while ( in.hasNext () ) 
      {
         guest.setFirstName (in.next());
         guest.setLastName (in.next());
         guest.setCpr (in.next());
         guest.setType (in.next());
         address[0] = in.next();
         address[1] = in.next();
         address[2] = in.next();
         guest.setAddress ( address );
         guest.setPhoneNr ( in.next());
         guest.setPassword ( in.next());
         guest.setID ( in.next());
         guest.setAccessLevel ( in.nextInt());
         guest.setGuestDays ( in.nextInt());
         guest.setMoneySpent ( in.nextDouble());
         array.add ( guest );
      }
            
      return array;
   }
   
      // Staff
   public ArrayList<Staff> loadStaff () 
                           throws FileNotFoundException 
   {
      File file = new File ( filePath + "/staff.txt" );
      Staff staff = new Staff();
      ArrayList<Staff> array = new ArrayList<Staff>();
      Scanner in = new Scanner ( file );      
      String[] address = new String[3];
      
      while ( in.hasNext () ) 
      {
         staff.setFirstName (in.next());
         staff.setLastName (in.next());
         staff.setCpr (in.next());
         staff.setType (in.next());
         address[0] = in.next();
         address[1] = in.next();
         address[2] = in.next();
         staff.setAddress ( address );
         staff.setPhoneNr ( in.next());
         staff.setPassword ( in.next());
         staff.setID ( in.next());
         staff.setAccessLevel ( in.nextInt());
         staff.setHours ( in.nextInt());
         staff.setSalary ( in.nextDouble());
         staff.setVacation ( in.nextInt());
         staff.setID (in.next());
         array.add ( staff );
      }
            
      return array;
   }
   
      // Bookings - Archived & Active
   public ArrayList<Booking> loadBookings ( boolean isArchived ) 
                           throws FileNotFoundException 
   {
      File file;
      if ( isArchived )
      {
         file = new File ( filePath + "/archived_bookings.txt" );
      }
      else 
      {
         file = new File ( filePath + "/bookings.txt" );
      }
      Booking booking = new Booking();
      ArrayList<Booking> array = new ArrayList<Booking>();
      Scanner in = new Scanner ( file );
      
      while ( in.hasNext () ) 
      {
         booking.setBookingID ( in.nextInt() );
         booking.setUserID ( in.next() );
         booking.setRoomID ( in.nextInt() );
         booking.setIsExtended ( in.nextBoolean() );
         booking.setStartDate ( in.nextInt() );
         booking.setEndDate ( in.nextInt() );
         booking.setHasInternet ( in.nextBoolean() );
         booking.setPrice ( in.nextInt() );
         array.add ( booking );
      }
            
      return array;
   }   
     
      // Savers
      
   /*
      Savers print all the object information they contain in the corresponding file.
      There is a saveFileName() method for all the files that are being used.
         - In the case of the bookings, they can be either active or archived and the saveBookings
           method takes in a boolean parameter 'isArchived' to help decide where to save the contents of the array.
   */

      // Rooms      
   public void saveRooms ( ArrayList<Room> array ) 
                     throws FileNotFoundException 
   {
      File file = new File ( filePath + "/rooms.txt" );
      File calFile = new File ( filePath + "/calendar.txt");
      PrintStream out = new PrintStream ( file );
      PrintStream calOut = new PrintStream ( calFile );
      for ( int i = 0; i < array.size(); i ++ ) 
      {
         Room room = array.get(i);
         out.println ( room.fileFormatString() );
         int[] calendar = room.getCalendar();
         for ( int j = 0; j < calendar.length; j++ ) {
            calOut.print ( calendar[j] + " " ); 
         }
         calOut.println();
      }
      calOut.flush();
      calOut.close();
      out.flush();
      out.close();   
   }
   
      // Guests
   public void saveGuests ( ArrayList<Guest> array ) 
                     throws FileNotFoundException 
   {
      File file = new File ( filePath + "/guests.txt" );
      PrintStream out = new PrintStream ( file );
      for ( int i = 0; i < array.size(); i ++ ) 
      {
         Guest guest = array.get(i);
         out.println ( guest.fileFormatString() );
      }
      out.flush();
      out.close();   
   }
   
      // Staff
   public void saveStaff ( ArrayList<Staff> array ) 
                     throws FileNotFoundException 
   {
      File file = new File ( filePath + "/staff.txt" );
      PrintStream out = new PrintStream ( file );
      for ( int i = 0; i < array.size(); i ++ ) 
      {
         Staff staff = array.get(i);
         out.println ( staff.fileFormatString() );
      }
      out.flush();
      out.close();   
   }
   
      // Bookings - Archived & Active
   public void saveBookings ( ArrayList<Booking> array, boolean isArchived ) 
                     throws FileNotFoundException 
   {
      File file;
      if ( isArchived )
      {
         file = new File ( filePath + "/archived_bookings.txt" );
      }
      else 
      {
         file = new File ( filePath + "/bookings.txt" );
      }
      PrintStream out = new PrintStream ( file );
      for ( int i = 0; i < array.size(); i ++ ) 
      {
         Booking booking = array.get(i);
         out.println ( booking.fileFormatString() );
      }
      out.flush();
      out.close();   
   }


      // Getters
   
   public String getFilePath () 
   {
      return filePath;
   }
   
      // Setters
      
   public void setFilePath ( String filePath ) 
   {
      this.filePath = filePath;
   }
   
   // Do your path assigment here or something. This method is called from MF during Init
   public void setFilePaths(Properties config)
   {
      /*
      Example code:
      this.bookingListPath = config.getProperty("bookingListPath");
      this.archivedBookingListPath = config.getProperty("archivedBookingListPath", "files/archive.txt"); // second parameter is a default value in case the property is missing
      */
   }        
}