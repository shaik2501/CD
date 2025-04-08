import java.util.Scanner;

class Rule{
    String left;
    String right;
    Rule(String left,String right){
        this.left = left;
        this.right = right;
    }

}
public class cdexp4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       String input , stack = "";

        System.out.println("Enter no of prod rules : ");
        int n = sc.nextInt();
        sc.nextLine();
        Rule[] r = new Rule[n];
        for(int i = 0 ; i < n ; i++){
            String[] temp = sc.nextLine().split("->");
            r[i] = new Rule(temp[0],temp[1]);
        }
        int i = 0;
        input = sc.nextLine();

        while(true){
            if(i < input.length()){
                char ch = input.charAt(i);
                stack+=ch;
                i++;
                System.out.println("STACK \t");
                System.out.println(input.substring(i) + "\t\tShift " + ch);

            }
            for(int j = 0 ; j < n ; j++){
                int sub = stack.indexOf(r[j].right);
                if(sub != -1){
                    stack = stack.substring(0 , sub) + r[j].left;
                    System.out.println(input.substring(i) + "\t\t Reduce " + r[j].left + "-> " + r[j].right);
                    j = -1;
                }
            }
        }

    }
}
