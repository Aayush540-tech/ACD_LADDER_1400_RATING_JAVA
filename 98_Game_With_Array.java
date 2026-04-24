import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();

        // The Golden Condition: Is the sum large enough to leave a gap?
        if (s >= 2 * n) {
            System.out.println("YES");
            
            // Print N-1 ones
            for (int i = 0; i < n - 1; i++) {
                System.out.print("1 ");
            }
            
            // Print the last element (the remainder)
            System.out.println(s - (n - 1));
            
            // Pick a K that sits exactly in our 'gap'
            // Since Vasya can reach 1...N-1, N is a safe K
            System.out.println(n);
        } else {
            // Not enough 'room' to hide K from Vasya
            System.out.println("NO");
        }
    }
}