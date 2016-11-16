package simulation;

public class Horaire {
	private Heure Debut;
	private Heure Fin;
	private String Nom;
	public Horaire(Heure Debut,Heure Fin){
		this.Debut=Debut;
		this.Fin=Fin;
		}
	public double CalculHeure(Heure Debut,Heure Fin)
	{
		double Resultat=( Fin.seconde/3600+Fin.heure+Fin.minute/60)
				- (Debut.seconde/3600+Debut.heure+Debut.minute/60);
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
