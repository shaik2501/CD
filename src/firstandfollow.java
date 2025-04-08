import java.util.*;
public class firstandfollow {
    static Map<String , List<String >> gram = new HashMap<>();
    static Map<String , Set<String >> fir = new HashMap<>();
    static Map<String , Set<String >> fol = new HashMap<>();
    static String ss = "";
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        int n = sc.nextInt();sc.nextLine();
        for(int i = 0 ; i  < n ; i++){
            String par[] = sc.nextLine().split("=");
            String lhs = par[0].trim();
            String[] rhs = par[1].split("\\|");
            if(i == 0){
                ss = lhs;
            }
            gram.putIfAbsent(lhs,new ArrayList<>());
            for(String prod : rhs){
                gram.get(lhs).add(prod);
            }
        }
        for (String nonTerminal : gram.keySet()) {
            fir.put(nonTerminal, first(nonTerminal));
        }

        // Initialize follow set
        for (String nt : gram.keySet()) {
            fol.put(nt, new HashSet<>());
        }
        fol.get(ss).add("$");

        // Compute FOLLOW
        for (String nonTerminal : gram.keySet()) {
            follow(nonTerminal);
        }

        // Print results
        System.out.println("\nFIRST sets:");
        for (String nt : fir.keySet()) {
            System.out.println("FIRST(" + nt + ") = " + fir.get(nt));
        }

        System.out.println("\nFOLLOW sets:");
        for (String nt : fol.keySet()) {
            System.out.println("FOLLOW(" + nt + ") = " + fol.get(nt));
        }

    }

    public static Set<String> first(String sym){
        Set<String> res = new HashSet<>();
        if (!gram.containsKey(sym)){
            res.add(sym);
            return res;
        }

        for(String prod : gram.get(sym)){
            if(gram.containsKey("e")) {
                res.add("e");
                continue;
            }
            for(int i = 0 ; i  < prod.length() ; i++){
                String s = String.valueOf(prod.charAt(i));
                Set<String> nf = first(s);
                res.addAll(nf);
                if(!nf.contains("e")){
                    break;
                }
                if(i == prod.length() - 1){
                    res.add("e");
                }
            }

        }
        return res;
    }
    public static void follow(String sym) {
        for (String lhs : gram.keySet()) {
            for (String prod : gram.get(lhs)) {
                for (int i = 0; i < prod.length(); i++) {
                    String st = String.valueOf(prod.charAt(i));
                    if (st.equals(sym)) {
                        if (i + 1 < prod.length()) {
                            String s = String.valueOf(prod.charAt(i));
                            Set<String> nf = first(s);
                            fol.get(lhs).addAll(nf);
                            fol.remove("e");
                            if (nf.contains("e")) {
                                if (!lhs.equals(sym))
                                    follow(lhs);

                                fol.get(sym).addAll(fol.get(lhs));
                            }


                        } else {
                            if (!lhs.equals(sym))
                                follow(lhs);

                            fol.get(sym).addAll(fol.get(lhs));

                        }
                    }
                }
            }
        }
    }
}
