package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controler.gestionSetup;

public class AjoutSetupPanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 5L;
	private JButton returnButton, applyButton;
	private JFrame frame;
	private JLabel textLabel1, label, validateLabel;
	private JTextField textZone1;
	private int codeScene;

	public AjoutSetupPanel(JFrame frame, int codeScene) {

		this.frame = frame;
		this.codeScene = codeScene;

		this.setLayout(null);
		this.frame.setContentPane(this);

		label = new JLabel("Ajout d'un Setup");
		label.setLayout(null);
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setBounds(25, 0, 250, 40);
		this.add(label);

		textLabel1 = new JLabel("Paramètres du Setup", SwingConstants.CENTER);
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

		validateLabel = new JLabel();
		validateLabel.setLayout(null);
		validateLabel.setForeground(Color.green.darker());
		validateLabel.setFont(new Font("Arial", Font.BOLD, 20));
		validateLabel.setBounds(AppInterface.windowsSizeX - 650, AppInterface.windowsSizeY - 100, 400, 40);
		this.add(validateLabel);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == applyButton) {

			if (textZone1.getText().equals("")) {
				if (textZone1.getText().equals(""))
					textZone1.setBorder(new LineBorder(Color.red, 1));
				else
					textZone1.setBorder(new LineBorder(Color.GRAY, 1));

				validateLabel.setText("Certain champs ne sont pas rempli");
				validateLabel.setForeground(Color.red.darker());
			} else
				validateLabel.setText("");

			if (!(textZone1.getText().equals(""))) {
				validateLabel.setText("La scène a été ajouté");
				validateLabel.setForeground(Color.green.darker());

				boolean bool = gestionSetup.ajouterSetup(textZone1.getText(), codeScene);
				if(bool) {
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
			JPanel AffichageSetupPanel = new AffichageSetupPanel(frame, codeScene);
			frame.getIgnoreRepaint();
			frame.revalidate();
		}
	}

}
