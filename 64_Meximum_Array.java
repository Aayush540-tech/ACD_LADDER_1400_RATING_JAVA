import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        
        if (!sc.hasNext()) return;
        int t = sc.nextInt();
        
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            int[] freq = new int[n + 2];
            
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
                if (a[i] <= n) {
                    freq[a[i]]++;
                }
            }
            
            int mex = 0;
            while (freq[mex] > 0) {
                mex++;
            }
            
            List<Integer> b = new ArrayList<>();
            boolean[] seen = new boolean[n + 2];
            int i = 0;
            
            while (i < n) {
                int targetMex = mex;
                b.add(targetMex);
                
                if (targetMex == 0) {
                    int val = a[i];
                    freq[val]--;
                    // If we exhaust a number smaller than current MEX, the global MEX drops
                    if (freq[val] == 0 && val < mex) {
                        mex = val;
                    }
                    i++;
                } else {
                    int seenCount = 0;
                    while (i < n && seenCount < targetMex) {
                        int val = a[i];
                        
                        // If we found a useful number we haven't seen in this prefix
                        if (val < targetMex && !seen[val]) {
                            seen[val] = true;
                            seenCount++;
                        }
                        
                        freq[val]--;
                        if (freq[val] == 0 && val < mex) {
                            mex = val;
                        }
                        i++;
                    }
                    
                    // Crucial: Clear ONLY the elements we touched in 'seen' array to keep it O(N)
                    for (int j = 0; j < targetMex; j++) {
                        seen[j] = false;
                    }
                }
            }
            
            out.println(b.size());
            for (int j = 0; j < b.size(); j++) {
                out.print(b.get(j) + (j == b.size() - 1 ? "" : " "));
            }
            out.println();
        }
        out.flush();
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        boolean hasNext() {
            while (st == null || !st.hasMoreElements()) {
                try { 
                    String line = br.readLine();
                    if (line == null) return false;
                    st = new StringTokenizer(line); 
                }
                catch (IOException e) { return false; }
            }
            return true;
        }
        String next() {
            if (!hasNext()) return null;
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
    }
}