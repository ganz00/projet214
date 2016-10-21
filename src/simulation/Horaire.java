package simulation;

public class Horaire {
	private int Debut;
	private int Fin;
	private String Nom;
	public int CalculHeure(int Debut,int Fin)
	{
		int Resultat= Fin -Debut;
		return Resultat;
	}
	public int getDebut() {
		return Debut;
	}
	public void setDebut(int debut) {
		Debut = debut;
	}
	public int getFin() {
		return Fin;
	}
	public void setFin(int fin) {
		Fin = fin;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}

}
