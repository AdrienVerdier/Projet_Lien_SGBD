package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controler.gestionScene;

public class AjoutSceneExterieurPanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 4L;
	private JButton returnButton, applyButton, nouveauLieu;
	private JFrame frame;
	private JLabel textLabel1, textLabel2, textLabel3, textLabel4, label, validateLabel;
	private JTextField textZone1, textZone2, textZone3;
	private JComboBox<String> dropDownList;
	private JCheckBox isNocturne;
	private boolean bool = false;

	public AjoutSceneExterieurPanel(JFrame frame) {

		this.frame = frame;

		this.setLayout(null);
		this.frame.setContentPane(this);

		label = new JLabel("Ajout d'une scène iextèrieur");
		label.setLayout(null);
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setBounds(25, 0, 250, 40);
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
		
		isNocturne = new JCheckBox("Scène nocturne");
		isNocturne.setBounds(AppInterface.windowsSizeX - 500, AppInterface.windowsSizeY - 800, 300, 50);
		isNocturne.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(isNocturne);

		textLabel2 = new JLabel("Lieu :", SwingConstants.CENTER);
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
		dropDownList = gestionScene.RemplirListeLieu(dropDownList);

		textLabel3 = new JLabel("adresse", SwingConstants.CENTER);
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
		
		textLabel4 = new JLabel("description", SwingConstants.CENTER);
		textLabel4.setLayout(null);
		textLabel4.setFont(new Font("Arial", Font.BOLD, 20));
		textLabel4.setBounds(AppInterface.windowsSizeX - 400, AppInterface.windowsSizeY - 550, 300, 50);
		textLabel4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		textLabel4.setBackground(Color.LIGHT_GRAY);
		textLabel4.setOpaque(true);
		this.add(textLabel4);
		textLabel4.setVisible(bool);

		textZone3 = new JTextField();
		textZone3.setFont(new Font("Arial", Font.BOLD, 20));
		textZone3.setLayout(null);
		textZone3.setBounds(AppInterface.windowsSizeX - 300, AppInterface.windowsSizeY - 550, 300, 50);
		textZone3.setHorizontalAlignment(JTextField.CENTER);
		this.add(textZone3);
		textZone3.setVisible(bool);

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
		
		nouveauLieu = new JButton("Nouveau Lieu");
		nouveauLieu.setBounds(AppInterface.windowsSizeX - 225, AppInterface.windowsSizeY - 300, 175, 50);
		nouveauLieu.setFont(new Font("Arial", Font.BOLD, 20));
		nouveauLieu.setForeground(Color.BLACK);
		nouveauLieu.setBackground(Color.LIGHT_GRAY);
		this.add(nouveauLieu);
		nouveauLieu.addActionListener(this);

		validateLabel = new JLabel();
		validateLabel.setLayout(null);
		validateLabel.setForeground(Color.green.darker());
		validateLabel.setFont(new Font("Arial", Font.BOLD, 20));
		validateLabel.setBounds(AppInterface.windowsSizeX - 650, AppInterface.windowsSizeY - 100, 400, 40);
		this.add(validateLabel);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == applyButton) {

			if (textZone1.getText().equals("") || ((textZone2.getText().equals("") && bool == true) || (textZone3.getText().equals("") && bool == true)) || (dropDownList.getSelectedItem().equals("") && bool == false)) {
				if (textZone1.getText().equals(""))
					textZone1.setBorder(new LineBorder(Color.red, 1));
				else
					textZone1.setBorder(new LineBorder(Color.GRAY, 1));

				if (textZone2.getText().equals("") && bool == true)
					textZone2.setBorder(new LineBorder(Color.red, 1));
				else
					textZone2.setBorder(new LineBorder(Color.GRAY, 1));
				
				if (textZone3.getText().equals("") && bool == true)
					textZone3.setBorder(new LineBorder(Color.red, 1));
				else
					textZone3.setBorder(new LineBorder(Color.GRAY, 1));

				validateLabel.setText("Certain champs ne sont pas rempli");
				validateLabel.setForeground(Color.red.darker());
			} else
				validateLabel.setText("");

			if (!(textZone1.getText().equals("")) && (!(textZone2.getText().equals("")) && bool == true) && (!(textZone3.getText().equals("")) && bool == true)) {
				validateLabel.setText("La scène a été ajouté");
				validateLabel.setForeground(Color.green.darker());

				//le bool va nous servir a savoir si c'est un nouvau lieu ou un ancien
				boolean bool2 = gestionScene.ajouterSceneExterieur(textZone1.getText(), isNocturne.isSelected(), textZone2.getText(), textZone3.getText(), bool);
				if(bool2) {
					validateLabel.setText("La scène a été ajouté");
					validateLabel.setForeground(Color.green.darker());
				}
				else {
					validateLabel.setText("Problème lors de l'ajout de la scène");
					validateLabel.setForeground(Color.red.darker());
				}
			}
			
			if (!(textZone1.getText().equals("")) && (!(dropDownList.getSelectedItem().equals("")) && bool == false)) {
				validateLabel.setText("La scène a été ajouté");
				validateLabel.setForeground(Color.green.darker());

				//le bool va nous servir a savoir si c'est un nouvau lieu ou un ancien
				boolean bool2 = gestionScene.ajouterSceneExterieur(textZone1.getText(), isNocturne.isSelected(), (String)dropDownList.getSelectedItem(), "OUIIIIIIIIIII", bool);
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
		
		if (e.getSource() == nouveauLieu) {
			bool = !bool;
			
			textLabel3.setVisible(bool);
			textZone2.setVisible(bool);
			textLabel4.setVisible(bool);
			textZone3.setVisible(bool);
			dropDownList.setVisible(!bool);
			
		}
	}

}
