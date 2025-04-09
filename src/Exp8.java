import java.util.*;

public class Exp8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter no. of terminals: ");
        int n = sc.nextInt();
        System.out.print("Enter terminals: ");
        char[] ter = sc.next().toCharArray();

        char[][] opt = new char[n][n];
        System.out.println("Enter table values:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(ter[i] + " " + ter[j] + " = ");
                opt[i][j] = sc.next().charAt(0);
            }
        }

        System.out.print("Enter input string: ");
        String input = sc.next() + "$";

        Stack<Character> stack = new Stack<>();
        stack.push('$');
        int i = 0;
        System.out.println("STACK		INPUT		ACTION");

        while (i < input.length()) {
            char a = stack.peek();
            char b = input.charAt(i);

            int row = new String(ter).indexOf(a);
            int col = new String(ter).indexOf(b);

            if (a == '$' && b == '$') {
                System.out.println("String is accepted.");
                break;
            }

            if (opt[row][col] == '<' || opt[row][col] == '=') {
                stack.push(b);
                i++;
                System.out.println(stack + "\t\t" + input.substring(i) + "\t\tShift " + b);
            } else if (opt[row][col] == '>') {
                stack.pop();
                System.out.println(stack + "\t\t" + input.substring(i) + "\t\tReduce");
            } else {
                System.out.println("String is not accepted.");
                break;
            }
        }
        sc.close();
    }
}