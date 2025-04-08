import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class cdxp2 {
    public static  boolean isiden(String str){
        if(Character.isDigit(str.charAt(0))){
            return false;
        }else{
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> key = new ArrayList<>(Arrays.asList("int","float" , "char" , "if"));
        ArrayList<String> op = new ArrayList<>(Arrays.asList("--","++" , "-" , "+"));
        ArrayList<String> del = new ArrayList<>(Arrays.asList("[","]" , "{" , "}"));

        System.out.println("Enter input ");
        String inp = sc.nextLine();
        String[] a = inp.split(" ");
        int len = a.length;
        String[] ans = new String[len];
        for(int i = 0 ; i  < len ; i++){
            if(key.contains(a[i])){
                ans[i] = "Key";
            } else if(op.contains(a[i])){
                ans[i] = "op";
            }else if(del.contains(a[i])){
                ans[i] = "del";
            } else if (isiden(a[i])) {
                ans[i] = "ident";

            }
        }
        for(int i = 0 ; i  < ans.length ; i++){
            System.out.println(ans[i]);
        }


    }
}
