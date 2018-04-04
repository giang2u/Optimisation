package Model;

import java.util.ArrayList;

public class Processeur {
	
	protected ArrayList<Tache> lTache;
	
	public Processeur(ArrayList<Tache> lproc) {
		this.lTache = lproc;
	}
	
	public Processeur() {
		this.lTache = new ArrayList<>();
	}

	public ArrayList<Tache> getListTache() {
		return lTache;
	}

	public void setLproc(ArrayList<Tache> lproc) {
		this.lTache = lproc;
	}
	
	public void add(Tache t) {
		this.lTache.add(t);
	}
	
	public void remove(int i) {
		this.lTache.remove(i);
	}
	
	public int getDuree() {
		int duree= 0;
		for ( Tache t : lTache) {
			duree+= t.getDuree();
		}
		return duree;
	}

}
