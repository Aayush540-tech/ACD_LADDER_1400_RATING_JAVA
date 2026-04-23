import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        int[] a = new int[n];
        int[] b = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) b[i] = Integer.parseInt(st.nextToken());
        
        // 1. Map where each value is located in array B
        int[] posB = new int[n + 1];
        for (int i = 0; i < n; i++) {
            posB[b[i]] = i;
        }
        
        // 2. Count frequencies of required shifts
        int[] shiftFreq = new int[n];
        for (int i = 0; i < n; i++) {
            int val = a[i];
            int posInA = i;
            int posInB = posB[val];
            
            // Formula for cyclic distance: (A - B + N) % N
            int distance = (posInA - posInB + n) % n;
            shiftFreq[distance]++;
        }
        
        // 3. Find the maximum matching for a single shift
        int maxMatching = 0;
        for (int count : shiftFreq) {
            maxMatching = Math.max(maxMatching, count);
        }
        
        System.out.println(maxMatching);
    }
}