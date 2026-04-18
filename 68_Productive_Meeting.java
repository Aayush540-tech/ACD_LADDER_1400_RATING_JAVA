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
            
            // Max-Heap: Sorts by sociability in descending order
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
            
            for (int i = 1; i <= n; i++) {
                int val = sc.nextInt();
                if (val > 0) {
                    pq.add(new int[]{val, i});
                }
            }
            
            List<String> meetings = new ArrayList<>();
            
            while (pq.size() > 1) {
                int[] first = pq.poll();
                int[] second = pq.poll();
                
                meetings.add(first[1] + " " + second[1]);
                
                first[0]--;
                second[0]--;
                
                if (first[0] > 0) {
                    pq.add(first);
                }
                if (second[0] > 0) {
                    pq.add(second);
                }
            }
            
            out.println(meetings.size());
            for (String meeting : meetings) {
                out.println(meeting);
            }
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