package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controler.gestionScene;

public class AjoutSceneInterieurPanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 3L;
	private JButton returnButton, applyButton, nouveauTheatre;
	private JFrame frame;
	private JLabel textLabel1, textLabel2, textLabel3, label, validateLabel;
	private JTextField textZone1, textZone2;
	private JComboBox<String> dropDownList;
	private boolean bool = false;

	public AjoutSceneInterieurPanel(JFrame frame) {

		this.frame = frame;

		this.setLayout(null);
		this.frame.setContentPane(this);

		label = new JLabel("Ajout d'une scène intérieur");
		label.setLayout(null);
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setBounds(25, 0, 350, 40);
		this.add(label);

		textLabel1 = new JLabel("Description de la scène", SwingConstants.CENTER);
		textLabel1.setLayout(null);
		textLabel1.setFont(new Font("Arial", Font.BOLD, 20));
		textLabel1.setBounds(AppInterface.windowsSizeX - 800, AppInterface.windowsSizeY - 750, 300, 50);
		textLabel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		textLabel1.setBackground(Color.LIGHT_GRAY);
		textLabel1.setOpaque(true);
		this.add(textLabel1);

		textZone1 = new JTextField();
		textZone1.setFont(new Font("Arial", Font.BOLD, 20));
		textZone1.setLayout(null);
		textZone1.setBounds(AppInterface.windowsSizeX - 500, AppInterface.windowsSizeY - 750, 300, 50);
		this.add(textZone1);
		textZone1.setHorizontalAlignment(JTextField.CENTER);

		textLabel2 = new JLabel("Théatre :", SwingConstants.CENTER);
		textLabel2.setLayout(null);
		textLabel2.setFont(new Font("Arial", Font.BOLD, 20));
		textLabel2.setBounds(AppInterface.windowsSizeX - 800, AppInterface.windowsSizeY - 650, 300, 50);
		textLabel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		textLabel2.setBackground(Color.LIGHT_GRAY);
		textLabel2.setOpaque(true);
		this.add(textLabel2);
		
		dropDownList = new JComboBox<String>();
		dropDownList.setBounds(AppInterface.windowsSizeX - 500, AppInterface.windowsSizeY - 650, 300, 50);
		dropDownList.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(dropDownList);
		dropDownList = gestionScene.RemplirListeTheatre(dropDownList);

		textLabel3 = new JLabel("description", SwingConstants.CENTER);
		textLabel3.setLayout(null);
		textLabel3.setFont(new Font("Arial", Font.BOLD, 20));
		textLabel3.setBounds(AppInterface.windowsSizeX - 800, AppInterface.windowsSizeY - 550, 300, 50);
		textLabel3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		textLabel3.setBackground(Color.LIGHT_GRAY);
		textLabel3.setOpaque(true);
		this.add(textLabel3);
		textLabel3.setVisible(bool);

		textZone2 = new JTextField();
		textZone2.setFont(new Font("Arial", Font.BOLD, 20));
		textZone2.setLayout(null);
		textZone2.setBounds(AppInterface.windowsSizeX - 500, AppInterface.windowsSizeY - 550, 300, 50);
		textZone2.setHorizontalAlignment(JTextField.CENTER);
		this.add(textZone2);
		textZone2.setVisible(bool);

		applyButton = new JButton("APPLIQUER");
		applyButton.setBounds(AppInterface.windowsSizeX - 600, AppInterface.windowsSizeY - 150, 175, 50);
		applyButton.setFont(new Font("Arial", Font.BOLD, 20));
		applyButton.setForeground(Color.BLACK);
		applyButton.setBackground(Color.LIGHT_GRAY);
		this.add(applyButton);
		applyButton.addActionListener(this);

		returnButton = new JButton("RETOUR");
		returnButton.setBounds(AppInterface.windowsSizeX - 225, AppInterface.windowsSizeY - 125, 175, 50);
		returnButton.setFont(new Font("Arial", Font.BOLD, 20));
		returnButton.setForeground(Color.BLACK);
		returnButton.setBackground(Color.LIGHT_GRAY);
		this.add(returnButton);
		returnButton.addActionListener(this);
		
		nouveauTheatre = new JButton("Nouveau Théatre");
		nouveauTheatre.setBounds(AppInterface.windowsSizeX - 275, AppInterface.windowsSizeY - 300, 225, 50);
		nouveauTheatre.setFont(new Font("Arial", Font.BOLD, 20));
		nouveauTheatre.setForeground(Color.BLACK);
		nouveauTheatre.setBackground(Color.LIGHT_GRAY);
		this.add(nouveauTheatre);
		nouveauTheatre.addActionListener(this);

		validateLabel = new JLabel();
		validateLabel.setLayout(null);
		validateLabel.setForeground(Color.green.darker());
		validateLabel.setFont(new Font("Arial", Font.BOLD, 20));
		validateLabel.setBounds(AppInterface.windowsSizeX - 650, AppInterface.windowsSizeY - 100, 400, 40);
		this.add(validateLabel);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == applyButton) {

			if (textZone1.getText().equals("") || (textZone2.getText().equals("") && bool == true) || (dropDownList.getSelectedItem() == null && bool == false)) {
				if (textZone1.getText().equals(""))
					textZone1.setBorder(new LineBorder(Color.red, 1));
				else
					textZone1.setBorder(new LineBorder(Color.GRAY, 1));

				if (textZone2.getText().equals("") && bool == true)
					textZone2.setBorder(new LineBorder(Color.red, 1));
				else
					textZone2.setBorder(new LineBorder(Color.GRAY, 1));

				validateLabel.setText("Certain champs ne sont pas rempli");
				validateLabel.setForeground(Color.red.darker());
			} else
				validateLabel.setText("");

			if (!(textZone1.getText().equals("")) && (!(textZone2.getText().equals("")) && bool == true)) {
				validateLabel.setText("La scène a été ajouté");
				validateLabel.setForeground(Color.green.darker());

				//le bool va nous servir a savoir si c'est un nouvaux théatre ou un ancien
				boolean bool2 = gestionScene.ajouterSceneInterieur(textZone1.getText(), textZone2.getText(), bool);
				if(bool2) {
					validateLabel.setText("La scène a été ajouté");
					validateLabel.setForeground(Color.green.darker());
				}
				else {
					validateLabel.setText("Problème lors de l'ajout de la scène");
					validateLabel.setForeground(Color.red.darker());
				}
			}
			
			if (!(textZone1.getText().equals("")) && (!(dropDownList.getSelectedItem() == null) && bool == false)) {
				validateLabel.setText("La scène a été ajouté");
				validateLabel.setForeground(Color.green.darker());

				//le bool va nous servir a savoir si c'est un nouvaux théatre ou un ancien
				boolean bool2 = gestionScene.ajouterSceneInterieur(textZone1.getText(), (String)dropDownList.getSelectedItem(), bool);
				if(bool2) {
					validateLabel.setText("La scène a été ajouté");
					validateLabel.setForeground(Color.green.darker());
				}
				else {
					validateLabel.setText("Problème lors de l'ajout de la scène");
					validateLabel.setForeground(Color.red.darker());
				}
			}

		}

		if (e.getSource() == returnButton) {
			JPanel AffichageScenePanel = new AffichageScenePanel(frame);
			frame.getIgnoreRepaint();
			frame.revalidate();
		}
		
		if (e.getSource() == nouveauTheatre) {
			bool = !bool;
			
			textLabel3.setVisible(bool);
			textZone2.setVisible(bool);
			dropDownList.setVisible(!bool);
			
		}
	}

}
