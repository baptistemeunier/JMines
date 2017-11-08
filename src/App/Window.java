package App;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import App.GameManager;
 
public class Window extends JFrame {

	private GameManager gameManager;

	private static Window instance = null;

	public static Window getInstance() {
		if(instance == null) {
			instance = new Window();
		}
		return instance;
	}
	
	private Window(){
	    this.setTitle("Ma première fenêtre Java");
	    this.setSize(500, 500);
	    this.setLocationRelativeTo(null);               
	    this.setResizable(false);
	    this.setVisible(true);
	    this.menu();
	}

	private void menu(){
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Fichier");
		JMenuItem calculer = new JMenuItem("Voir les scores");
		menu1.add(calculer);
		JMenuItem options = new JMenuItem("Options");
		menu1.add(options);
		JMenuItem quitter = new JMenuItem("Quitter");
		menu1.add(quitter);
		menuBar.add(menu1);
 
		JMenu menu2 = new JMenu("?");
		JMenuItem aPropos = new JMenuItem("A propos");
		menu2.add(aPropos);
		menuBar.add(menu2);
		this.setJMenuBar(menuBar);
	}

}