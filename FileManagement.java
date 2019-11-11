import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class FileManagement
{

      // Attributes
   
   private String filePath;
      
      // Constructors
      
   public FileManagement () 
   {
   
   }
   
   public FileManagement ( String filePath ) 
   {
      this.filePath = filePath;
   }
   
      // Methods
      
   public void appendToFile ( String log ) // Appends a given line to the bottom of the logs.txt file
               throws IOException
   {
      File file = new File ( filePath + "/logs.txt" );
      FileWriter fw = new FileWriter ( file, true );
      PrintWriter out = new PrintWriter ( fw );
      out.println ( log );
      out.flush();
      out.close();
   }
      
      // Loaders
      
   /*
      Loaders return an array containing the objects of all the data that has been saved in a specific file.
      There is a loadFileName() method for all the files that are being used.
         - In the case of the bookings, they can be either active or archived and the loadBookings
           method takes in a boolean parameter 'isArchived' to help decide where to load the array from.
   */
     
      // Rooms
   public ArrayList<Room> loadRooms () 
                           throws FileNotFoundException 
   {
      File file = new File ( filePath + "/rooms.txt" );
      Room room = new Room();
      ArrayList<Room> array = new ArrayList<Room>();
      Scanner in = new Scanner ( file );
      
      while ( in.hasNext () ) 
      {  
         /*
            room.setRoomID (in.nextInt());
            room.setFloor ( in.nextInt());
            room.setBeds ( in.nextInt());
            room.setPrice ( in.nextInt()); 
         */
         room.setID (in.next());
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
      
      while ( in.hasNext () ) 
      {
         guest.setID (in.next());
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
      
      while ( in.hasNext () ) 
      {
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
         booking.setBookingID ( in.next() );
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
      PrintStream out = new PrintStream ( file );
      for ( int i = 0; i < array.size(); i ++ ) 
      {
         Room room = array.get(i);
         out.println ( room.fileFormatString() );
      }
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
}