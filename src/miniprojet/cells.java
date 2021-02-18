package miniprojet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class cells extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int [][] Tgrille = new int[20][20] ;
	public int x = 0 ;
	public int y = 0 ;
	public int [][] Tnombre = new int [20][20] ;
	public int celluvoisins = 0 ;
	public boolean alive = false ;
	public int [] tab  = new int [2];
	public int comp = 0 ;
	
	public cells(){
		this.addMouseListener(new MouseAdapter(){
		      public void mousePressed(MouseEvent ev){
		    	  Tgrille[(int)(ev.getY()/(400/20))][(int)(ev.getX()/(400/20))] = 1 ;
		    	  repaint();
		    	  
		      }
		    });
		
	}
	
	public void start(int [][] Tgrille , Graphics cellu)
	{
	
		//état initiale de la grille 
		for(int i = 0 ; i< Tgrille.length ; i ++){
			for(int j = 0 ; j < Tgrille[0].length ; j++){
				//les cellules mortes
				if(Tgrille[i][j] == 0){
				cellu.setColor(Color.CYAN) ;
				cellu.fillRect(x,y,20,20);
				cellu.setColor(Color.white);
				cellu.fillRect(x,y,17,17);
				}
				//les cellules vivantes
				if(Tgrille[i][j] == 1){
					cellu.setColor(Color.white) ;
					cellu.fillRect(x,y,20,20);
					cellu.setColor(Color.blue);
					cellu.fillRect(x,y,17,17);	
				}
				
				//remplissage de grille
				x = x + 20;	
			}
			x = 0 ;
			y = y + 20 ;
		}
		x = y = 0 ;
	}
	
	//Evolution les cellules en fonction des régles
	public void Regle(){
		for(int i = 0 ; i < 20 ; i++){
			for(int j = 0 ; j<20 ; j++){
				alive = false ;
				//si on est dans le coint supérieur gauche
				 if(i == 0 && j == 0 && alive == false){
					tab[0] = i ;
					tab[1] = j ;
					for(int l =  0 ; l < 2 ;  l ++) {
						for(int m = 0 ; m < 2 ; m ++){
							if(Tgrille[l][m] == 1 ){
								celluvoisins ++ ;
							}
							
						}
					}
					alive = true ;
					eval() ;
				}
			     //si on est dans le coint inférieur gauche
				 if(i == 0 && j == 19 && alive == false){
					tab[0] = i ;
					tab[1] = j ;
					for(int l = 0 ; l < 1 ; l ++ ){
						for(int m = 18 ; m < 20 ; m++){
							if(Tgrille[l][m] == 1 ){
								celluvoisins ++ ;
							}
						}
					}
				    alive = true ;	
					eval() ;
				}
				//si on est dans le coint supérieur droit
				else if(i == 19 && j == 0 && alive == false){
					tab[0] = i ;
					tab[1] = j ;
					for(int l = 18 ; l < 20 ; l ++ ){
						for(int m = 0 ; m < 2 ; m++){
							if(Tgrille[l][m] == 1 ){
								celluvoisins ++ ;
							}
						}
					}
					alive = true ;
					eval() ;
				}
				//si on est dans le coint inférieur droit
				else if(i == 19 && j == 19  && alive == false ){
					tab[0] = i ;
					tab[1] = j ;
					for(int l = 18 ; l < 20 ; l ++ ){
						for(int m = 18 ; m < 20 ; m++){
							if(Tgrille[l][m] == 1 ){
								celluvoisins ++ ;
							}
						}
					}
					alive = true ;
					eval() ;
				}
				 
		
				if(i == 0 && alive ==false ){
					tab[0] = i ;
					tab[1] = j ;
					for(int l = 0 ; l < 2 ; l++){
						for(int m = j - 1 ; m < j+2 ; m++){
							if(Tgrille[l][m] == 1 ){
								celluvoisins ++ ;
							}
						}
					}
					alive = true ;
					eval() ;
				}
				// Completer 
				
				if(i == 19 && alive == false){
					tab[0] = i ;
					tab[1] = j ;
					for(int l = 18 ; l < 20 ; l++){
						for(int m =  j - 1 ; m < j+2 ; m++ ){
							if(Tgrille[l][m] == 1 ){
								celluvoisins ++ ;
							}	
						}
					}
					alive = true ;
					eval() ;
				}
				
				//
				if(j ==  0 && alive == false){
					tab[0] = i ;
					tab[1] = j ;
					for(int l = i -1 ; l < i+2 ; l++){
						for(int m =  0 ; m < 2 ; m++ ){
							if(Tgrille[l][m] == 1 ){
								celluvoisins ++ ;
							}
						}
					}
					alive = true ;
					eval() ;
				}
				
				if(j == 19 && alive == false){
					tab[0] = i ;
					tab[1] = j ;
				for(int l = i-1 ; l < i+2 ; l ++){
					for(int m = 18 ; m< 20 ; m++){
						if(Tgrille[l][m] == 1 ){
							celluvoisins ++ ;
						}
					}
				}
				alive = true ;
				eval() ;
				}
				
				if(alive == false ){
					tab[0] = i ;
					tab[1] = j ;
					for(int l = i-1 ; l < i+2 ; l++){
						for(int m = j -1 ; m < j+2 ; m ++){
							  if(Tgrille[l][m] == 1 ){
										celluvoisins  = celluvoisins + 1  ;	
						      }
						}
					}
					eval() ;
					alive = true ;
				}
			}
			
		}
	}
	// les régles sur les cellules voisines
	public void eval (){
		switch(celluvoisins){
		case 3 : Tnombre[tab[0]][tab[1]] = 1 ;
		break ;
		case 2 : Tnombre[tab[0]][tab[1]] = Tgrille[tab[0]][tab[1]] ;
		break ;
		default :Tnombre[tab[0]][tab[1]] = 0  ;
		}
		
		celluvoisins = 0 ;
	}
	//Methode d'affichage du Jeu De La Vie
	public void print(){
		for(int  i = 0 ; i < 20 ; i++){
			for(int j = 0 ; j< 20 ; j++){
				System.out.print(Tgrille[i][j]);
			}
			System.out.println();
		}
	}
	
	public void init(){
		for(int i = 0 ; i < 20 ; i ++){
			for(int j = 0 ; j < 20 ; j++){
				Tgrille[i][j] = Tnombre[i][j] ;
			}
		}
	}

	public void paintComponent(Graphics g){
		comp ++ ;
		start(Tgrille,g);
		Regle() ;
	}
}
