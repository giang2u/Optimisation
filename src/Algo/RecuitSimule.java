package Algo;

import java.util.ArrayList;

import Model.Processeur;
import Model.Tache;

public class RecuitSimule {

	protected double temperature;
	protected ArrayList<Processeur> lproc;
	protected int borneInf, borneSup, nbProc, nbTache;
	protected int pas;


	public RecuitSimule() {
		this.temperature = 5000;
		lproc = new ArrayList<>();
		borneInf=0;
		borneSup = 10;
		nbTache = 10;
		nbProc = 2;
		pas = 2;
	}

	public RecuitSimule(int temp, ArrayList<Processeur> lproc) {
		this.temperature = temp;
		lproc = new ArrayList<>();
		this.lproc = lproc;
		pas = 2;
		borneInf=0;
		borneSup = 10;
		nbTache = 10;
		nbProc = 2;
	}	

	public ArrayList<Processeur> recuitSimule(int nbTache, int nbProc, int temp, int borneInf, int borneSup) {

		double alpha = 0.99;
		int compteur= 0;
		// LPROC MEILLEURE SOLUTION
		lproc = new ArrayList<>();
		ArrayList<Processeur> lmax = new ArrayList<>();
		this.borneInf = borneInf;
		this.borneSup = borneSup;
		this.nbTache = nbTache;
		this.nbProc = nbProc;


		// initialisation des processeurs
		for (int i = 0; i < this.nbProc; i++)
		{
			this.lproc.add(new Processeur());
			lmax.add(new Processeur());
		}

		// initialisation des taches on met tout dans le premier proc
		for (int i = 0; i < this.nbTache; i++)
		{

			int range = this.borneSup-this.borneInf;
			int duree = (int)(Math.random() * (range)) + this.borneInf;

			this.lproc.get(0).add(new Tache(duree));
			lmax.get(0).add(new Tache(duree));
		}
		
		
		/*
		Tache t = new Tache(5);
		Tache t2 = new Tache(4);
		Tache t3 = new Tache(3);
		Tache t4 = new Tache(6);
		Tache t5 = new Tache(5);
		Tache t6 = new Tache(7);
		Tache t7 = new Tache(8);
		Tache t8 = new Tache(1);
		Tache t9 = new Tache(5);
		Tache t10 = new Tache(4);
		Processeur lt = new Processeur();
		lt.add(t);
		lt.add(t2);
		lt.add(t3);
		lt.add(t4);
		lt.add(t5);
		lt.add(t6);
		lt.add(t7);
		lt.add(t8);
		lt.add(t9);
		lt.add(t10);
		
		for (Tache ttt : lt.getListTache()) {
			this.lproc.get(0).add(ttt);
			lmax.get(0).add(ttt);
		}*/

		
		// temperature init
		this.temperature = temp;

		double i=this.temperature;
		int changement = 0;

		// condition quand la temperature est trop basse
		while (i > 1) {
			// Modif elementaire
			ArrayList<Processeur> procCourant =  voisin(lmax);

			int variation = objectifOptiL3(procCourant) - objectifOptiL3(lmax);

			// Regle dacceptation
			if ( variation < 0  || ( variation > 0 && Math.random() < Math.exp(-variation / i) )) {
				lmax = procCourant;
			}
			if ( objectifOptiL3(lproc) >  objectifOptiL3(lmax) ) {
				lproc = lmax;
				// affiche le meilleur etat tous les 3 changements
				if ( changement == pas) {
					System.out.println(" \n \n TACHE  A L ITERATION NUMERO   " + compteur);
					for ( Processeur pp : lproc) {
						System.out.println("  \n proc  avec pour duree " + pp.getDuree());
						for ( Tache tt : pp.getListTache()) {
							System.out.print("   Tache  " + tt.getDuree());
						}
					}
					changement = 0;
				}
				changement++;
			}
			compteur++;
			i = i*alpha; 
		}
		
		System.out.println("\n  \nFIN --------------------- \n Nombre d'itération recuit " + compteur);
		return lproc;
	}



	public int objectifOptiL3(ArrayList<Processeur> proc) {

		// calcul  du plus long chemin
		int max = Integer.MIN_VALUE;
		int courant = 0;
		for (Processeur p : proc) {

			courant = p.getDuree();
			if ( max < courant) max = courant;
		}
		//System.out.println(" DUREEE   " + max);
		return max;
	}

	// methode aleatoire qui genere un cas different
	public ArrayList<Processeur> voisin( ArrayList<Processeur> proc) {

		int i = 0;
		ArrayList<Processeur> p =  new ArrayList<>();

		for ( Processeur pro : proc) {
			p.add(new Processeur());
			for (Tache task : pro.getListTache()) {
				int k = task.getDuree();
				p.get(i).add(new Tache(k ));
			}
			i++;
		}

		int r, rr;
		int size;
		Tache t ;

		size = p.size();

		r =  0 + (int)(Math.random() * (size - 0)) + 1;
		int compt= 0;
		// methode englobante inverser ou non les listes

		while (compt == 0 ) {
			if (Math.random() < 0.5) {
				inversion(p);
			}

			if (Math.random() > 0.5) {
				enlevage(p);
				compt++;
			} 
			if (Math.random() > 0.5) {  
				echange(p);
				compt++;
			}


		}
		return p;
	}

	public void inversion(ArrayList<Processeur> p ) {

		int proc1, proc2;
		int size;
		Tache t ;

		// on va lancer un rand sur chaque proc pour savoir si on va linverser
		for (int i =0; i < p.size(); i++) {

			// inversion de 2elements
			if (Math.random() > 0.75 && p.get(i).getListTache().size() > 1) {

				size = p.get(i).getListTache().size();

				proc1 =  0 + (int)(Math.random() * (size - 0));



				t = p.get(i).getListTache().get(proc1);

				// inversion r et son voisin r+1
				if ( Math.random() <  0.5  ) {
					if ( proc1 < size-1) {
						p.get(i).getListTache().set(proc1, p.get(i).getListTache().get( (proc1+1) ));
						p.get(i).getListTache().set((proc1+1) , t);
					} 
				} else {
					// inversion r et son voisin r-1
					if ( proc1 > 0) {
						p.get(i).getListTache().set(proc1, p.get(i).getListTache().get( (proc1-1) ));
						p.get(i).getListTache().set((proc1-1) , t);
					} 
				}
			}
		}
	}


	// methode 2 enlever un element et le mettre dans un autre proc 
	public void enlevage(ArrayList<Processeur> p ) {

		int size, proc1,proc2, rr, i = 0;
		Tache t;

		size = p.size();
		boolean vide = true;
		while (i < p.size() && vide) {
			if (p.get(i).getListTache().size() > 0) vide = false;
			i++;
		}

		if (!vide ) {

			proc1 =  0 + (int)(Math.random() * (size - 0));
			i = 0;
			while (p.get(proc1).getListTache().size() ==0)  {
				proc1 =  0 + (int)(Math.random() * (size - 0));
				i++;
			}

			rr = 0 + (int)(Math.random() * (p.get(proc1).getListTache().size() - 0));

			t = p.get(proc1).getListTache().get(rr);


			p.get(proc1).getListTache().remove(rr);

			proc2 =  0 + (int)(Math.random() * (size - 0));

			while ( proc2 == proc1) {
				proc2 =  0 + (int)(Math.random() * (size - 0));
				i++;
			}

			p.get(proc2).getListTache().add(t);
		}

	}



	// echange de tache  a la meme place entre  2 proc different
	public void echange(ArrayList<Processeur> p ) {



		int size, r, proc1, proc2, i =0;
		Tache t, t2;

		size = p.size();

		proc1 =  0 + (int)(Math.random() * (size - 0));

		while (p.get(proc1).getListTache().size() > 0 && i < p.size())  {
			proc1 =  0 + (int)(Math.random() * (size - 0));
			i++;
		}
		i = 0;
		proc2 =  0 + (int)(Math.random() * (size - 0));


		while ( proc2 == proc1 && p.get(proc2).getListTache().size() > 0 && i < p.size()) {
			proc2 =  0 + (int)(Math.random() * (size - 0));
			i++;
		}

		if (p.get(proc1).getListTache().size() >0  && p.get(proc2).getListTache().size() > 0) {
			//System.out.println(" WELLOU 2");
			r = 0 + (int)(Math.random() * (p.get(proc1).getListTache().size() - 0));

			t = p.get(proc1).getListTache().get(r);


			// si lemplacement r est plus grand que la taille du proc2 on ajoute a la fin
			if ( r >= p.get(proc2).getListTache().size()) {
				size = p.get(proc2).getListTache().size()-1;
				t2 = p.get(proc2).getListTache().get( size);
				p.get(proc2).getListTache().remove(size);
				p.get(proc2).getListTache().add(t);
			} else {
				t2 = p.get(proc2).getListTache().get(r);
				p.get(proc2).getListTache().set(r, t);
			}

			p.get(proc1).getListTache().set(r, t2);


		}

	}


	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public ArrayList<Processeur> getLproc() {
		return lproc;
	}

	public void setLproc(ArrayList<Processeur> lproc) {
		this.lproc = lproc;
	}


	public int getBorneInf() {
		return borneInf;
	}

	public void setBorneInf(int borneInf) {
		this.borneInf = borneInf;
	}

	public int getBorneSup() {
		return borneSup;
	}

	public void setBorneSup(int borneSup) {
		this.borneSup = borneSup;
	}

	public int getNbProc() {
		return nbProc;
	}

	public void setNbProc(int nbProc) {
		this.nbProc = nbProc;
	}

	public int getNbTache() {
		return nbTache;
	}

	public void setNbTache(int nbTache) {
		this.nbTache = nbTache;
	}




}
