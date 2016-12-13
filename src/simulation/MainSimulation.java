package simulation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import SimulationAppareil.Appareil;
import SimulationAppareil.Chauffage;
import SimulationAppareil.Cuisiniere;
import SimulationAppareil.EauChaude;
import SimulationAppareil.Refrigerateur;
import simulation.DataSourceSingleConnection;

public class MainSimulation {
	public static Date date = new Date();
	public static Heure h = new Heure();
	public static Saison saison[] = new Saison[4];
	public static Appareil appareil;
	public static int boo = 0;
	public static DataSourceSingleConnection dataSource;
	public static void main(String[] args) throws SQLException, IOException {
		Maison maison = new Maison();
		File RepTravail = new File(System.getProperty("user.dir"));
		Heure hdebut = new Heure();
		Heure hfin = new Heure();
		
		// Création d'une instance de la base de données
		
			dataSource = new DataSourceSingleConnection();
			dataSource.setDriver("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost:8889/Simulateur");
			dataSource.setUser("root");
			dataSource.setPassword("root");
		
		
		
		// Création des Activités
		Activite activite1 = new Activite(1);
		Activite activite2 = new Activite(2);
		Activite activite3 = new Activite(3);
		Activite activite4 = new Activite(4);
		
		// Création des appareils et association à une activité
		Chauffage chauf = new Chauffage();
		Cuisiniere cuis = new Cuisiniere();
		EauChaude eau = new EauChaude();
		Refrigerateur refrigerateur = new Refrigerateur();
		activite1.listeAppareil.add(chauf);
		activite1.listeAppareil.add(cuis);
		activite1.listeAppareil.add(refrigerateur);
		activite2.listeAppareil.add(cuis);
		activite2.listeAppareil.add(eau);
		activite2.listeAppareil.add(refrigerateur);
		activite3.listeAppareil.add(eau);
		activite3.listeAppareil.add(refrigerateur);
		activite4.listeAppareil.add(refrigerateur);
		
		// Création d'une liste d'activités
		ArrayList<Activite> liste = new ArrayList<Activite>();
		ArrayList<Activite> liste2 = new ArrayList<Activite>();
		liste.add(activite1);
		liste.add(activite2);
		liste.add(activite3);
		liste2.add(activite1);
		liste2.add(activite2);
		liste2.add(activite4);
		
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
		Horaire horaire = new Horaire(hdebut, hfin);
		// intialise les saison
		InitSaison(saison);
		String tableau[] = { "Janvier", "Fervrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre",
				"Octobre", "Novembre", "Decembre" };
		File f = new File(RepTravail, tableau[(date.mois - 1) % 12] + date.annee + ".txt");
		
		try (PrintWriter pw = new PrintWriter(new FileWriter(f, true))) {
		while (date.mois == 1) {
			// permet d'organiser la date
			if ((h.heure >= 22 && h.heure <= 23) || (h.heure >= 0 && h.heure <= 8)) {
				System.out.println("dormir");
				DormirReveil(activite1, date, h.heure,pw);
			} else if (h.heure > 8 && h.heure <= 10) {
				System.out.println("petit dejeuner");
				DormirReveil(activite1, date, h.heure,pw);
			} else if (h.heure > 10 && h.heure <= 19) {
				System.out.println("hasart1");
				Hasard(liste,date,h.heure,pw);
			} else if (h.heure > 19 && h.heure < 22) {
				System.out.println("hasart2");
				Hasard(liste2,date,h.heure,pw);
			}
			calcul();
		}
		dataSource.closeConnection();
	}
		
}
	public static ArrayList<Activite> remplirListeActivite(Activite activite){
		ArrayList<Activite> liste = new ArrayList<Activite>();
		liste.add(activite);
		return liste;
		
	}
public static void InsertionBD(int num,int jour,int mois,int annee,int heure,int qtite,String saison) throws SQLException{
	Connection con = dataSource.getConnection();
	Statement sta = con.createStatement();
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
			      preparedStmt.setString(7, saison);

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

	public static void Hasard(ArrayList<Activite> liste, Date d, int h1,PrintWriter pw) throws SQLException {
		int id_activite = (int) (Math.random() * (3));
		Activite activite = liste.get(id_activite);
		DormirReveil(activite, d, h1, pw);

	}

	public static void DormirReveil(Activite activite, Date d, int heure,PrintWriter pw) throws SQLException {
		Iterator<Appareil> iter = activite.listeAppareil.iterator();
		int s = DetermineSaison(date.mois);
		String newLine = System.getProperty("line.separator");
		while (iter.hasNext()) {
			Appareil A = iter.next();
			int consomation = (int) A.conso;
			int id = A.num;
			//pw.write(saison[s].getNom() + date.annee + "/" + date.mois + "/" + date.jour + ";" + h.heure
			//		 + ";" + id + ";" + consomation + " " + newLine);
			InsertionBD(id, d.jour, d.mois, d.annee,h.heure, consomation,saison[s].getNom());
		}
	}

	public static Saison[] InitSaison(Saison[] saison2) {


		Date date11 = new Date();
		Date date2 = new Date();
		Date date3 = new Date();
		Date date4 = new Date();
		Date date5 = new Date();
		Date date6 = new Date();
		Date date7 = new Date();
		Date date8 = new Date();
		date11.mois = 6;
		date2.mois = 9;
		date3.mois = 3;
		date4.mois = 6;
		date5.mois = 12;
		date6.mois = 3;
		date7.mois = 9;
		date8.mois = 12;
		saison2[0] = new Saison();
		saison2[1] = new Saison();
		saison2[2] = new Saison();
		saison2[3] = new Saison();
		saison2[0].setNom("ete");
		saison2[1].setNom("prinptemps");
		saison2[2].setNom("hiver");
		saison2[3].setNom("autonone");
		saison2[0].setDebut(date11.mois);
		saison2[0].setFin((date2.mois));
		saison2[1].setDebut((date3.mois));
		saison2[1].setFin((date4.mois));
		saison2[2].setDebut((date5.mois));
		saison2[2].setFin((date6.mois));
		saison2[3].setDebut((date7.mois));
		saison2[3].setFin((date8.mois));
		saison2[0].setNumero(0);
		saison2[1].setNumero(1);
		saison2[2].setNumero(2);
		saison2[3].setNumero(3);
		return saison2;

	}
}
