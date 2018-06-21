import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/*
	kode ini akan mengkonvert file pajek dengan format yang biasa
	menjadi file pajek yang formatnya sesuai dengan input LinkRank Algorithm
	menghilangkan angka 1 ( weight graph dari matrix )
*/

public class Konvert{
	public static void main (String []args){
		BufferedReader br = null;
		
		
		try {
			String sCurrentLine;

			br = new BufferedReader(new FileReader("hasil_cite_only_fixed.net"));
			PrintWriter writer = new PrintWriter("jaringan_all.net");
			//PrintWriter limaTiga = new PrintWriter("53_aja.net");
			
			//System.out.println("haha");
			while ((sCurrentLine = br.readLine()) != null) {
				
				String[] idxSplit = sCurrentLine.split(" ", 3);
				
				String first_key = idxSplit[0];
				int second_key = Integer.parseInt(idxSplit[1]);
				String weight = idxSplit[2];

				if(second_key == 53){
					System.out.println(first_key + " " + second_key + " " + weight); 
					limaTiga.println(first_key + " " + second_key + " " + weight);
				}
				else{
					System.out.println(first_key + " " + second_key + " " + weight); 
					writer.println(first_key + " " + second_key + " " + weight);

				}
				
				
			}
			writer.close();

		} 
		catch (IOException e) {
		
	
		}
	/*public void ambil (String ind){
		if(ind = "index"){

		}
	}*/
	}
}