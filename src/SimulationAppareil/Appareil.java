package SimulationAppareil;

import simulation.Horaire;
public abstract class Appareil {
	protected String Nom;
	public double conso;
	protected int consoHoraire;
	protected int [] consommation;
	protected Horaire horaire; 
	public int num;
	public abstract int [] consommation(int id);
	
	
	
}
