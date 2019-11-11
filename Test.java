public class Test {


   public static void main(String[] args)
   {
   Room.setBasePrice(100);
   Room.setPricePerBed(200);
   Room.setFloorMultiplier(1.05);
   Room test1 = new Room(304, 2);
   Room test2 = new Room(305, 3);
   //test1.setBasePrice(200);
   System.out.println(test1.getPrice());
   System.out.println(test2.getPrice());
   
   
   Room.setBasePrice(100);
  //test1.countPrice();
   
   //System.out.println(test1.getPrice());
   //System.out.println(test2.getPrice());
   
   System.out.println(test1 + "\n\n" + test2);
   System.out.println(test1.fileFortmatString());
   
   
   
   
   }


}