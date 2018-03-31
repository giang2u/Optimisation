package Model;

public class Tache {
	
	protected int duree;
	
	public Tache( int d) {
		this.duree = d;
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
