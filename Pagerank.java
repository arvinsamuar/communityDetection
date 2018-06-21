import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;


public class Pagerank{

	public static void main (String []args){
		int n = 189000;
		int aa = 189000;
		double nn = 189000.0;
		final double alpha = 0.85;
		final double epsilon = 1e-8;
		//int indexG, indexG2;
		//int idx = 0;

		//char[] inputFile = new char[50];

		List<HashMap<Integer, Double>> gList = new ArrayList<HashMap<Integer, Double>>();
		for (int i=0; i<n; i++){
			gList.add(new HashMap<Integer, Double>());
		}

		double[] rVektor = new double[n+n];
		double[] rpVektor = new double[n+n];
		double[] vektorHelper = new double[n+n];
		double[] vektorHelper2 = new double[n+n];
		double[] outDegree = new double[n+n];
		double[] inDegree = new double[n+n];
		double[] linkRank = new double[n+n];
		double[] expVal = new double[n+n];

		BufferedReader br = null;
		BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out), 8*1024);
		try{
			String currentLine;

			//br = new BufferedReader(new FileReader("jaringan_diatas_2015.net"));
			br = new BufferedReader(new FileReader("jaringan_diatas_2015.net"));
			while((currentLine = br.readLine()) != null){
				String[] split = currentLine.split(" ", 3);

				int nodeI = Integer.parseInt(split[0]);
				int nodeJ = Integer.parseInt(split[1]);
				int weight = Integer.parseInt(split[2]);

				System.out.println(nodeI + " " + nodeJ);

				//gMatrix[nodeI*n+nodeJ] = weight; 
				HashMap<Integer, Double> gMap = gList.get(nodeI);
				gMap.put(nodeJ, (double)weight);
				outDegree[nodeI] += weight;
				inDegree[nodeJ] += weight;
			}

			PrintWriter printOutDegree = new PrintWriter("OutDegree.txt");
			for(int i=0; i<n; i++){
				//System.out.println(i + " " + outDegree[i]);
				printOutDegree.println(i + " " +outDegree[i]);
			}
			printOutDegree.close();
/*
			PrintWriter printInDegree = new PrintWriter("InDegree.txt");
			for(int i=0; i<n; i++){
				System.out.print(inDegree[i] + " ");
				printOutDegree.println(inDegree[i]);
			}
			printInDegree.close();*/

			//print array Gmatrix awal
			//PrintWriter printGMatrix = new PrintWriter("gMatrix.txt");
		
			
			/*
			log.println("Gmatrix awal : ");
			for(int i=0; i<n; i++){
				for(int j=0; j<n; j++){
					indexG = i*aa+j;
					log.print(indexG + ": " + gMatrix[i*aa+j] + "; ");
			//		printGMatrix.println(gMatrix[i*n+j]);
				}
			}
			*/
			//printGMatrix.close();
		

			//normalisasi
			for(int i=0; i< n; i++){
				HashMap<Integer, Double> gMap = gList.get(i);
				for (Map.Entry<Integer, Double> entry : gMap.entrySet()){
					Integer j = entry.getKey();
					Double w = entry.getValue();
					w /= outDegree[i];
					gMap.put(j, w);
					log.write( j + " norm " + w);
					log.newLine();
					log.flush();
				}
				/*
				if(outDegree[i] == 0.0){
					for(int j=0; j<n; j++){
						//gMatrix[i*n+j] = 1/nn;
						gMatrix[i*aa+j] = 1/nn;
					}
				}
				else{
					for(int j=0; j<n; j++){
						//gMatrix[i*n+j] = gMatrix[i*n+j]/outDegree[i];
						gMatrix[i*aa+j] = gMatrix[i*aa+j]/outDegree[i];
					}
				} 
				*/
			}

			//print array Gmatrix setelah normalisai
			/*
			log.println("Gmatrix setelah normalisasi : ");
			for(int i=0; i<n; i++){
				for(int j=0; j<n; j++){
					indexG = i*aa+j;
					log.print(indexG + ": " + gMatrix[i*aa+j] + "; ");
				}
			}
			*/
		

			//add teleportation, with probability 1-alpha
			for(int i=0; i<n; i++){
				HashMap<Integer, Double> gMap = gList.get(i);
				for (Map.Entry<Integer, Double> entry : gMap.entrySet()){
					Integer j = entry.getKey();
					Double w = entry.getValue();
					w = alpha * w + (1.0 - alpha)/nn;
					gMap.put(j, w);
					log.write(j + " alpha " + w);
					log.newLine();
					log.flush();
				}	
				/*
				for(int j=0; j<n; j++){
					//gMatrix[i*n+j] = alpha * gMatrix[i*n+j] + (1.0 - alpha)/nn;
					gMatrix[i*aa+j] = alpha * gMatrix[i*aa+j] + (1.0 - alpha)/nn;
				}
				*/
			}
		
			//print array Gmatrix setelah 1-alpha
			/*
			log.println("gMatrix setelah 1-alpha : ");
			for(int i=0; i<n; i++){
				for(int j=0; j<n; j++){
					indexG = i*aa+j;
					if(gMatrix[i*n+j] != 0.0)
						log.print(indexG + ": " + gMatrix[i*aa+j] + "; ");
				}
			}
			*/
			double helper = alpha * (1/nn) + (1.0 - alpha)/nn;
			double helper2 = (1.0 - alpha)/nn;

		
			double totalDegree = 0.0;
			for(int i=0; i<n; i++){
				totalDegree += inDegree[i];
			}
			

			//iterate to get final R
			for(int i=0; i<n; i++){
				rVektor[i] = 1/nn;
			
			}
			

			
			double norm = 1.0;
			int count = 0;
			double temp;
			


			while(norm >= epsilon){
				count++;

				double tempVektor = 0;
				double tempVektor2 = 0;
				for(int i=0; i<n; i++){
					rpVektor[i] = rVektor[i];
					rVektor[i] = 0.0;
					if (outDegree[i] == 0.0){
						tempVektor += rpVektor[i];
					} else{
						tempVektor2 += rpVektor[i];
					}
					
				}
				log.write("loop ke- " + count);
				log.newLine();
				log.flush();

				for(int i = 0; i<n; i++){
					vektorHelper[i] = tempVektor;
					vektorHelper2[i] = tempVektor2;
				}
				
				for(int i=0; i<n; i++){
					HashMap<Integer, Double> gMap = gList.get(i);
					for (Map.Entry<Integer, Double> entry : gMap.entrySet()){
						Integer j = entry.getKey();
						Double w = entry.getValue();
						rVektor[j] += rpVektor[i] * w;
						vektorHelper2[j] -= rpVektor[i];
					}
				}

				for(int i=0; i<n; i++){
					rVektor[i] += vektorHelper[i] * helper;
					rVektor[i] += vektorHelper2[i] * helper2;
				}

				// calculate norm
				norm = 0.0;
				for(int i=0; i<n; i++){
					temp = rVektor[i] - rpVektor[i];

					if(temp < 0.0){
						temp = 0.0 - temp;
					}
					if(temp > norm){
						norm = temp;
					}
				}
			}
			 


			//Print Pagerank
			 BufferedWriter printPr = new BufferedWriter(new FileWriter("Pagerank.txt"), 8*1024);
			//PrintWriter printPageRank = new PrintWriter("PageRankFor2.txt");
			//for(int i=0; i<n; i++){
			for(int i=0; i<n; i++){
				printPr.write(i + " " + rVektor[i]);
				printPr.newLine();
				//printPageRank.println(i + " " + rVektor[i]);
			}
			printPr.close();
			System.out.println("PR Done!");
			//Print LinkRank
			BufferedWriter printLr = new BufferedWriter(new FileWriter("LinkRank.txt"), 8*1024);
			//PrintWriter printLinkRank = new PrintWriter("LinkRankFor2.txt");
			for(int i=0; i<n; i++){
				for(int j=0; j<n; j++){
					//linkRank [i] = rVektor[i] * gMatrix[i*n+j];
					//linkRank [i] = rVektor[i] * gMatrix[i*aa+j];
					HashMap<Integer, Double> gMap = gList.get(i);
					Double w = gMap.get(j);
					if (w != null){
						linkRank[i] = rVektor[i] * w;
						printLr.write(i + " " + j + " " + linkRank[i]);
						printLr.newLine();
						log.write(i + " LR " + j);
						log.newLine();
  						log.flush();
					}
					/*else{
						if (outDegree[i] == 0.0){
							linkRank[i] = rVektor[i] * helper;
						} else{
							linkRank[i] = rVektor[i] * helper2;
						}
					}*/
					
				
					//printLinkRank.println(i + " " + j + " " + linkRank[i]);
				}
			}
			printLr.close();
			
			System.out.println("LR Done!");

			//Print Expected Value
			BufferedWriter printEx = new BufferedWriter(new FileWriter("Exp.txt"), 8*1024);
			//PrintWriter printExpVal = new PrintWriter("ExpFor2.txt");
			for(int i=0; i<n; i++){
				for(int j=0; j<n; j++){
					HashMap<Integer, Double> gMap = gList.get(i);
					Double w = gMap.get(j);
					if (w != null){
						expVal [i] = rVektor[i] * rpVektor[j];
						printEx.write(i + " " + j + " " + expVal[i]);
						printEx.newLine();
						log.write(i + " EX " + j);
						log.newLine();
  						log.flush();
					}
					
					//printExpVal.println(i + " " + j + " " + expVal[i]);
				}
			}
			printEx.close();

			
			System.out.println("Number of Steps : " + count);

		}
		catch(IOException e){

		}

	}

}