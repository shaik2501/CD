import java.util.*;
public class cd6 {
    static Map<Character , List<String>>  mp = new HashMap<>();
    static String ip ;
    static int i = 0;
    public static boolean parse(char nt){
      int bt = i;
      for(String prod : mp.get(nt)){
          i = bt;
          boolean success = true;
          for(char ch : prod.toCharArray()){
              if(ch == '@'){
                  continue;
              } else if (Character.isUpperCase(ch)) {
                  success &= parse(ch);

              }else if(i < ip.length() && ip.charAt(i) == ch){
                  i++;
              }else{
                  success = false;
                  break;
              }
          }
          if(success){
              return true;
          }
      }
      return false;

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();sc.nextLine();

        for(int j = 0 ;  j <  n ; j++){
            String[] par = sc.nextLine().split("->");
            mp.put(par[0].charAt(0),Arrays.asList(par[1].split("\\|")));
        }
        System.out.println("String");
        ip = sc.next();

        System.out.println(parse('E') && i == ip.length() ? "Accept" : "Reject");




    }
}
