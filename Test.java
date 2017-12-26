public class Test
{

   public static void main(String[] args)
   {

      SkipList S = new SkipList();

      S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");

      S.put("ABC", new Integer(123));
      S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");

      S.put("DEF", new Integer(123));
      S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");

      S.put("KLM", new Integer(123));
      S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");

      S.put("HIJ", new Integer(123));
      S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");

      S.put("GHJ", new Integer(123));
      S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");

      S.put("AAA", new Integer(123));
      S.printHorizontal();
      System.out.println("------");
//    S.printVertical();
//    System.out.println("======");


   }

}