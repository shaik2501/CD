import java.util.Scanner;

public class cd9 {
    static int top;
    static int stack[];
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        top = -1;
        n = sc.nextInt();
        stack = new int[n];
        int ch;
        do{
            ch = sc.nextInt();
            switch(ch){
                case 1 :
                    push(sc);
                    break;
                case 2:
                    pop();
                    break;
                case 3:
                    disp();
                    break;
                case 4 :
                    return;
            }
        }while(ch != 4);

    }
    public static void push(Scanner sc){
        if(top >= n - 1){
            System.out.println("Overflow");
        }else{
            int x = sc.nextInt();
            top++;
            stack[top] = x;
        }
    }
    public static void pop(){
        System.out.println(stack[top]);
        top--;
    }
    public static void disp(){
        for(int i = top ; i  >= 0 ; i--){
            System.out.println(stack[i]);
        }
    }
}
