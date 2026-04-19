import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        if (!sc.hasNextInt()) return;
        int m = sc.nextInt();
        int s = sc.nextInt();
        
        // Edge Case: Sum is 0
        if (s == 0) {
            if (m == 1) {
                System.out.println("0 0");
            } else {
                System.out.println("-1 -1");
            }
            return;
        }
        
        // Edge Case: Sum is too large
        if (s > 9 * m) {
            System.out.println("-1 -1");
            return;
        }
        
        // Build Maximum Number
        StringBuilder maxStr = new StringBuilder();
        int tempS = s;
        for (int i = 0; i < m; i++) {
            int digit = Math.min(9, tempS);
            maxStr.append(digit);
            tempS -= digit;
        }
        
        // Build Minimum Number
        int[] minNum = new int[m];
        minNum[0] = 1; // Reserve 1
        tempS = s - 1;
        
        for (int i = m - 1; i >= 0; i--) {
            int add = Math.min(9 - minNum[i], tempS);
            minNum[i] += add;
            tempS -= add;
        }
        
        StringBuilder minStr = new StringBuilder();
        for (int i = 0; i < m; i++) {
            minStr.append(minNum[i]);
        }
        
        System.out.println(minStr.toString() + " " + maxStr.toString());
    }
}