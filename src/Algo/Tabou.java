package Algo;

import java.util.ArrayList;

import Model.Processeur;
import Model.Tache;

public class Tabou {

	protected ArrayList<Processeur> lproc;
	protected ArrayList<Tache> ltache;


	public Tabou() {
		lproc = new ArrayList<>();
		ltache = new ArrayList<>();
	}

	public Tabou(int temp, ArrayList<Processeur> lproc,ArrayList<Tache> ltache) {
		lproc = new ArrayList<>();
		ltache = new ArrayList<>();
		this.lproc = lproc;
		this.ltache = ltache;
	}	

	public ArrayList<Processeur> tabou(ArrayList<Tache> t, int nbProc, int iter) {
		
		// LPROC MEILLEURE SOLUTION

		lproc = new ArrayList<>();
		if ( nbProc  == 0) {

			if (nbProc == 1) {
				this.lproc.add(new Processeur());
				for (Tache tt : t) {
					// solution init on met tous dans un seul proc
					this.lproc.get(0).add(tt);;
				}
			} else {


			// SOLUTION INIT on repartit les taches equitablement
				for ( int i = 1 ;  i < nbProc; i++) {
					this.lproc.add(new Processeur());
				}

				// nombre delement dans chaque proc
				int nb =  t.size()/nbProc;

				int j = 0, k = 0;

				// on met dans nbProc - 1 , nb tache
				for (int i = 0;   i < nbProc; i++) {
					
					// borne darret
					k = i*nb + nb;

					for (j = 0; j < k ; j++) {
						this.lproc.get(i).add(t.get(    k-nb+j    ));
					}
				}
				
				// dans le dernier proc on met le reste  car possibilite de dimpair

				for (int i = k;  i < t.size(); i++) {
					this.lproc.get(nbProc-1).add(t.get(i));
				}

				
				// Liste tabou vide
				
				ArrayList< ArrayList<Processeur> >listeTabou = new ArrayList<>();
				
				
				// LE GROS DU BOULOT
				
				while (iter > 0) {
					
					
					
					
					
					
					
					
				}
				
				
				
				


					
				

			}
		}


		return lproc;
	}

	public ArrayList<Tache> getLtache() {
		return ltache;
	}

	public void setLtache(ArrayList<Tache> ltache) {
		this.ltache = ltache;
	}

	public ArrayList<Processeur> getLproc() {
		return lproc;
	}

	public void setLproc(ArrayList<Processeur> lproc) {
		this.lproc = lproc;
	}
	
	public int objectifOptiL3(ArrayList<Processeur> proc) {
		
		// calcul  du plus long chemin
		int max = Integer.MIN_VALUE;
		int courant = 0;
		for (Processeur p : proc) {
			
			for ( Tache t : p.getLproc()) {
				courant+=t.getDuree();
			}
			if ( max < courant) max = courant;
		}
		return max;
	}



}
