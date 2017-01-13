package simulation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import SimulationAppareil.Appareil;
import SimulationAppareil.Incident;

public class MainSimulation {
	public static Date date = new Date();
	public static Heure h = new Heure();
	public static Saison saison[] = new Saison[4];
	public static Appareil appareil;
	public static int boo = 0;
	public static DataSourceSingleConnection dataSource;
	public static Incident incident = new Incident();
	public static Incident incident2 = new Incident();
	
	public static void main(String[] args) throws SQLException, IOException {
		File RepTravail = new File(System.getProperty("user.dir"));
		Heure hdebut = new Heure();
		Heure hfin = new Heure();
		
		// Création d'une instance de la base de données
		
			dataSource = new DataSourceSingleConnection();
			dataSource.setDriver("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost:3306/simulateur");
			dataSource.setUser("root");
			dataSource.setPassword("");
		
		// Création des Activités
		Programme pgr = new Programme();
		
		// Création des incidents
		incident.annee = 2016;
		incident.heure = 14;
		incident.valeur = 300;
		incident.jour = 14;
		incident.mois = 1;
		
		incident2.annee = 2016;
		incident2.heure = 15;
		incident2.valeur = 250;
		incident2.jour = 16;
		incident2.mois = 1;
		
		
		
		// Listes des incidents
		ArrayList<Incident> listesIncident = new ArrayList<Incident>();
		listesIncident.add(incident);
		listesIncident.add(incident2);
		
		// Création des appareils et association à une activité

		
		// Création d'une liste d'activités
		
		//ArrayList<Activite> liste=	remplirListeActivite(activite1);
		hdebut.heure = 0;
		hdebut.minute = 0;
		hdebut.seconde = 0;
		hfin.heure = 0;
		hfin.minute = 0;
		hfin.seconde = 0;
		date.annee = 2016;
		date.mois = 1;
		date.jour = 1;
		// intialise les saison
		InitSaison();
		String tableau[] = { "Janvier", "Fervrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre",
				"Octobre", "Novembre", "Decembre" };
		File f = new File(RepTravail, tableau[(date.mois - 1) % 12] + date.annee + ".txt");
		
		try (PrintWriter pw = new PrintWriter(new FileWriter(f, true))) {
		while (date.mois == 1) {
			int saison = DetermineSaison(date.mois);
			for(Incident incident : listesIncident) {
				if ((incident.heure == h.heure) && (incident.mois == date.mois) && (incident.jour == date.jour)) {
					
					InsertionBD(99, incident.jour, incident.mois, incident.annee, incident.heure, incident.valeur, saison);
				}
			}
			System.out.println("Merci");
			
			// permet d'organiser la date
				if ((h.heure >= 22 && h.heure <= 23) || (h.heure >= 0 && h.heure <= 8)) {
					DormirReveil(pgr.getsaison(saison)[0][0], date, h.heure,pw);
				} else if (h.heure > 8 && h.heure <= 10) {
					DormirReveil(pgr.getsaison(saison)[1][0], date, h.heure,pw);
				} else if (h.heure > 10 && h.heure <= 19) {
					Hasard(pgr.getsaison(saison)[2],date,h.heure,pw,10);
				} else if (h.heure > 19 && h.heure < 22) {
					Hasard(pgr.getsaison(saison)[3],date,h.heure,pw,6);
				}
				calcul();
			}
		}
		dataSource.closeConnection();
}
	
	public static ArrayList<Activite> remplirListeActivite(Activite activite){
		ArrayList<Activite> liste = new ArrayList<Activite>();
		liste.add(activite);
		return liste;
		
	}
	public static void InsertionBD(int num,int jour,int mois,int annee,int heure,int qtite,int saison) throws SQLException{
	Connection con = dataSource.getConnection();
	String query = " insert into consommation (id_appareil, jour, mois, annee, heure, consomation,saison)"
			        + " values (?, ?, ?, ?, ?, ?, ?)";

			      // Donn�es � ins�rer dans la base de donn�es
			      PreparedStatement preparedStmt = con.prepareStatement(query);
			      preparedStmt.setInt(1, num);
			      preparedStmt.setInt(2, jour);
			      preparedStmt.setInt (3, mois);
			      preparedStmt.setInt(4, annee);
			      preparedStmt.setInt(5, heure);
			      preparedStmt.setInt(6, qtite);
			      preparedStmt.setInt(7, saison);

			      // Ex�cution de la requ�te
			      preparedStmt.execute();
	
}
	public static int DetermineSaison(int mois) {
		int numero = 0;

		if (mois == 12 || mois <= 3) {
			numero = 2;
		} else if (mois > 3 && mois <= 6) {
			numero = 1;
		} else if (mois > 6 && mois <= 9) {
			numero = 0;
		} else if (mois > 9 && mois <= 12) {
			numero = 3;
		}

		return numero;
	}

	public static void calcul() {

		if (h.heure < 23) {
			h.heure++;
		} else {

			h.heure = 0;
			if (date.jour < 30) {
				boo = 0;
				date.jour++;
			} else {
				boo = 1;
				date.jour = 1;
				if (date.mois < 12) {
					date.mois++;
				} else {
					date.annee++;
				}
			}
		}

	}

	public static void Hasard(Activite[] liste, Date d, int h1,PrintWriter pw,int taille) throws SQLException {
		int id_activite = (int) (Math.random() * (taille));
		Activite activite = liste[id_activite];
		DormirReveil(activite, d, h1, pw);

	}

	public static void DormirReveil(Activite activite, Date d, int heure,PrintWriter pw) throws SQLException {
		Iterator<Appareil> iter = activite.listeAppareil.iterator();
		int s = DetermineSaison(date.mois);
		while (iter.hasNext()) {
			Appareil A = iter.next();
			int consomation = (int) A.conso;
			int id = A.num;
			
			InsertionBD(id, d.jour, d.mois, d.annee,h.heure, consomation,saison[s].getNumero()+1);
		}
	}

	public static void InitSaison() {
		Date date1 = new Date();
		Date date2 = new Date();
		Date date3 = new Date();
		Date date4 = new Date();
		Date date5 = new Date();
		Date date6 = new Date();
		Date date7 = new Date();
		Date date8 = new Date();
		date1.mois = 6;
		date2.mois = 9;
		date3.mois = 3;
		date4.mois = 6;
		date5.mois = 12;
		date6.mois = 3;
		date7.mois = 9;
		date8.mois = 12;
		saison[0] = new Saison();
		saison[1] = new Saison();
		saison[2] = new Saison();
		saison[3] = new Saison();
		saison[0].setNom("ete");
		saison[1].setNom("prinptemps");
		saison[2].setNom("hiver");
		saison[3].setNom("autonone");
		saison[0].setDebut(date1.mois);
		saison[0].setFin((date2.mois));
		saison[1].setDebut((date3.mois));
		saison[1].setFin((date4.mois));
		saison[2].setDebut((date5.mois));
		saison[2].setFin((date6.mois));
		saison[3].setDebut((date7.mois));
		saison[3].setFin((date8.mois));
		saison[0].setNumero(0);
		saison[1].setNumero(1);
		saison[2].setNumero(2);
		saison[3].setNumero(3);

	}
}
