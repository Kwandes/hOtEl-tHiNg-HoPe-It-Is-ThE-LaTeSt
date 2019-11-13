public class GuestUI extends CLI
{
   public GuestUI(User user, String title)
   {
      this.title = title;
      this.screenNumber = 0;
      this.loggedUser = user.getLastName();
      this.userAccessLevel = 0; // Cannot be more than 0 for security reasons
      this.seperator = "----------------------------------------------------------------------------------------------------"; // 100 dashes
      this.running = true;
   }
   
   public void display()
   {
      while(running)
      {
         switch(screenNumber)
         {
            // actual display
            // listen to inputs, show different screens depending on input
            // each "screen" has a specific screen Number
            // screens choosing example below
            case 1:
               mainMenu();
               break;
            case 99:
               exit();
               break;
         }
      }  
   }
   
   public void mainMenu()
   {
      this.screenNumber = 1;
   }
   
   public void exit()
   {
      this.running = false;
   }
}