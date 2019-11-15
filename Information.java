import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Information
{
      // Attributes 
   public ArrayList<Booking> bookingList = new ArrayList<Booking>();
   public ArrayList<Room> roomList = new ArrayList<Room>();
   public ArrayList<Guest> guestList = new ArrayList<Guest>();
   public ArrayList<Staff> staffList = new ArrayList<Staff>();
   private FileManagement fm;
   
   //private Property prop;
   
      // Constructors
   public Information ()
   {
      
   }
   
   public Information ( FileManagement fm )
   {
      this.fm = fm;
   }
   /*
   public Information ( FileManagement fm, Property prop )
   {
      this.fm = fm;
      this.prop = prop
   }*/
   
      // Methods
   
   public static void saveAll ( Information info, boolean isArchived )
                     throws FileNotFoundException
   {
      if ( info.bookingList != null ) 
      {
         info.fm.saveBookings ( info.bookingList, isArchived );
      } 
      if ( info.roomList != null ) 
      {
         info.fm.saveRooms ( info.roomList );
      } 
       if ( info.guestList != null ) 
      {
         info.fm.saveGuests ( info.guestList );
      } 
      if ( info.staffList != null ) 
      {
         info.fm.saveStaff ( info.staffList );
      } 
   }
   
   public static void loadAll ( Information info, boolean isArchived )
                     throws FileNotFoundException
   {
      info.bookingList = info.fm.loadBookings ( isArchived );
      info.roomList = info.fm.loadRooms ();
      info.guestList = info.fm.loadGuests ();
      info.staffList = info.fm.loadStaff ();
   }
}