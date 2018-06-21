import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

 
public class Komu {
    public static void main(String []args){
        BufferedReader readResult = null;
        try {
            int i = 0;
            //int n = 188515;
            //int[] arr = new int [n];
            PrintWriter writer = new PrintWriter("sum_community.txt"); //new pajek file based on community
            readResult = new BufferedReader(new FileReader("community_sorted.txt")); //result from community detection
            int sum = 1;
            
            String sCurrentLine = readResult.readLine();
            String sBeforeLine = sCurrentLine;

            while ((sCurrentLine = readResult.readLine()) != null) {
                if(sBeforeLine.equals(sCurrentLine)){
                    sum++;
                }
                else{
                    writer.println(sBeforeLine + " " + sum);
                    System.out.println(sBeforeLine + " " + sum);
                    sBeforeLine = sCurrentLine;
                    sum = 1;
                }
            }
            writer.println(sBeforeLine + " " + sum);
            System.out.println(sBeforeLine + " " + sum);
             writer.close();
        } 
        catch (IOException e) {
        }        
    }
}