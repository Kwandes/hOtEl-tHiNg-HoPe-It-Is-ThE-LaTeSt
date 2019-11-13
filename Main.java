// Main, does nothing but call MF or run tests

public class Main
{
   public static void main(String [] args)
   {
//       UnitTests test = new UnitTests();
//       test.runTests(false);
   
      MainFrame hotel = new MainFrame(true);
      hotel.init();
      if(hotel.getInitStatus())
      {
         hotel.yeet();
      }
   }
}