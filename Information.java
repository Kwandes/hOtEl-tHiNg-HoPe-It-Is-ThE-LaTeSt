import java.util.ArrayList;

public class Information
{
   public ArrayList<Booking> bookingList;
   public ArrayList<Booking> archivedBookingList;
   public ArrayList<Room> roomList;
   public ArrayList<Guest> guestList;
   public ArrayList<Staff> staffList;
   
   public boolean loadBookings = false;
   public boolean loadArchive = false;
   public boolean loadRooms = false;
   public boolean loadGuests = false;
   public boolean loadStaff = false;
   
   // Constructor for saving data
   public Information( ArrayList<Booking> bookingList,
                            ArrayList<Booking> archivedBookingList,
                            ArrayList<Room> roomList,
                            ArrayList<Guest> guestList,
                            ArrayList<Staff> staffList )
   {
       this.bookingList = bookingList;
       this.archivedBookingList = archivedBookingList;
       this.roomList = roomList;
       this.guestList = guestList;
       this.staffList =staffList;
   }
   
   // Constructor for loading data from file
   public Information( boolean loadBookings,
                           boolean loadArchive,
                           boolean loadRooms,
                           boolean loadGuests,
                           boolean loadStaff )
   {
      this.loadBookings = loadBookings;
      this.loadArchive = loadArchive;
      this.loadRooms = loadRooms;
      this.loadGuests = loadGuests;
      this.loadStaff = loadStaff;
   }
}