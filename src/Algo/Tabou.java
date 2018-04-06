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
		while(iter < this.iteration){
			System.out.println("iteration " + iter + "*****************************");
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
		//selection a processeur random
		//int indiceProcSelect = (int)(Math.random() * (this.lproc.size() - 0));
		int indiceProcSelect = 0;
	
		for(int i = 0 ; i < this.lproc.get(indiceProcSelect).getListTache().size(); ++i){
			if(i != indiceProcSelect){
				System.out.println(this.lproc.get(indiceProcSelect).getlTache().get(i));
				ajouterVoisin(this.lproc.get(indiceProcSelect).getlTache().get(i), lproc);
				this.lproc.get(indiceProcSelect).getListTache().remove(i);
			}
		}
		
		
		Processeur[] tabVoisin = new  Processeur[this.nbProc];
		for(int i  = 0 ; i < this.nbProc ; ++i){
			tabVoisin[i] = this.lproc.get(i);
					
		}
		this.listVoisin.add(tabVoisin);
		updateState(tabVoisin);
		
		
	}
	
	public void updateState(Processeur[] tabProc){
		this.lproc.clear();
		for(int i = 0; i < this.nbProc ; ++i){
			this.lproc.add(tabProc[i]);
		}
		
	}
	
	public void insertTabou(){
		Processeur[] tabProc = new Processeur[this.nbProc];
		for(int i = 0; i < nbProc ; ++i){
			tabProc[i] = this.lproc.get(i);
		}
		this.listTabou.add(tabProc);
	}
	
	public void ajouterVoisin(Tache tache , ArrayList<Processeur> listproc){
		for(Processeur p : listproc){
			p.add(tache);
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
