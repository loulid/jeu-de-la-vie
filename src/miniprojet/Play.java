package miniprojet;

public class Play implements Runnable {
	private JeuDeVie jeu; 
	
	public Play(JeuDeVie jeu){
	
		this.jeu=jeu;  
	}
	public void run(){
		jeu.game() ;
	}
	

}
; 