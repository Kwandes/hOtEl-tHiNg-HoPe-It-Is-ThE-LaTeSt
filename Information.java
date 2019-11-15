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
}