import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            String a = br.readLine();
            String b = br.readLine();

            List<Integer> diffIndices = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    diffIndices.add(i);
                }
            }

            int cnt = diffIndices.size();

            if (cnt % 2 != 0) {
                System.out.println(-1);
                continue;
            }

            if (cnt == 0) {
                System.out.println(0);
            } else if (cnt > 2) {
                // Since x >= y, we just pair them using y cost
                System.out.println((long) (cnt / 2) * y);
            } else {
                // cnt is exactly 2
                int i = diffIndices.get(0);
                int j = diffIndices.get(1);

                if (i + 1 == j) {
                    // Adjacent bits
                    System.out.println(Math.min(x, 2 * y));
                } else {
                    // Non-adjacent bits
                    System.out.println(y);
                }
            }
        }
    }
}