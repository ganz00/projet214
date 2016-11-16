package simulation;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class mainSimulation {

	public static void main(String[] args) {
		
		// defintion des variables 
		Maison maison = new Maison();
		File RepTravail = new File(System.getProperty("user.dir"));
		File f = new File(RepTravail,"simulation.txt");
		String newLine = System.getProperty("line.separator");
		Cuisiniere cuisiniere = new Cuisiniere() ;
		Heure hdebut =new Heure();
		Heure hfin = new Heure();
		hdebut.heure=0;
		hdebut.minute=0;
		hdebut.seconde=0;
		hfin.heure=0;
		hfin.minute=0;
		hfin.seconde=0;
		Chauffage chauffage = new Chauffage( );
		Eclairage eclairage = new Eclairage() ;
		Refrigerateur refrigerateur = new Refrigerateur();
		SecheLinge sechelinge = new SecheLinge() ;
		Television television = new Television() ;
		MicroOndes micro = new MicroOndes() ;
		LaveLinge lavelinge = new LaveLinge() ;
		LaveVaissele lavaissele = new LaveVaissele() ;
		Date date = new Date();
		date.annee=2016;
		date.mois=1;
		date.jour=1;
		Horaire horaire= new Horaire(hdebut,hfin) ;
		Personne p1 = new Personne();
		Personne p2 = new Personne();
	    Saison saison = new Saison() ;
	    maison.listAppareil.add(lavaissele);
		maison.listAppareil.add(lavelinge);
		maison.listAppareil.add(television);
		maison.listAppareil.add(sechelinge);
		maison.listAppareil.add(refrigerateur);
		maison.listAppareil.add(eclairage);
		maison.listAppareil.add(cuisiniere);
		maison.listAppareil.add(chauffage);
		maison.listAppareil.add(micro);
		int a =0;
		int b=0;
		int c=0;
		double resultat = 0;
		try(PrintWriter pw = new PrintWriter(new FileWriter(f,true)))
		{
			
		while(hfin.heure<8640)
		{
			hfin.heure+=1;
		Iterator<Appareil> iter = maison.listAppareil.iterator();
			
			//if(hfin.heure<24){
			/*resultat=objet.conso*horaire.CalculHeure(hdebut, hfin);
		    pw.write("consommation horaire");
			pw.write(objet.Nom +"a consomme"+ resultat + newLine);
			}
			else if (hfin.heure%24==0){
				resultat=objet.conso*horaire.CalculHeure(hdebut, hfin);
				date.jour+=1;
				pw.write("consommation journaliere");
				pw.write(objet.Nom +"a consomme"+resultat+ newLine);
			}
			else if(date.jour%30==0){
				resultat=objet.conso*horaire.CalculHeure(hdebut, hfin);
				date.mois+=1;
				pw.write("consommation menstruelle");
				pw.write(objet.Nom +"a consomme"+resultat+ newLine);
			}
			else if(date.mois%12==0)
			{
				resultat=objet.conso*horaire.CalculHeure(hdebut, hfin);
				date.annee+=1;
				pw.write("consommation annuelle");
				pw.write(objet.Nom +"a consomme"+resultat+ newLine);
				
			*/
				 a =hfin.heure;
				if(a%24!=0&&a%720!=0){
					while(iter.hasNext()){
						
						Appareil objet = iter.next();
				
				resultat=objet.conso*horaire.CalculHeure(hdebut, hfin);
				pw.write(date.annee+" "+date.mois+" "+date.jour+" "+hdebut.heure+" "+"-"+" "+hfin.heure+" "+objet.Nom +" "+resultat+" "+ newLine);
					}
					}
				else if(date.mois%12==0){
					System.out.println(date.annee);
					date.mois=1;
					date.annee+=1;
	             while(iter.hasNext()){
						
						Appareil objet = iter.next();
				
				resultat=objet.conso*horaire.CalculHeure(hdebut, hfin);
				pw.write(date.annee+" "+date.mois+" "+date.jour+" "+hdebut.heure+" "+"-"+" "+hfin.heure+" "+objet.Nom +" "+resultat+" "+ newLine);
				}
	}
				
				else if(a%720==0){
					date.jour=1;
					date.mois+=1;
					while(iter.hasNext()){
						
						Appareil objet = iter.next();
				
				resultat=objet.conso*horaire.CalculHeure(hdebut, hfin);
				pw.write(date.annee+" "+date.mois+" "+date.jour+" "+hdebut.heure+" "+"-"+" "+hfin.heure+" "+objet.Nom +" "+resultat+" "+ newLine);
					}}
					
					
			else if(a%24==0){
				date.jour+=1;
				 
				while(iter.hasNext()){
					
					Appareil objet = iter.next();
			
				
	
				resultat=objet.conso*horaire.CalculHeure(hdebut, hfin);
				pw.write(date.annee+" "+date.mois+" "+date.jour+" "+hdebut.heure+" "+"-"+" "+hfin.heure+" "+objet.Nom +" "+resultat+" "+ newLine);
				}
				}
	
			
				//}
		
			
		
	
			hdebut.heure+=1;
		}
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
	    catch(IOException e){
	    	System.out.println(e.getMessage());
	    }
		System.out.println(date.annee);
}
	
	
	}
