import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Fast I/O is helpful for Codeforces
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            long[] a = new long[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(st.nextToken());
            }

            // Sorting is O(n log n)
            Arrays.sort(a);

            long maxWeight = 0;

            // Strategy 1: Maximize with the largest element
            // We iterate from index 0 to n-2, treating i and i+1 as a pair
            for (int i = 0; i < n - 2; i++) {
                // Formula derived: (a[n-1] - a[i]) + (a[i+1] - a[i])
                maxWeight = Math.max(maxWeight, a[n - 1] - a[i] + a[i + 1] - a[i]);
            }

            // Strategy 2: Maximize with the smallest element
            // We iterate from index 2 to n-1, treating i and i-1 as a pair
            for (int i = 2; i < n; i++) {
                // Formula derived: (a[i] - a[0]) + (a[i] - a[i-1])
                maxWeight = Math.max(maxWeight, a[i] - a[0] + a[i] - a[i - 1]);
            }

            System.out.println(maxWeight);
        }
    }
}