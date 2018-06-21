import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

 
public class SortingPG {

    public static void main(String a[]){
        BufferedReader readResult = null;

        try {
            String sCurrentLine;
            int i = 0;
            int n = 37664;
            int[] arr = new int [n];
            PrintWriter writer = new PrintWriter("pg1_sorted_score.txt"); //new pajek file based on community
            readResult = new BufferedReader(new FileReader("pg1_sorted.txt")); //result from community detection

            while ((sCurrentLine = readResult.readLine()) != null) {
                String[] resultSplit = sCurrentLine.split(": ", 2);
                String node = resultSplit[0];
                String pgScore = resultSplit[1];

                if(pgScore.contains("9.") || pgScore.contains("10.") || pgScore.contains("11.") ||
                 pgScore.contains("12.") || pgScore.contains("13.") || pgScore.contains("14.")){
                        System.out.println(node + " " + pgScore); 
                        writer.println(node + " " + pgScore);
                }
            }
            writer.close();
        } 
        catch (IOException e) {
    
        }
    }
}