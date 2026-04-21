import java.io.*;
import java.util.*;

public class Main {
    // Fast I/O class for competitive programming
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        public FastReader() { 
            br = new BufferedReader(new InputStreamReader(System.in)); 
        }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try { st = new StringTokenizer(br.readLine()); }
                catch (IOException e) { e.printStackTrace(); }
            }
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
    }

    public static void main(String[] args) {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        
        int t = in.nextInt();
        
        while (t-- > 0) {
            int n = in.nextInt();
            char trump = in.next().charAt(0);
            
            // 1. Group cards by suit
            Map<Character, List<String>> suits = new HashMap<>();
            suits.put('C', new ArrayList<>());
            suits.put('D', new ArrayList<>());
            suits.put('H', new ArrayList<>());
            suits.put('S', new ArrayList<>());
            
            for (int i = 0; i < 2 * n; i++) {
                String card = in.next();
                suits.get(card.charAt(1)).add(card);
            }
            
            // 2. Sort each suit alphabetically (ranks '2'-'9' sort perfectly)
            for (List<String> list : suits.values()) {
                Collections.sort(list); 
            }
            
            List<String> ans = new ArrayList<>();
            boolean possible = true;
            char[] suitOrder = {'C', 'D', 'H', 'S'};
            
            // 3. Process non-trump suits
            for (char s : suitOrder) {
                if (s == trump) continue;
                
                List<String> currentSuit = suits.get(s);
                for (int i = 0; i < currentSuit.size() - 1; i += 2) {
                    ans.add(currentSuit.get(i) + " " + currentSuit.get(i+1));
                }
                
                // Rescue leftover card with a trump
                if (currentSuit.size() % 2 != 0) {
                    String leftover = currentSuit.get(currentSuit.size() - 1);
                    List<String> trumpSuit = suits.get(trump);
                    if (!trumpSuit.isEmpty()) {
                        String trumpCard = trumpSuit.remove(0);
                        ans.add(leftover + " " + trumpCard);
                    } else {
                        possible = false;
                        break;
                    }
                }
            }
            
            if (!possible) {
                out.println("IMPOSSIBLE");
                continue;
            }
            
            // 4. Pair remaining trumps
            List<String> trumpSuit = suits.get(trump);
            for (int i = 0; i < trumpSuit.size(); i += 2) {
                if (i + 1 < trumpSuit.size()) {
                    ans.add(trumpSuit.get(i) + " " + trumpSuit.get(i+1));
                } else {
                    possible = false;
                }
            }
            
            if (!possible) {
                out.println("IMPOSSIBLE");
            } else {
                for (String pair : ans) {
                    out.println(pair);
                }
            }
        }
        out.flush();
    }
}