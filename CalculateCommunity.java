import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

 
public class CalculateCommunity {
    public static void sort(int[] a, int low, int high) {
        int N = high - low;         
        if (N <= 1) 
            return; 
        int mid = low + N/2; 
        // recursively sort 
        sort(a, low, mid); 
        sort(a, mid, high); 
        // merge two sorted subarrays
        int[] temp = new int[N];
        int i = low, j = mid;
        for (int k = 0; k < N; k++) 
        {
            if (i == mid)  
                temp[k] = a[j++];
            else if (j == high) 
                temp[k] = a[i++];
            else if (a[j]<a[i]) 
                temp[k] = a[j++];
            else 
                temp[k] = a[i++];
        }    
        for (int k = 0; k < N; k++) 
            a[low + k] = temp[k];         
    }
    public static void main(String a[]){
        BufferedReader bacaHasil = null;        
        try {
            String sCurrentLine;
            int i = 0;
            int n = 188515;
            int[] arr = new int [n];
            PrintWriter writer = new PrintWriter("komunitas_sorted.txt"); 
            bacaHasil = new BufferedReader(new FileReader("Result_final_list.txt")); 

            while ((sCurrentLine = bacaHasil.readLine()) != null) {
                
                String[] resultSplit = sCurrentLine.split(" ", 2);
                int komunitas = Integer.parseInt(resultSplit[1]);
                arr[i] = komunitas;
                i++;
            }

            sort(arr, 0, n);

            for(int ii=0; ii<n; ii++){
                writer.println(arr[ii]);
                System.out.println(arr[ii]);
            }
            writer.close();
        } 
        catch (IOException e) {
    
        }        
    }
}