package simulation;

import SimulationAppareil.*;




public  class Programme {
	public static int n = 4;
	public Activite Ete[][];
	public Activite Hivers[][];
	public Activite Automne[][];
	public Activite Printemps[][];
	public Activite dormir;
	public Activite dejeune;
	public Activite sieste;
	public Activite detente;
	public Activite preparer;
	public Activite douche;
	public Activite promenade;
	public Activite[] hazart1;
	public Activite[] hazart2;

	Chauffage chauffage;
	Cuisiniere cuisiniere;
	EauChaude Echaude;
	Eclairage eclair;
	Four four;
	LaveLinge lave;
	LaveVaissele lvaisselle;
	MicroOndes microOnde;
	Refrigerateur refrig;
	SecheLinge seLinge;
	Television television;

	public Programme() {
		super();
		declare();
		hazart1 = new Activite[10];
		hazart2 = new Activite[6];
		initE();
		setTab(Ete, dormir, dejeune, hazart1, hazart2);
	    initH();
	    setTab(Hivers, dormir, dejeune, hazart1, hazart2);
		initA();
		setTab(Automne, dormir, dejeune, hazart1, hazart2);
		initP();
		setTab(Printemps, dormir, dejeune, hazart1, hazart2);
	}
	

	public void initE() {
		dormir = new Activite(0);
		dormir.listeAppareil.add(refrig);
		dejeune = new Activite(1);
		dejeune.listeAppareil.add(refrig);
		dejeune.listeAppareil.add(cuisiniere);
		dejeune.listeAppareil.add(microOnde);
		dejeune.listeAppareil.add(television);
		Hazart(new int[] {2,3,1,2,2},0);
		Hazart2(new int[] {2,3,1},0);
	}

	public void initA() {
		dormir = new Activite(2);
		dormir.listeAppareil.add(refrig);
		dormir.listeAppareil.add(chauffage);
		dejeune = new Activite(3);
		dejeune.listeAppareil.add(refrig);
		dejeune.listeAppareil.add(cuisiniere);
		dejeune.listeAppareil.add(microOnde);
		dejeune.listeAppareil.add(television);
		dejeune.listeAppareil.add(chauffage);
		Hazart(new int[] {2,3,1,2,2},1);
		Hazart2(new int[] {2,3,1},1);

	}

	public void initP() {
		dormir = new Activite(4);
		dormir.listeAppareil.add(refrig);
		dormir.listeAppareil.add(chauffage);
		dejeune = new Activite(5);
		dejeune.listeAppareil.add(refrig);
		dejeune.listeAppareil.add(cuisiniere);
		dejeune.listeAppareil.add(microOnde);
		dejeune.listeAppareil.add(television);
		dejeune.listeAppareil.add(chauffage);
		Hazart(new int[] {2,3,1,2,2},2);
		Hazart2(new int[] {2,3,1},2);
	}

	public void initH() {
		dormir = new Activite(6);
		dormir.listeAppareil.add(refrig);
		dormir.listeAppareil.add(chauffage);
		dejeune = new Activite(7);
		dejeune.listeAppareil.add(refrig);
		dejeune.listeAppareil.add(cuisiniere);
		dejeune.listeAppareil.add(microOnde);
		dejeune.listeAppareil.add(television);
		dejeune.listeAppareil.add(chauffage);
		Hazart(new int[] {2,3,1,2,2},3);
		Hazart2(new int[] {2,3,1},3);
	}

	public Activite[][] getsaison(int id) {
		switch (id) {
		case 1:
			return Ete;
		case 2:
			return Hivers;
		case 3:
			return Automne;
		case 4:
			return Printemps;
		default:
			return null;
		}

	}

	public void declare() {
		Ete = new Activite[4][];
		Hivers = new Activite[4][];
		Automne = new Activite[4][];
		Printemps = new Activite[4][];
		
		chauffage = new Chauffage();
		cuisiniere = new Cuisiniere();
		eclair = new Eclairage();
		Echaude = new EauChaude();
		four = new Four();
		lave = new LaveLinge();
		lvaisselle = new LaveVaissele();
		microOnde = new MicroOndes();
		refrig = new Refrigerateur();
		seLinge = new SecheLinge();
		television = new Television();
	}

	// prend en parametre le tableau des frequences
	public void Hazart(int [] T,int ind) {
		sieste = new Activite(20);
		detente = new Activite(21);
		preparer = new Activite(22);
		douche = new Activite(23);
		promenade = new Activite(24);
		sieste.listeAppareil.add(refrig);
		detente.listeAppareil.add(refrig);
		detente.listeAppareil.add(television);
		detente.listeAppareil.add(eclair);
		preparer.listeAppareil.add(cuisiniere);
		preparer.listeAppareil.add(eclair);
		preparer.listeAppareil.add(four);
		preparer.listeAppareil.add(refrig);
		douche.listeAppareil.add(refrig);
		douche.listeAppareil.add(Echaude);
		promenade.listeAppareil.add(refrig);
		if(ind == 3 || ind == 2){
			detente.listeAppareil.add(chauffage);
			preparer.listeAppareil.add(chauffage);
			douche.listeAppareil.add(chauffage);
			promenade.listeAppareil.add(chauffage);
			sieste.listeAppareil.add(chauffage);
		}
		hazart1 = new Activite[10];
		Activite[] haza = {sieste, detente, preparer, douche, promenade};
		faire(T,haza);

	}

	public void Hazart2(int [] TT,int ind) {
		detente = new Activite(30);
		preparer = new Activite(31);
		douche = new Activite(32);
		detente.listeAppareil.add(refrig);
		detente.listeAppareil.add(television);
		detente.listeAppareil.add(eclair);
		preparer.listeAppareil.add(cuisiniere);
		preparer.listeAppareil.add(eclair);
		preparer.listeAppareil.add(four);
		preparer.listeAppareil.add(refrig);
		douche.listeAppareil.add(refrig);
		douche.listeAppareil.add(Echaude);
		if(ind == 3 || ind == 2){
			detente.listeAppareil.add(chauffage);
			preparer.listeAppareil.add(chauffage);
			douche.listeAppareil.add(chauffage);
		}
		Activite[] haza = {detente, preparer, douche};
		faire2(TT,haza);

	}
	public void faire(int [] T,Activite[] haza){
		int b=0;
		int a=0;
		for(int i = 0;i<T.length;i++){
			b=0;
			for(int j=T[i];j>0;j--){
				hazart1[a+b] = haza[i];
				b++;
			}
			a=a+T[i];
		}
	}
	public void faire2(int [] T,Activite[] haza){
		int b=0;
		int a=0;
		for(int i = 0;i<T.length;i++){
			b=0;
			for(int j=T[i];j>0;j--){
				hazart2[a+b] = haza[i];
				b++;
			}
			a=a+T[i];
		}
	}
	
	public void setTab(Activite[][] A,Activite dormir,Activite reveil,Activite[] haz1,Activite[] haz2){
	 Activite[]	Tdormir={dormir};
	 Activite[]	Treveil={reveil};
	 A[0] = Tdormir;
	 A[1] = Treveil;
	 A[2] = haz1;
	 A[3] = haz2;
	}

}
