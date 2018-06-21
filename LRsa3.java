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
import java.util.Random;

public class LRsa3{
	public static void main (String []args){
		int n = 188515;
		int nSub;
		double nn = 188515.0;
		double alpha = 0.85;
		double dua = 2.0;

		BufferedReader lr = null;
		BufferedReader ex = null;

		double tMax = 0.001;
		double tMin = 0.0001;
		double cooling = 0.3;
		double tSubMax = 1.0;

		//Data Structure
		//int cMax, cTemp, cSubTemp, cSub, map;
		//double dd, dSub;

		int groupID, groupNum;
		double dRand, dRand1, dTemp;

		List<HashMap<Integer, Double>> dList = new ArrayList<HashMap<Integer, Double>>();
		for (int i=0; i<n; i++){
			dList.add(new HashMap<Integer, Double>());
		}

		List<HashMap<Integer, Double>> dSubList = new ArrayList<HashMap<Integer, Double>>();
		for (int i=0; i<n; i++){
			dSubList.add(new HashMap<Integer, Double>());
		}

		List<List<Integer>> al = new ArrayList<List<Integer>>();
		for (int i = 0; i<n; i++) {
			al.add(new ArrayList<>());
		}

		BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out), 8*1024);
		//double[] dMatrix = new double[n*n];
		//double[] dSubMatrix = new double[n*n];
		int[] cMatrix = new int[n];
		int[] cMaxMatrix = new int[n];
		int[] cTempMatrix = new int[n];
		int[] cSubMatrix = new int[n];
		int[] cSubTempMatrix = new int[n];
		int[] mapMatrix = new int[n];
		int[] groupIDMatrix = new int[n+1];


		double q, qTemp, qSub, qSubTemp;
		double qMax = 0;
		double t, tSub;
		int k, x, y;
		int flag;
		int nTemp1, nTemp2, nTemp3;
		try{
			String currentLR;
			String currentEx;

			lr = new BufferedReader(new FileReader("LinkRank.txt"));
			ex = new BufferedReader(new FileReader("Exp.txt"));

			// baca file LinkRank
			while((currentLR = lr.readLine()) != null){
				String[] splitLr = currentLR.split(" ", 3);
				int nodeI = Integer.parseInt(splitLr[0]);
				int nodeJ = Integer.parseInt(splitLr[1]);
				double lrVal = Double.parseDouble(splitLr[2]);


				HashMap<Integer, Double> dMap = dList.get(nodeI);
				dMap.put(nodeJ, (double)lrVal);
				
				//dMatrix[nodeI*n+nodeJ] = lrVal;
			}

			/*for(int i=0; i<n; i++){
				for(int j=0; j<n; j++){
						System.out.println(dMatrix[i*n+j]);
				}
			}*/

			// baca file Exp
			while((currentEx = ex.readLine()) != null){
				String[] splitEx = currentEx.split(" ", 3);
				int nodeI = Integer.parseInt(splitEx[0]);
				int nodeJ = Integer.parseInt(splitEx[1]);
				double exVal = Double.parseDouble(splitEx[2]);

				HashMap<Integer, Double> dMap = dList.get(nodeI);
				for(Map.Entry<Integer, Double> entry : dMap.entrySet()){
					Integer j = entry.getKey();
					Double eksp = entry.getValue();
					if( nodeJ == j ){
						eksp -= exVal;
						dMap.put(nodeJ, (double)eksp);
					}
					
				}
				//dMatrix[nodeI*n+nodeJ] -= exVal;				
			}

			//memasukan edge kedalam list al untuk loop reverse
			for (int i = 0; i < n; i++) {
	   			HashMap<Integer, Double> dMap = dList.get(i);
	    		for (Map.Entry<Integer, Double> entry : dMap.entrySet()) {
	        		Integer j = entry.getKey();
	            	al.get(i).add(j);
			    }
			}

			/*for(int i=0; i<n; i++){
				HashMap<Integer, Double> dMap = dList.get(i);
				for (Map.Entry<Integer, Double> entry : dMap.entrySet()){
					Integer j = entry.getKey();
					Double w = entry.getValue();

					System.out.println(i + " " + j + " " + w);
				}
			}*/

			//initialization cMatrix
			for(int i=9; i<n/2; i++){
				cMatrix[i] = 0;
			}
			for(int i=(n+1)/2; i<n; i++){
				cMatrix[i] = 1;
			}

			groupNum = 2;
			groupIDMatrix[0] = 0;
			groupIDMatrix[1] = 1;
		

			//modularity
			q = 0.0;
			for(int i=0; i<n; i++){
				HashMap<Integer, Double> dMap = dList.get(i);
				for(Map.Entry<Integer, Double> entry : dMap.entrySet()){
					Integer j = entry.getKey();
					Double lrV = entry.getValue();
					if(cMatrix[i] == cMatrix[j]){
						q += lrV;
					}
				}
			}

			System.out.println("Q_Initial = " + q);

			Random rand = new Random(System.currentTimeMillis());
			t = tMax;
			while(t > tMin){
				if(groupNum > 1){
					log.write("161 groupNum > 1" + groupNum);log.newLine();log.flush();
					for(int i=0; i<n; i++){
						log.write("163 i = " + i);log.newLine();log.flush();
						int ii = (int)((rand.nextDouble()) * n);
						int jj = (int)((rand.nextDouble()) * groupNum);
						//log.write("ii = " + ii + "; jj = " + jj);log.newLine();log.flush();

						nTemp1 = groupIDMatrix[jj];
						if(nTemp1 == cMatrix[ii]){
							continue;
						}
						qTemp = q;
						k = ii;
						HashMap<Integer, Double> dMap = dList.get(k);
						for(Map.Entry<Integer, Double> entry : dMap.entrySet()){
							Integer j = entry.getKey();
							Double dVal = entry.getValue();
							if(k == j){
								continue;
							}
							if(nTemp1 == cMatrix[j]){
								if( dVal != null )
									qTemp += dVal;
									//qTemp += dMatrix[k*n+j];
								else
									qTemp += dua;
									//qTemp += dMatrix[j*n+k];
							}
							if(cMatrix[k] == cMatrix[j]){
								if( dVal != null ){
									qTemp -= dVal;
									//qTemp -= dMatrix[k*n+j];
								}
								else{
									qTemp -= dua;
									//qTemp -= dMatrix[j*n+k];										
								}
							}
						}

						dTemp = Math.exp((qTemp-q)/t);
						dRand = rand.nextDouble();
						//log.write("dTemp = " + dTemp + "; dRand = " + dRand);log.newLine();log.flush();

						if(qTemp > q || dRand < dTemp){
							cMatrix[ii] = nTemp1;
							q = qTemp;
						}
						if(qTemp > qMax){
							qMax = qTemp;
							for(int j=0; j<n; j++){
								log.write("212 nulis cMaxMatrix = " + j);log.newLine();log.flush();
								cMaxMatrix[j] = cMatrix[j];
							}
						}
					}
				}

				//merging and dividing
				for(int i=n; i>0; i--){
					int a, b;
					log.write("222 merging & dividing i = " + i + " t= " + t + "; tMin= " + tMin);log.newLine();log.flush();
					dRand1 = rand.nextDouble();
					//log.write("dRand1 = " + dRand1);log.newLine();log.flush();
					//merging
					if(dRand1 < 0.5){
						if(groupNum < 2){
							continue;
						}
						dRand = rand.nextDouble();
						//log.write("dRand 212 = " + dRand);log.newLine();log.flush();
						
						a = (int)dRand * groupNum; 	//ath group is selected to receive new members
						dRand = rand.nextDouble();
						b = (int)dRand * groupNum; 	//bth group is selected to delete
						//log.write("a = " + a + "; b = " + b);log.newLine();log.flush();

						if(a == b){
							continue;
						}
							//nTemp1 = groupIDMatrix[a];
							//nTemp2 = groupIDMatrix[b];
						qTemp = q;
						for (int kk = al.size(); kk >= 0; kk--) {
   							if(cMatrix[kk] == groupIDMatrix[b]){
	   							HashMap<Integer, Double> dMap = dList.get(kk);
	    						for (int ll = al.get(kk).size(); ll >= 0; ll--) {
	        						Double w = dMap.get(ll);
	        						if(cMatrix[ll] == groupIDMatrix[a]){
										qTemp += w;
										qTemp += dua;
									}
	        					}
	        				}
        				}

						/*for(int kk=n-1; kk>=0; kk--){
							if(cMatrix[kk] == groupIDMatrix[b]){
								HashMap<Integer, Double> dMap = dList.get(kk);
								for(int ll=n-1; ll>=0; ll--){
									Double w = dMap.get(ll);
									if(w != null){
										if(cMatrix[ll] == groupIDMatrix[a]){
											qTemp += w;
											//qTemp += dMatrix[kk*n+ll];
											qTemp += dua; //???
											//qTemp += dMatrix[ll*n+kk];
										}
									}
								}
							}
						}*/

						dTemp = Math.exp((qTemp-q)/t);
						dRand = rand.nextDouble();
						//log.write("252 dTemp = " + dTemp + "; dRand = " + dRand);log.newLine();log.flush();

						if(qTemp > q || dRand < dTemp){
							for(int kk=n-1; kk>=0; kk--){
								if(cMatrix[kk] == groupIDMatrix[b]){
									cMatrix[kk] = groupIDMatrix[a];
								}
							}
							q = qTemp;
						//	log.write("263 groupNum = " + groupNum);log.newLine();log.flush();
							for(int kk=b; kk<groupNum-1; kk++){
								groupIDMatrix[kk] = groupIDMatrix[kk+1];		//deleting jth group from group id
							}
							groupNum--;
						}
						if(qTemp > qMax){
							qMax = qTemp;
							for(int kk=0; kk<n; kk++){
								cMaxMatrix[kk] = cMatrix[kk];
							}
						}

					}
					else{	//dividing
						dRand = rand.nextDouble();
						//log.write("279 dRand = " + dRand);log.newLine();log.flush();
						a = (int)dRand * groupNum; 	//i is the indexof the group to divide
						//log.write("281 a = " + a);log.newLine();log.flush();
						b = 0;
						flag = 1;

						while(flag != 0){
							flag = 0;
							for(int kk=0; kk<groupNum; kk++){
								if(groupIDMatrix[kk] == b)
									flag = 1;
							}
							b++;
						}
						b--;  		// b is the ID of the new group, not an index

						//nTemp1 = groupIDMatrix[i];
						//nTemp2 = b;
						//mapping to Dsub, Csub
						nSub = 0;
						dRand = rand.nextDouble();

						for(int kk=n-1; kk>=0; kk--){
							if(cMatrix[kk] == groupIDMatrix[i]){
								mapMatrix[nSub] = kk;
								//log.write("307 mapMatrix[] = " + mapMatrix[nSub]);log.newLine();log.flush();
								if(dRand < 0.5){
									cSubMatrix[nSub] = groupIDMatrix[i];
								}
								else{
									cSubMatrix[nSub] = b;
								

								dRand = rand.nextDouble();
								//log.write("318 dRand = " + dRand);log.newLine();log.flush();
								nSub++;
								//log.write("320 nSub = " + nSub);log.newLine();log.flush();
								}
							}
						}
						log.write("321 nSub = " + nSub);log.newLine();log.flush();
						if(nSub < 2){
							log.write("331 nSub < 2 : " + nSub);log.newLine();log.flush();
							continue;
						}
						//calculate total edge, degree of ith group
						for(int kk=nSub-1; kk>=0; kk--){
							HashMap<Integer, Double> dSubMap = dSubList.get(kk);
							HashMap<Integer, Double> dMap = dList.get(kk);
							for (int ll=nSub-1; ll>=0; ll--){
								int idxIsiSub = mapMatrix[kk] * n + mapMatrix[ll];
								int idxIsiSub2 = mapMatrix[ll] * n + mapMatrix[kk];
								Double isiSub = dMap.get(idxIsiSub);
								Double isiSub2 = dMap.get(idxIsiSub2);

								if(isiSub != null){
									if(isiSub2 != null){
										dSubMap.put(ll, (double)isiSub);
										dSubMap.put(kk, (double)isiSub2);
									}
									else{
										dSubMap.put(ll, (double)isiSub);
										dSubMap.put(kk, (double)dua);	
									}
								}
								else{
									if(isiSub2 != null){
										dSubMap.put(ll, (double)dua);
										dSubMap.put(kk, (double)isiSub2);
									}
									else{
										dSubMap.put(ll, (double)dua);
										dSubMap.put(kk, (double)dua);	
									}
								}
								//dSubMatrix[kk*nSub+ll] = dMatrix[(int)mapMatrix[kk]*n+(int)mapMatrix[ll]];
								//dSubMatrix[ll*nSub+kk] = dMatrix[(int)mapMatrix[ll]*n+(int)mapMatrix[kk]]; 
							}
						}

						//calculate Qsub first
						/*qSub = 0.0;
						for(int kk=nSub-1; kk>=0; kk--){
							for(int ll=nSub-1; ll>=0; ll--){
								if(cSubMatrix[kk] == cSubMatrix[ll]){
									qSub += dSubMatrix[kk*nSub+ll];
								}
							}
						}*/
						qSub = 0.0;
						for(int kk=nSub-1; kk>=0; kk--){
							HashMap<Integer, Double> dSubMap = dSubList.get(kk);
							for(int ll=nSub-1; ll>=0; ll--){
								Double idx = dSubMap.get(ll);
								if(cSubMatrix[kk] == cSubMatrix[ll]){
									qSub += idx;
								}
							}
						}
						log.write("387 qSub = " + qSub);log.newLine();log.flush();

						//tsub cooling
						tSub = tSubMax;
						while(tSub>t){
							log.write("while tSub > t");log.newLine();log.flush();
							for(int kk=nSub*nSub-1; kk>=0; kk--){
								dRand = rand.nextDouble();
								x = (int)dRand * nSub; 	//x is a random number of [0, nsub-1], index of selected node
								nTemp3 = cSubMatrix[x];

								if(nTemp3 == groupIDMatrix[i])
									y = b;		// y is the groupID which is selected to move to k=x
								else
									y = groupIDMatrix[i];
								qSubTemp = qSub;
								for(int ll=nSub-1; ll>=0; ll--){
									HashMap<Integer, Double> dSubMap = dSubList.get(x);
									Double dsubM = dSubMap.get(ll);
									if(x == ll){
										continue;
									}
									if(cSubMatrix[ll] == nTemp3){
										if(dsubM != null){
											qSubTemp -= dsubM;
										}
										else{
											qSubTemp -= dua;
										}
										//qSubTemp -= dSubMatrix[x*nSub+ll];
										//qSubTemp -= dSubMatrix[ll*nSub+x];
									}
									else{
										qSubTemp += dsubM;
										qSubTemp += dua;
										//qSubTemp += dSubMatrix[x*nSub+ll];
										//qSubTemp += dSubMatrix[ll*nSub+x]; 
									}
								}

								dTemp = Math.exp(qSubTemp-qSub/t);
								dRand = rand.nextDouble();
								if(qSubTemp > qSub || dRand < dTemp){
									cSubMatrix[x] = y;
									qSub = qSubTemp;
								}
							}
							tSub = cooling*tSub;
						}

						for(int kk=n-1; kk>=0; kk--){
							cTempMatrix[kk] = cMatrix[kk];
						}
						for(int kk=nSub-1; kk>=0; kk--){
							cTempMatrix[(int)mapMatrix[kk]] = cSubMatrix[kk];
						}

						/*qTemp=0.0;
						for(int kk=0; kk<n; kk++){
							for(int ll=0; ll<n; ll++){
								if(cTempMatrix[kk] == cTempMatrix[ll]){
									qTemp += dMatrix[kk*n+ll];	
								}
							}
						}*/

						qTemp = 0.0;
						for(int kk=0; kk<n; kk++){
							HashMap<Integer, Double> dMap = dList.get(kk);
							for(Map.Entry<Integer, Double> entry : dMap.entrySet()){
								Integer ll = entry.getKey();
								Double lrV = entry.getValue();
								if(cTempMatrix[kk] == cTempMatrix[ll]){
									qTemp += lrV;
								}
							}
						}
						log.write("465 qTemp = " + qTemp);log.newLine();log.flush();

						dTemp = Math.exp(qTemp-q/t);
						dRand = rand.nextDouble();

						if(qTemp > q || dRand < dTemp){
							groupIDMatrix[groupNum] = b;
							groupNum++;
							for (int kk=0; kk<n; kk++) {
								cMatrix[kk] = cTempMatrix[kk];
							}
							q = qTemp;
						}
						if(qTemp > qMax){
							qMax = qTemp;
							for (int kk=0; kk<n; kk++) {
								cMaxMatrix[kk] = cTempMatrix[kk];
							}
						}

					}

				}
				System.out.println("             T = " + t + ";                   Q = " + q);
				t = t*cooling;
			}

			PrintWriter fpFinal = new PrintWriter("Result_final_list.txt");
			for(int i=0; i<n; i++){
				fpFinal.println(i + " " + cMatrix[i]);
			}
			fpFinal.close();

			PrintWriter fpMax = new PrintWriter("Result_max_list.txt");
			for (int i=0; i<n; i++) {
				fpMax.println(i + " " + cMaxMatrix[i]);
			}
			fpMax.close();

			PrintWriter fpMod = new PrintWriter("Alpha_Q.txt");
			fpMod.println(alpha + " " + qMax);
		}
		catch(IOException e){

		}


	}

}
