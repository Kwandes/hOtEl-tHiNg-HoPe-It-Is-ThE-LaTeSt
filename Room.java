// placeholder class
public class Room
{
   private boolean isBooked; // Whether the room is CURRENTLY available, aka for this night
   private int roomID;  // XY format, X = floor, Y =  room number. 203 - room 3 on 2nd floor
   private boolean requiresCleaning;
   
   public boolean isBooked()
   {
      return isBooked;
   }
   
   public int getRoomID()
   {
      return roomID;
   }
   
   public void setRequiresCleaning(boolean requires)
   {
      this.requiresCleaning = requires;
   }
}