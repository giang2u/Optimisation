package Model;

public class Tache {
	
	protected int duree;
	public int numeroTache;
	
	public Tache( int d) {
		this.duree = d;
	}
	
	public Tache( int d, int i) {
		this.duree = d;
		this.numeroTache = i;
	}
	

	public int getNumeroTache() {
		return numeroTache;
	}

	public void setNumeroTache(int numeroTache) {
		this.numeroTache = numeroTache;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}
	
	/*public String toString() {
		StringBuilder sb = null;
		sb.append("Je suis la tache " + name + " avec une duree " + duree);
		return sb.toString();
	}*/
	
	

}
