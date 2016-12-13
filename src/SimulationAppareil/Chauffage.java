package SimulationAppareil;

public class Chauffage extends Appareil{
	public Chauffage( )
	{
		super.conso=14400;	
		super.Nom="chauffage";
		super.num=1;
		}
	@Override
	public int[] consommation(int id ) {
		int [] tab1= {1,3,4,15,16};
		int [] tab11=new int[20];
		int [] tab2= {0,8,9,10};
		int [] tab3= {8,10,17,18,23};
		int [] tab4= {7,8,15,22,23};
		id=id+1;
		if(id==1){
			for(int i = 0;i<tab1.length;i++){


			 tab11[i]=tab1[i];
			
			}
			
		}
		else if(id==2){
			for(int i = 0;i<tab2.length;i++){
				 tab11[i]=tab2[i];
				}
				
		}
else if(id==3){
	for(int i = 0;i<tab3.length;i++){
		 tab11[i]=tab3[i];
		}
		
		}
else if(id==4){
	for(int i = 0;i<tab4.length;i++){
		 tab11[i]=tab4[i];
		}
	
	
}
	return tab11;	
	}
	

	
}
