package miniprojet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.*;
import javax.swing.*;


public class JeuDeVie extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  cells Play  = new cells() ;
	private JPanel asidePanel = new JPanel();
	private JButton btnStart;
	private JButton bntStop;
	private JButton btnReset;
	private JButton btnCharger;
	public Thread Tgrille ;
	private Color buttonsColor;
	private Color buttonsFrontColor;
	private Color backgroundColor;
	private Color backgroundFrontColor;
	
	
	public JeuDeVie(){
		this.setTitle("Jeu de La Vie");
	    this.setSize(400, 480);
	    asidePanel.setLayout(new BorderLayout());
	    asidePanel.add(Play, BorderLayout.CENTER);
		asidePanel.setBackground(new Color(183,244,251));
	    btnStart = new JButton("Démarrer");
        bntStop = new JButton("Arrêter");
        btnReset = new JButton("RAZ");
        btnCharger=new JButton("charger");
        this.buttonsColor = new Color(150,90,0);
		this.buttonsFrontColor = Color.white;
		this.backgroundColor = new Color(183,244,251);
		this.backgroundFrontColor = Color.black;

	    
	    JPanel menuPanel = new JPanel();
	    menuPanel.add(btnStart);
	    menuPanel.add(bntStop);
	    menuPanel.add(btnReset);
	    menuPanel.add(btnCharger);
	   
	    btnStart.addActionListener(new StartListener(this));
        bntStop.addActionListener (new StopListener());
        btnReset.addActionListener(new ClearListener());
        btnCharger.addActionListener(new chargerListener());
	    asidePanel.add(menuPanel , BorderLayout.SOUTH);
	    this.setContentPane(asidePanel);
	}
	


	
	public void game (){
		while(true){
			Play.repaint() ;
			sleep() ;
			Play.init();
			
		}
	}
	public void sleep(){
		 try {
	            Thread.sleep(100);
	          } catch (InterruptedException e) {
	            e.printStackTrace();
	          }	
	}
	
	private void setButtonsColor(Color c) {
		this.buttonsColor = c;
		JButton[] tab = { btnStart,bntStop, btnReset, btnCharger};
		
		for (int i=0; i<tab.length; i++) {
			tab[i].setBackground(this.buttonsColor);
		}
	}
	
	private void setButtonsFrontColor(Color c) {
		this.buttonsFrontColor = c;
		JButton[] tab = {btnStart, bntStop,btnReset,btnCharger};
		
		for (int i=0; i<tab.length; i++) {
			tab[i].setForeground(this.buttonsFrontColor);
		}
	}
	
	private void setAsidePanelColor(Color c) {
		this.backgroundColor = c;
		this.asidePanel.setBackground(c);
	}
	
	private void setAsidePanelFrontColor(Color c) {
		this.backgroundFrontColor = c;
		this.asidePanel.setBackground(c);
	}
	
	private void setFrameTitle(String title){
		this.setTitle(title);
	}
	
	public void createInitFile(String file){
		try{
			FileWriter fichier = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fichier);
			PrintWriter pw = new PrintWriter(bw);
			pw.println("200-200-200");
			pw.println("100-100-100");
			pw.println("255-255-255");
			pw.println("223-109-20");
			pw.println("Game");
			pw.close();
			System.out.println("fichier écrit");
		}
		catch (IOException e){
		    e.printStackTrace();
		} 
	}
	
	public Color lineToColor(String line) throws NumberFormatException { 
		Color c = Color.BLACK;
		String[] rgb = line.split("-");
		if (rgb.length == 3) {
			int red = Integer.parseInt(rgb[0]);
			int green = Integer.parseInt(rgb[1]);
			int blue = Integer.parseInt(rgb[2]);
			if (red>=0 && red<256 && green>=0 && green<256 && blue>=0 && blue<256){
				c = new Color(red, green, blue);
			}
		}
		return c;
	}
	
	public void loadInitFile(String file){

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String ligne = "";
			int i = 1;
			while (ligne != null) { 
				ligne = br.readLine();
				if (i<5) {
					Color couleur = lineToColor(ligne);
					if (i == 1) {
						setButtonsColor(couleur);
					}
					else if (i == 2) {
						setButtonsFrontColor(couleur);
					}
					else if (i == 3) {
						setAsidePanelColor(couleur);
					}
					else if (i == 4) {
						setAsidePanelFrontColor(couleur);
					}
				}
					else if (i == 5) {
						setFrameTitle(ligne);
					}
					i = i+1;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 class ClearListener implements ActionListener {
	       public void actionPerformed(ActionEvent ev) {
	        if(ev.getSource()==btnReset) {
	            for (int i = 0; i < 20; i++) {
	            	 for(int j = 0 ; j < 20 ; j++){
	 	    			Play.Tgrille[i][j]  = 0 ;
	 	    			setButtonsColor(new Color(150,90,0));
	 	   			    setButtonsFrontColor(Color.black);
	 	   			    setAsidePanelColor(new Color(183,244,251));
		 	   			setAsidePanelFrontColor(Color.white);
		 	   			setFrameTitle("Jeu de La vie");
	 	    		 } 
	            }
	            
	        }
	       }
	}
	   
	public class StartListener implements ActionListener {
		private JeuDeVie j; 
		public StartListener(JeuDeVie j) {
			this.j=j; 
		}
		
	public void actionPerformed(ActionEvent ev) {
			if(ev.getSource()==btnStart) { 
			Runnable runnable = new Play (j); 
			 Tgrille = new Thread (runnable);
	    	 Tgrille.start();
			} 
    }
   }
 
    class StopListener implements ActionListener {
	  public void actionPerformed(ActionEvent ev) {
	    	if(ev.getSource()==bntStop) {
	    		Tgrille.stop();
		    
				}
	    }
     }
    class chargerListener implements ActionListener {
	    public void actionPerformed(ActionEvent ev) { 
			if (ev.getSource() == btnCharger){
				//createInitFile("init");
				loadInitFile("init.txt");
			}
	    }
    }
}
