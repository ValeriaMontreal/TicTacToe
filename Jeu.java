
public class Jeu implements TicTacToe {
	private String[] grilleText;      //Version locale de la grille
	private int tour =0;		      //Combien de coups joués par X
	private boolean termine = false;  //Il y a un gagnant ou une nulle

// Constructeur vide pour créer dynamiquement le tableau et faire initialiser le jeu.

    public Jeu() {
    	grilleText = new String[9];
    	initialise();
    }

// Initialiser une partie, soit lors de la créatioin de l'objet, soit
// lorsque le joueur va vouloir une nouvelle partie.
    public void initialise(){
    	for(int i = 0 ; i<9; i++){
    		grilleText[i]="";
    	}
    	tour = 0;
    	termine = false;
    }

// Méthode appelée par l'interface graphique pour informer l'objet Jeu
// du choix de la case par le joueur X.
    public void setX( int cellule){
    	grilleText[cellule] = "X";
    	tour ++;
    }

// Fonction qui retourne le poid numérique associé à une valeur dans une case.
// Valeur = X, poid = 1, valeur = O, poid = -1 et la chaîne vide a un poid = 0.
    private int lePoids(int cellule){
    	if(grilleText[cellule].equals("X"))
    		return 1;
    	else
    		if(grilleText[cellule].equals("O"))
    			return -1;
    		else
    			return 0;
    }

    private void lesPoids(int[] solutions){
    	int n;

    	for(int j = 0; j<3; j++)
    	{	n = 0;
    		for(int i=0; i<3; i++)
    			n += lePoids(i+ j*3);
	    	solutions[j] = n;
    	}

    	for(int j = 0; j<3; j++)
    	{	n = 0;
    		for(int i=0; i<3; i++)
    			n += lePoids(i*3+ j);
	    	solutions[j+3] = n;
    	}

    	n=0;
    	for(int i = 0; i <3 ; i++)
    		n+=lePoids(i*4);
    	solutions[6] = n;

    	n = 0;
    	for(int i = 0; i <3 ; i++)
    		n+=lePoids((i+1)*2);
		solutions[7] = n;
    }

    public int getO(){
    	int[]poids = new int[8];
    	int ch = -1;
    	if(tour==1){
    		if(grilleText[4].equals("X"))
    		{
    			grilleText[0]="O";
    			return 0;
    		}
    		else{
    			grilleText[4]="O";
    			return 4;
    		}
    	}
    	if(tour==2){                     // X a jouer 2 fois et O une seule.
    		lesPoids(poids);
    		for(int i = 0; i<8; i++){
    			if(poids[i] == 2){       // il y a 2 X et une vide
    				int n = lIndice(i);  // l'indice de la case vide
    				grilleText[n] = "O"; // on y place O pour empêcher la
    				return n;            // victoire de X.
    			}
    		}

    		if(!grilleText[0].equals("") && !grilleText[4].equals("") && !grilleText[8].equals(""))
    			if(grilleText[4].equals("O"))
    			{
    				grilleText[1]="O";
    				return 1;
    			}
    			else
    			{
    				grilleText[2]="O";
    				return 2;
    			}
    		else
    			if(!grilleText[6].equals("") && !grilleText[4].equals("") && !grilleText[2].equals(""))
    				if(grilleText[4].equals("O"))
    				{
    					grilleText[1]="O";
    					return 1;
    				}
    				else
    				{
    					grilleText[2]="O";
    					return 2;
    				}
    				else
    					if(grilleText[4].equals("O")){
    						if(grilleText[0].equals("X"))
    						{	if( grilleText[7].equals("X")){
    								grilleText[6]="O";
    								return 6;
    							}
    							else
    							if( grilleText[5].equals("X")){
    								grilleText[2]="O";
    								return 2;
    							}
    						}
    						else
    							if(grilleText[8].equals("X"))
    							{	if (grilleText[1].equals("X")){
    									grilleText[2]="O";
    									return 2;
    								}
    								else
    									if (grilleText[3].equals("X")){
    										grilleText[6]="O";
    										return 6;
    									}
    							}
        						else
    								if(grilleText[2].equals("X"))
    								{	if (grilleText[7].equals("X")){
    										grilleText[8]="O";
    										return 8;
    									}
    									else
    										if (grilleText[3].equals("X")){
    											grilleText[0]="O";
    											return 0;
    										}
    								}

        							else
    									if(grilleText[6].equals("X"))
    									{	if (grilleText[1].equals("X")){
    											grilleText[0]="O";
    											return 0;
    										}
    										else
    											if (grilleText[5].equals("X")){
    												grilleText[8]="O";
    												return 8;
    											}
    									}
    									else
    										if(grilleText[1].equals("X"))
    										{
    											if(grilleText[5].equals("X"))
    											{
    												grilleText[2] = "O";
    												return 2;
    											}
    											else
    												if(grilleText[3].equals("X"))
    												{
    													grilleText[0] = "O";
    													return 0;
    												}
    										}
    										else
    											if(grilleText[7].equals("X"))
    											{
    												if(grilleText[5].equals("X"))
    												{
    													grilleText[8] = "O";
    													return 8;
    												}
	    											else
    													if(grilleText[3].equals("X"))
    													{
    														grilleText[6] = "O";
    														return 6;
    													}
    											}

    					}

    		}

    	// troisième tour ou plus.
    	lesPoids(poids);
    	for(int i = 0; i<8; i++){
    		if(poids[i] == -2){      // il ne manque qu'un O pour gagner
    			int n = lIndice(i);  // indice de la case vide
    			grilleText[n] = "O"; // on y place O
    			return n;
    		}
    		if(poids[i]==2) // Il ne manque qu'un X pour gagner, avant d'y
    			ch = i;     // jouer un O, on va regarder s'il y a possibilité
    	}                   // d'une victoire immédiate de O ailleurs.

    	if(ch >=0){			// Aucune victoire immédiate, mais défaite potentielle.
    		int n = lIndice(ch);  // l'indice de la case vide à bloquer.
    		grilleText[n]="O";
    		return n;
    	}
    	ch = -1;

    	// Pas de cas de victoire immédiate ou défaite au prochain tour.
    	for(int i =0; i<8; i++){
    		if(poids[i]==-1)     // Est-ce qu'il y a plus de O que de X?
    			if(lIndice(i)>=0) // Est-ce qu'il y a une case vide (donc
    				ch=i;		  // 1 O et pas de X)?
    	}

    	if(ch>=0){   // s'il y a une ligne, colonne ou diagonale avec 1 O et
    		int n = lIndice(ch);  // pas de X, alors on place le O dans une des
    		grilleText[n]="O";    // cases vides.  Remarque, voici l'endroit où
    		return n;             // cet algo n'est pas optimal, car il ne vérifie
   		}                         // pas quelle case augmenterais la probabilité de
   		                          // victoire.

    	for(int i=0; i<8; i++)    // Aucune ligne, colonne ou diagonale où il est encore
    		if(lIndice(i)>=0){    // possible de gagner, on cherche une case vide.
    			int n = lIndice(i);
    			grilleText[n]="O";
    			return n;
    		}
    	return -1;
    }

    // retourne la première case vide rencontrée selon l'indice de la ligne, colonne ou
    // diagonale.  Les indices 0 à 2 représentent les trois lignes, 3 à 5, les trois
    // colonnes, 7 et 8, les deux diagonales.
    private int lIndice(int pos){
    	if(pos <3)
    	{
    		for(int i = 0; i<3; i++)
    			if(grilleText[pos *3 + i].equals(""))
    				return pos*3 +i;
    	}
    	else
    		if(pos < 6)
    		{	for(int i = 0; i<3; i++)
    				if(grilleText[3 * i+ pos-3].equals(""))
    					return 3*i + pos -3;
    		}
    		else
    			if(pos==6){
    				for(int i = 0; i<3; i++)
    					if(grilleText[i*4].equals(""))
    						return i *4;

    			}
    			else
    				if(pos==7){
    					for(int i = 0; i<3; i++)
    						if(grilleText[(i+1)*2].equals(""))
    							return (i+1)*2;
    				}
    	return -1;

    }

    // Est-ce que la partie est nulle?
    // Oui, si X a joué dans 5 cases et O dans 4 cases sans qu'il y ait de gagnant.
    public boolean isPartieNulle(){
    	return tour==5;
    }

    public boolean gagnant(String joueur, int[] pos ){
    //	System.out.println("joueur "+ joueur);

    		if(grilleText[0].equals(joueur) && grilleText[1].equals(joueur)&& grilleText[2].equals(joueur))
    		{
    			pos[0]=0;
    			pos[1]=1;
    			pos[2]=2;
    			return true;
    		}
    		if(grilleText[0].equals(joueur) && grilleText[4].equals(joueur)&& grilleText[8].equals(joueur))
    		{
    			pos[0]=0;
    			pos[1]=4;
    			pos[2]=8;
    			return true;
    		}
    		if(grilleText[0].equals(joueur) && grilleText[3].equals(joueur)&& grilleText[6].equals(joueur))
    		{
    			pos[0]=0;
    			pos[1]=3;
    			pos[2]=6;
    			return true;
    		}

    			if(grilleText[6].equals(joueur) && grilleText[7].equals(joueur)&& grilleText[8].equals(joueur))
    			{
    				pos[0]=6;
    				pos[1]=7;
    				pos[2]=8;
    				return true;
    			}
    			if(grilleText[6].equals(joueur) && grilleText[4].equals(joueur)&& grilleText[2].equals(joueur))
    			{
    				pos[0]=6;
    				pos[1]=4;
    				pos[2]=2;
    				return true;
    			}

       			if(grilleText[3].equals(joueur)&& grilleText[4].equals(joueur)&& grilleText[5].equals(joueur))
    			{
    				pos[0]=3;
    				pos[1]=4;
    				pos[2]=5;
    				return true;
    			}
    			if(grilleText[1].equals(joueur)&& grilleText[4].equals(joueur)&& grilleText[7].equals(joueur))
    			{
    				pos[0]=1;
    				pos[1]=4;
    				pos[2]=7;
    				return true;
    			}
    			if(grilleText[2].equals(joueur)&& grilleText[8].equals(joueur)&& grilleText[5].equals(joueur))
    			{
    				pos[0]=2;
    				pos[1]=5;
    				pos[2]=8;
    				return true;
    			}


    	return false;
    }

     public void testDebug(int[] t){
    	for(int i=0; i<t.length; i++){
    		if( i%2 == 0 && t[i] != -1) {
    			grilleText[t[i]] = "X";
    			tour++;
    		}
    		else
    			if(i%2 == 1 && t[i] != -1)
    				grilleText[t[i]] ="O";
    	}

     }
}