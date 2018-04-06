package Algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.sun.org.apache.bcel.internal.generic.IDIV;

import Model.Processeur;
import Model.Tache;

public class Tabou {

	protected ArrayList<Processeur> lproc;
	protected ArrayList<Tache> ltache;
	protected ArrayList<Processeur[]> listTabou;
	protected ArrayList<Processeur[]> listVoisin;
	protected HashMap<Processeur,ArrayList<Tache>> meilleurConfig;
	protected Processeur[] tabProc;
	protected int taille;
	protected int iteration;
	protected int nbProc;
	protected int nbTache;

	public Tabou(int taille, int nbProc, int nbTache, int iter) {

		this.taille = taille;
		this.nbProc = nbProc;
		this.nbTache = nbTache;
		this.iteration = iter;
		this.lproc = new ArrayList<>(nbProc);
		this.ltache = new ArrayList<>(nbTache);
		this.listTabou = new ArrayList<Processeur[]>(taille);
		this.listVoisin = new ArrayList<Processeur[]>();
		tabProc = new Processeur[this.nbProc];
		settingTacheProcesseur();
	}
	
	public void settingTacheProcesseur(){
		//int i = (int)(Math.random() * (this.lproc.size() - 0));
		
		//cre nb processeur
		for(int i = 0; i < this.nbProc;++i){
			Processeur p = new Processeur(i);
			this.lproc.add(p);
		}
		
		for(int j = 0; j < this.nbTache; j++){
			//System.out.println("Duree de la tache " + j);
			//Scanner sci = new Scanner(System.in);
			//int dure = sci.nextInt();
			//Tache t = new Tache(dure, j);
			Tache t = new Tache(5, j);
			this.ltache.add(t);
		}
		
		//affectation a chaque processeur une arraylist de tache facon random
		//for(Processeur p : this.lproc){
			//int j = (int)(Math.random() * (this.ltache.size() - 1));
			for(Tache tache : this.ltache){
				this.lproc.get(0).add(tache);
			}
	}


	public void tabou() {
		int iter = 0;
		affichage();
		while(iter < this.iteration){
			//affichage();
			System.out.println("**********iteration " + iter + "**********");
			selectTache();
			affichage();
			iter++;
		}

	}
	
	public int cMax(ArrayList<Processeur> lproc){
		int max = 0;
		for(Processeur p : lproc){
			if(p.getDuree() > max){
				max = p.getDuree();
			}
		}
		return max;
		
	}
	
	public void affichage() {
		
		for(Processeur p : this.lproc){
			System.out.println("Processeur " + p.getNomProcesseur());
			for(Tache t : p.getListTache()){
				System.out.print("	Tache " + t.getNumeroTache() +": " + t.getDuree());
			}
			System.out.println();
		}

		System.out.println(this.cMax(lproc) + " END");
	}
	
	public void selectTache(){
		insertTabou();
		int indiceProcSelectEmi = getProcesseurDurreMax();
		int indiceProcSelectRecu = getProcesseurDurreMin();
		/*while (indiceProcSelectEmi == indiceProcSelectRecu || this.lproc.get(indiceProcSelectEmi).getListTache().size() == 0){
			indiceProcSelectEmi = (int)(Math.random() * (this.lproc.size() - 0));
			indiceProcSelectRecu = (int)(Math.random() * (this.lproc.size() - 0));
			
		}*/

		int indiceTacheSelect = (int)(Math.random() * (this.lproc.get(indiceProcSelectEmi).getListTache().size() - 0));
		
		ajouterVoisin(this.lproc.get(indiceProcSelectEmi).getlTache().get(indiceTacheSelect), indiceProcSelectEmi,indiceProcSelectRecu, lproc);
		this.lproc.get(indiceProcSelectEmi).getListTache().remove(indiceTacheSelect);
		
		Processeur[] tabVoisin = new  Processeur[this.nbProc];
		for(int i  = 0 ; i < this.nbProc ; ++i){
			tabVoisin[i] = this.lproc.get(i);
					
		}
		this.listVoisin.add(tabVoisin);
		//updateState(tabVoisin);
		
		
	}
	
	public int getProcesseurDurreMax(){
		int indice = 0;
		int max = 0;
		for(int i =0; i < this.lproc.size(); ++i){
			if(this.lproc.get(i).getDuree() > max){
				max = this.lproc.get(i).getDuree();
				indice = i;
			}
		}
		return indice;
	}
	
	public int getProcesseurDurreMin(){
		int indice = 0;
		int min = this.lproc.get(0).getDuree(); ;
		for(int i = 0; i < this.lproc.size(); ++i){
			if(this.lproc.get(i).getDuree() <= min){
				min = this.lproc.get(i).getDuree();
				indice = i;
			}
		}
		return indice;
	}
	
	
	public void updateState(Processeur[] tabProc){
		this.lproc.clear();
		for(int i = 0; i < this.nbProc ; ++i){
			this.lproc.add(tabProc[i]);
		}
		
	}
	
	public void insertTabou(){
		for(int i = 0; i < nbProc ; ++i){
			tabProc[i] = this.lproc.get(i);
		}
		this.listTabou.add(tabProc);
		
		/*for(int i = 0; i < this.listTabou.size() ; i++){
			for(int j = 0 ; j < this.listTabou.get(i).length; ++j ){
				System.out.println("\n Processeur tabou " + this.listTabou.get(i)[j].getNomProcesseur());
				for(Tache t: this.listTabou.get(i)[j].getListTache() ){
					System.out.print("	Tache tabou " + t.getNumeroTache() +": " + t.getDuree());
				}
				
			}
			System.out.println();
		}*/
	}
	
	public void ajouterVoisin(Tache tache , int i, int j, ArrayList<Processeur> listproc){
		if(!listproc.get(j).getListTache().contains(tache)){
			listproc.get(j).add(tache);
		}
	}
	
	public void updateConfiguration(Processeur p,ArrayList<Tache> listTache){
		meilleurConfig.put(p, listTache);
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
