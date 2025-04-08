import java.util.*;

public class parth4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of productions:");
        int n = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the productions:");
        String prod = sc.nextLine();  // Just one line,  like: S-> Sa | Sb | Sc
        String[] parts = prod.split("->")[1].split("\\|");
        String prefix = commonPrefix(parts[0], parts[1]);
        System.out.println("S->" + prefix + "S'");
        System.out.print("S'->");
        for (int i = 0; i < parts.length; i++) {
            System.out.print(parts[i].substring(prefix.length()));
            if (i != parts.length - 1) System.out.print("|");
        }
        System.out.println();
    }

    static String commonPrefix(String a, String b) {
        int i = 0;
        while (i < a.length() && i < b.length() && a.charAt(i) == b.charAt(i)) i++;
        return a.substring(0, i);
    }
}

