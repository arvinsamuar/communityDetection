import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class CreatePajekFile{
	public static void main (String []args){
		BufferedReader readResult = null;
		BufferedReader readPajek = null;
		
		try {
			String sCurrentLine;
			
			/**
			this class will create a new pajek file based in the community
			number in the file name will be represent the community
			*/
			PrintWriter writer = new PrintWriter("community8243.net"); 
			/**
			the result of community detection will be the input of this class
			*/
			readResult = new BufferedReader(new FileReader("Result_final_fixed.txt")); 
			
			while ((sCurrentLine = readResult.readLine()) != null) {
				
				String[] resultSplit = sCurrentLine.split(" ", 2);
				
				String nodeCom = resultSplit[0];
				int community = Integer.parseInt(resultSplit[1]);
				System.out.println(nodeCom);

				/**
				Community number changed based on the community that desired
				*/
				if (community == 8243){
					System.out.println("dapet");
					/**
					readPajek is a input file that will be compared to be a new pajek File
					*/
					readPajek = new BufferedReader(new FileReader("network_above_year_2015.net")); //original file pajek
					String pCurrentLine;
					while((pCurrentLine = readPajek.readLine()) != null){
						String[] pajekSplit = pCurrentLine.split(" ", 2);

						String realNode1 = pajekSplit[0];
						String realNode2 = pajekSplit[1];
						//System.out.println(realNode1 + " " + realNode2);

						if (realNode1.equals(nodeCom) || realNode2.equals(nodeCom)){
							writer.println(realNode1 + " " + realNode2);
							System.out.println(realNode1 + " " + realNode2);
						}
					}
					System.out.println("Done");

				}
/*
				System.out.println(first_key + " " + second_key); 
				writer.println(first_key + " " + second_key);
				*/				
			}
			writer.close();
		} 
		catch (IOException e) {
		
		}
	}
}
