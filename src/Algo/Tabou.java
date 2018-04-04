package Algo;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Processeur;
import Model.Tache;

public class Tabou {

	protected ArrayList<Processeur> lproc;
	protected ArrayList<Tache> ltache;
	protected HashMap<Processeur,ArrayList<Tache>> listTabou;
	protected int taille;


	public Tabou(int taille) {
		lproc = new ArrayList<>();
		ltache = new ArrayList<>();
		this.taille = taille;
		this.listTabou = new HashMap<Processeur, ArrayList<Tache>>(taille);
	}

	public Tabou(int taille, ArrayList<Processeur> lproc,ArrayList<Tache> ltache) {
		lproc = new ArrayList<>();
		ltache = new ArrayList<>();
		this.lproc = lproc;
		this.ltache = ltache;
		this.taille = taille;
		this.listTabou = new HashMap<Processeur, ArrayList<Tache>>(taille);
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
	
	
	
	
	public void insertTabout(Processeur proc, ArrayList<Tache> lTache){
		this.listTabou.put(proc, lTache);
	}
	
	public void updateConfiguration(ArrayList<Tache> listTache){
		this.ltache = listTache;
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
	
	
	public Tache selectMeilleurVoisin(int i) {
		return this.ltache.get(i);
	}
	
	
	//recupere la tache avec la durre max dans un processeur
	public int evaluationVoisin(Processeur p){
		//int i = (int)(Math.random() * (this.lproc.size() - 0));
		int tacheIndix = 0;
		int dureMax = 0;
		for(int i = 0; i < p.getListTache().size();++i){
			if(p.getListTache().get(i).getDuree() > dureMax){
				dureMax = p.getListTache().get(i).getDuree();
				tacheIndix = i;
			}
		}
	
		return tacheIndix;
	}



}
