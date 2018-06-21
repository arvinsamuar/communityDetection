import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/** 
This class will add "1" to each node
**/
public class FixinResultCom{
	public static void main (String []args){
		BufferedReader readResult = null;
		//BufferedReader bacaPajek = null;
		
		try {
			String sCurrentLine;
			
			PrintWriter writer = new PrintWriter("Result_final_fixed.txt"); //new pajek file based on community
			readResult = new BufferedReader(new FileReader("Result_final_list.txt")); //read result from community detection
		//	bacaPajek = new BufferedReader(new FileReader("hehe.net")); //original pajek file

			while ((sCurrentLine = readResult.readLine()) != null) {
				
				String[] resultSplit = sCurrentLine.split(" ", 2);
				
				String nodeCom = resultSplit[0];
				String commuinity = resultSplit[1];

				int nodeFixed = Integer.parseInt(nodeCom) + 1;

				System.out.println(nodeFixed + " " + commuinity); 
				writer.println(nodeFixed + " " + commuinity);

				
			}
			writer.close();
		} 
		catch (IOException e) {
	
		}
	}
}