import java.util.HashMap;
import java.util.*;
public class cdexp5 {
    static Map<String, List<String>> grammar = new HashMap<>();
    static Map<String, Set<String>> first = new HashMap<>();
    static Map<String, Set<String>> follow = new HashMap<>();
    static String startSymbol = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of productions:");
        int n = sc.nextInt(); sc.nextLine();

        System.out.println("Enter productions (e.g., E=TA | ε):");
        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().split("=");
            String lhs = parts[0].trim();
            if (i == 0) startSymbol = lhs;
            String[] rhsParts = parts[1].split("\\|");
            grammar.putIfAbsent(lhs, new ArrayList<>());
            for (String prod : rhsParts) {
                grammar.get(lhs).add(prod.trim());
            }
        }

        // Compute FIRST
        for (String nonTerminal : grammar.keySet()) {
            first.put(nonTerminal, findFirst(nonTerminal));
        }

        // Initialize follow set
        for (String nt : grammar.keySet()) {
            follow.put(nt, new HashSet<>());
        }
        follow.get(startSymbol).add("$");

        // Compute FOLLOW
        for (String nonTerminal : grammar.keySet()) {
            findFollow(nonTerminal);
        }

        // Print results
        System.out.println("\nFIRST sets:");
        for (String nt : first.keySet()) {
            System.out.println("FIRST(" + nt + ") = " + first.get(nt));
        }

        System.out.println("\nFOLLOW sets:");
        for (String nt : follow.keySet()) {
            System.out.println("FOLLOW(" + nt + ") = " + follow.get(nt));
        }

        sc.close();
    }

    static Set<String> findFirst(String symbol) {
        Set<String> result = new HashSet<>();
        if (!grammar.containsKey(symbol)) {
            result.add(symbol); // Terminal
            return result;
        }

        for (String production : grammar.get(symbol)) {
            if (production.equals("ε")) {
                result.add("ε");
                continue;
            }
            for (int i = 0; i < production.length(); i++) {
                String sym = String.valueOf(production.charAt(i));
                Set<String> symFirst = findFirst(sym);
                result.addAll(symFirst);
                if (!symFirst.contains("ε")) break;
                if (i == production.length() - 1) result.add("ε");
            }
        }
        return result;
    }

    static void findFollow(String symbol) {
        for (String lhs : grammar.keySet()) {
            for (String production : grammar.get(lhs)) {
                for (int i = 0; i < production.length(); i++) {
                    if (String.valueOf(production.charAt(i)).equals(symbol)) {
                        if (i + 1 < production.length()) {
                            String next = String.valueOf(production.charAt(i + 1));
                            Set<String> nextFirst = findFirst(next);
                            follow.get(symbol).addAll(nextFirst);
                            follow.get(symbol).remove("ε");
                            if (nextFirst.contains("ε")) {
                                if (!lhs.equals(symbol)) findFollow(lhs);
                                follow.get(symbol).addAll(follow.get(lhs));
                            }
                        } else {
                            if (!lhs.equals(symbol)) findFollow(lhs);
                            follow.get(symbol).addAll(follow.get(lhs));
                        }
                    }
                }
            }
        }
    }
}
