import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        String s = br.readLine();
        if (s == null || s.trim().isEmpty()) return;
        
        s = s.trim();
        int n = s.length();
        
        // Print the universal 3-move sequence
        out.println(3);
        out.println("L 2");
        out.println("R 2");
        out.println("R " + (2 * n - 1));
        
        out.flush();
    }
}