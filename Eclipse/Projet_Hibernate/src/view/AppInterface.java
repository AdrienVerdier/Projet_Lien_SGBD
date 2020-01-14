package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import controler.Connexion;

public class AppInterface extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public static int windowsSizeX = 1000;
	public static int windowsSizeY = 800;

	public AppInterface() {
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

		this.setTitle("Application de gestion de scène");
		this.setResizable(false);
		this.setSize(windowsSizeX, windowsSizeY);
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				Connexion.fermerconnexion();
				System.exit(0);
			}
		});

		JPanel AffichageScenePanel = new AffichageScenePanel(this);
		
		this.setVisible(true);
	}

}
