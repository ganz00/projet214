package simulation;

import java.util.ArrayList;

import SimulationAppareil.Appareil;

public class Activite {

	public int Id_Activite ;
	public ArrayList<Appareil> listeAppareil;
	public Activite(int id_Activite) {
		super();
		Id_Activite = id_Activite;
		listeAppareil=new ArrayList<Appareil>();
	}
	
}
