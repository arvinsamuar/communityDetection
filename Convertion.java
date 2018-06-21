import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
/*
	this class will took the index from a publication that cited
	with another publication.
	with format "#index A(space)#index that cited by A"
*/

public class Convertion{
	public static void main (String []args){
		BufferedReader br = null;
		String realIndex;
		String realCitation;
		
		try {
			String sCurrentLine;
			String sNextLine;
			String idx = "index";
			String cit = "%";
			PrintWriter writer = new PrintWriter("result_cite_only.txt");
			br = new BufferedReader(new FileReader("dblp_aminer.txt")); //DBLP file

			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.toLowerCase().contains(idx.toLowerCase())){
					realIndex = sCurrentLine.replace("index" , "");
					if((sNextLine = br.readLine()).contains(cit)){
						realCitation = sNextLine.replace("%" , "");
						System.out.println(realIndex + " " + realCitation);
						writer.println(realIndex + " " + realCitation);
						
						while((sCurrentLine = br.readLine()).contains(cit)){
							realCitation = sCurrentLine.replace("%" , "");
								System.out.println(realIndex + " " + realCitation);
								writer.println(realIndex + " " + realCitation);
						}
					}	
				}				
			}
			writer.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
	}
}