package Model;

import java.util.ArrayList;

public class Processeur {
	
	protected ArrayList<Tache> lproc;
	
	public Processeur(ArrayList<Tache> lproc) {
		this.lproc = lproc;
	}
	
	public Processeur() {
		this.lproc = new ArrayList<>();
	}

	public ArrayList<Tache> getLproc() {
		return lproc;
	}

	public void setLproc(ArrayList<Tache> lproc) {
		this.lproc = lproc;
	}
	
	public void add(Tache t) {
		this.lproc.add(t);
	}
	
	public void remove(int i) {
		this.lproc.remove(i);
	}
	
	public int getDuree() {
		int duree= 0;
		for ( Tache t : lproc) {
			duree+= t.getDuree();
		}
		return duree;
	}

}
