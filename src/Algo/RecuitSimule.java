package Algo;

import java.util.ArrayList;

import Model.Processeur;
import Model.Tache;

public class RecuitSimule {

	protected int temperature;
	protected ArrayList<Processeur> lproc;
	protected ArrayList<Tache> ltache;


	public RecuitSimule() {
		this.temperature = 0;
		lproc = new ArrayList<>();
		ltache = new ArrayList<>();
	}

	public RecuitSimule(int temp, ArrayList<Processeur> lproc,ArrayList<Tache> ltache) {
		this.temperature = temp;
		lproc = new ArrayList<>();
		ltache = new ArrayList<>();
		this.lproc = lproc;
		this.ltache = ltache;
	}	

	public ArrayList<Processeur> recuitSimule(ArrayList<Tache> t, int nbProc, int temp) {

		double alpha = 0.95;

		// LPROC MEILLEURE SOLUTION

		lproc = new ArrayList<>();
		if ( nbProc > 0) {

			if (nbProc == 1) {
				this.lproc.add(new Processeur());
				for (Tache tt : t) {
					// solution init on met tous dans un seul proc
					this.lproc.get(0).add(tt);;
				}
			} else {


				// SOLUTION INIT on repartit les taches equitablement
				for ( int i = 0 ;  i < nbProc; i++) {
					this.lproc.add(new Processeur());
				}

				// nombre delement dans chaque proc
				int nb =  t.size()/nbProc;

				//System.out.println("NOMBRE   " + nb);
				int j = 0, k = 0;

				// on met dans nbProc - 1 , nb tache
				for (int i = 0;   i < (nbProc -1); i++) {
					// borne darret
					k = i*nb + nb;

					for (j = (k-nb); j < k ; j++) {

						this.lproc.get(i).add(t.get( j ));
					}
				}

				System.out.println("nbproc "+ lproc.size());
				// dans le dernier proc on met le reste  car possibilite de dimpair

				for (int i = k;  i < t.size(); i++) {
					this.lproc.get(nbProc-1).add(t.get(i));

				}


				// temperature init
				this.temperature = temp;

				double i=temp;
				boolean fige = false;

				while (i > 1) {




					// Modif elementaire
					ArrayList<Processeur> procCourant =  voisin(lproc);



					int variation = objectifOptiL3(procCourant) - objectifOptiL3(lproc);
					/*	if ( i == 100000) {
						System.out.println(" \n    Variationoooooooo " + variation);
						for ( Processeur pp : procCourant) {

							System.out.println("\nproc COURANT  " + pp.getDuree());
							for ( Tache tt : pp.getLproc()) {
								System.out.print("    Tache  " + tt.getDuree());
							}
						}

						for ( Processeur ppp : lproc) {

							System.out.println("\nproc PAS COURANT  " + ppp.getDuree());
							for ( Tache ttt : ppp.getLproc()) {
								System.out.print("    Tache  " + ttt.getDuree());
							}
						}


					}*/
					// Regle dacceptation
					if ( variation < 0  || ( variation > 0 && Math.random() < Math.exp(-variation / i) )) {
						System.out.println(" RANDOM  " + Math.random() + "     Variatio   " + variation + "      EXP " + Math.exp(-variation / i));
						lproc = procCourant;
					}
					i = i*alpha; 
				}
			}
		}
		/*for ( Processeur pp : lproc) {

			System.out.println("\nproc PAS COURANT  " + pp.getDuree());
			for ( Tache tt : pp.getLproc()) {
				System.out.print("    Tache  " + tt.getDuree());
			}
		}*/

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
			for (Tache task : pro.getLproc()) {
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
			if (Math.random() > 0.75 && p.get(i).getLproc().size() > 1) {

				size = p.get(i).getLproc().size();

				proc1 =  0 + (int)(Math.random() * (size - 0));



				t = p.get(i).getLproc().get(proc1);

				// inversion r et son voisin r+1
				if ( Math.random() <  0.5  ) {
					if ( proc1 < size-1) {
						p.get(i).getLproc().set(proc1, p.get(i).getLproc().get( (proc1+1) ));
						p.get(i).getLproc().set((proc1+1) , t);
					} 
				} else {
					// inversion r et son voisin r-1
					if ( proc1 > 0) {
						p.get(i).getLproc().set(proc1, p.get(i).getLproc().get( (proc1-1) ));
						p.get(i).getLproc().set((proc1-1) , t);
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
			if (p.get(i).getLproc().size() > 0) vide = false;
			i++;
		}

		if (!vide ) {

			proc1 =  0 + (int)(Math.random() * (size - 0));
			i = 0;
			while (p.get(proc1).getLproc().size() ==0)  {
				proc1 =  0 + (int)(Math.random() * (size - 0));
				i++;
			}

			rr = 0 + (int)(Math.random() * (p.get(proc1).getLproc().size() - 0));

			t = p.get(proc1).getLproc().get(rr);


			p.get(proc1).getLproc().remove(rr);

			proc2 =  0 + (int)(Math.random() * (size - 0));

			while ( proc2 == proc1) {
				proc2 =  0 + (int)(Math.random() * (size - 0));
				i++;
			}

			p.get(proc2).getLproc().add(t);
		}

	}



	// echange de tache  a la meme place entre  2 proc different
	public void echange(ArrayList<Processeur> p ) {



		int size, r, proc1, proc2, i =0;
		Tache t, t2;

		size = p.size();

		proc1 =  0 + (int)(Math.random() * (size - 0));

		while (p.get(proc1).getLproc().size() > 0 && i < p.size())  {
			proc1 =  0 + (int)(Math.random() * (size - 0));
			i++;
		}
		i = 0;
		proc2 =  0 + (int)(Math.random() * (size - 0));


		while ( proc2 == proc1 && p.get(proc2).getLproc().size() > 0 && i < p.size()) {
			proc2 =  0 + (int)(Math.random() * (size - 0));
			i++;
		}

		if (p.get(proc1).getLproc().size() >0  && p.get(proc2).getLproc().size() > 0) {
			//System.out.println(" WELLOU 2");
			r = 0 + (int)(Math.random() * (p.get(proc1).getLproc().size() - 0));

			t = p.get(proc1).getLproc().get(r);







			// si lemplacement r est plus grand que la taille du proc2 on ajoute a la fin
			if ( r >= p.get(proc2).getLproc().size()) {
				size = p.get(proc2).getLproc().size()-1;
				t2 = p.get(proc2).getLproc().get( size);
				p.get(proc2).getLproc().remove(size);
				p.get(proc2).getLproc().add(t);
			} else {
				t2 = p.get(proc2).getLproc().get(r);
				p.get(proc2).getLproc().set(r, t);
			}

			p.get(proc1).getLproc().set(r, t2);


		}

	}

	public ArrayList<Tache> getLtache() {
		return ltache;
	}

	public void setLtache(ArrayList<Tache> ltache) {
		this.ltache = ltache;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public ArrayList<Processeur> getLproc() {
		return lproc;
	}

	public void setLproc(ArrayList<Processeur> lproc) {
		this.lproc = lproc;
	}



}
