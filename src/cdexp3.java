import java.util.*;
public class cdexp3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of productions : ");
        int n = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the grammar as E ->. E / a");
        for(int i = 0 ; i  < n ; i++){
            String prod = sc.nextLine().trim();
            elim(prod);
        }
    }
    public static void elim(String prod){
        String produ[] = prod.split("->");
        char nt = produ[0].charAt(0);
        String choices[] = produ[1].split("/");
        if(choices[0].startsWith(""+nt)){
            String beta = choices[0].substring(1);
            System.out.println();

        }
    }
}
