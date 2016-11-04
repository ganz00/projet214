package simulation;

public class Horaire {
	private Heure Debut;
	private Heure Fin;
	private String Nom;
	public int CalculHeure(int Debut,int Fin)
	{
		int Resultat= Fin - Debut;
		return Resultat;
	}
	public Heure getDebut() {
		return Debut;
	}
	public void setDebut(Heure debut) {
		Debut = debut;
	}
	public Heure getFin() {
		return Fin;
	}
	public void setFin(Heure fin) {
		Fin = fin;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}

}
