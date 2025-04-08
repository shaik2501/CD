import java.util.*;
public class cd10 {
    public static void generateIntermediateCode(String expression) {
        Stack<String> operator = new Stack<>();
        Stack<String> operand = new Stack<>();
        String token[] = expression.split(" ");
        int tc = 1;
        for(String tokens : token) {
            if (isOperator(tokens)) {
                operator.push(tokens);
            } else {
                operand.push(tokens);
            }
            if (operand.size() >= 2 && operator.size() >= 1) {
                String op1 = operand.pop();
                String op2 = operand.pop();
                String ops = operator.pop();
                String te = "t" + tc++;
                System.out.println(te + " = " + op2 + " " + ops + " " + op1);
                operand.push(te);
            }
        }



    }
    public static boolean isOperator(String token){
        return "+-*/".contains(token);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an arithmetic expression (e.g., a * b + c / d):");
        String expression = scanner.nextLine();


        System.out.println("Intermediate Code:");
        generateIntermediateCode(expression);
    }
}
