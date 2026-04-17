import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        
        if (!sc.hasNext()) return;
        int n = sc.nextInt();
        
        String[] strings = new String[n];
        long[][] cnt = new long[6][46];
        
        for (int i = 0; i < n; i++) {
            strings[i] = sc.next();
            int len = strings[i].length();
            int sum = 0;
            for (int j = 0; j < len; j++) {
                sum += strings[i].charAt(j) - '0';
            }
            cnt[len][sum]++;
        }
        
        long ans = 0;
        
        for (String s : strings) {
            int L = s.length();
            
            for (int k = 1; k <= L; k++) {
                if ((L + k) % 2 == 0) {
                    int mid = (L + k) / 2;
                    
                    // Case 1: 's' is on the left
                    int leftSum = 0, rightSum = 0;
                    for (int i = 0; i < mid; i++) leftSum += s.charAt(i) - '0';
                    for (int i = mid; i < L; i++) rightSum += s.charAt(i) - '0';
                    
                    int reqSum = leftSum - rightSum;
                    if (reqSum >= 0 && reqSum <= 45) {
                        ans += cnt[k][reqSum];
                    }
                    
                    // Case 2: 's' is on the right
                    if (k < L) {
                        int leftSum2 = 0, rightSum2 = 0;
                        for (int i = 0; i < L - mid; i++) leftSum2 += s.charAt(i) - '0';
                        for (int i = L - mid; i < L; i++) rightSum2 += s.charAt(i) - '0';
                        
                        int reqSum2 = rightSum2 - leftSum2;
                        if (reqSum2 >= 0 && reqSum2 <= 45) {
                            ans += cnt[k][reqSum2];
                        }
                    }
                }
            }
        }
        
        out.println(ans);
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