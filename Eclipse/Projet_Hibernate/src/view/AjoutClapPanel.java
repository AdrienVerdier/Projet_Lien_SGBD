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

import controler.gestionClap;

public class AjoutClapPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 7L;
	private JButton returnButton, applyButton;
	private JFrame frame;
	private JLabel textLabel1, textLabel3, label, validateLabel;
	private JTextField textZone1;
	private JComboBox<Integer> dropDownList1, dropDownList2;
	private int codeSetup;
	private int codeScene;

	/**
	 * This method creates the panel
	 * 
	 * @param frame the frame of the interface
	 * @param data  the object which represent all the data that are stored in the
	 *              main application
	 */
	public AjoutClapPanel(JFrame frame, int codeScene, int codeSetup) {
		this.codeScene = codeScene;
		this.codeSetup = codeSetup;
		this.frame = frame;

		this.setLayout(null);
		this.frame.setContentPane(this);

		label = new JLabel("Ajout d'un clap");
		label.setLayout(null);
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setBounds(25, 0, 250, 40);
		this.add(label);

		textLabel1 = new JLabel("Code de la Bobine : ", SwingConstants.CENTER);
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

		textLabel3 = new JLabel("Duree :", SwingConstants.CENTER);
		textLabel3.setLayout(null);
		textLabel3.setFont(new Font("Arial", Font.BOLD, 20));
		textLabel3.setBounds(AppInterface.windowsSizeX - 800, AppInterface.windowsSizeY - 550, 300, 50);
		textLabel3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		textLabel3.setBackground(Color.LIGHT_GRAY);
		textLabel3.setOpaque(true);
		this.add(textLabel3);

		Integer[] listMinute = new Integer[60];

		for (int i = 0; i < 60; i++) {
			listMinute[i] = i;
		}

		dropDownList1 = new JComboBox<Integer>(listMinute);
		dropDownList1.setFont(new Font("Arial", Font.BOLD, 20));
		dropDownList1.setBounds(AppInterface.windowsSizeX - 500, AppInterface.windowsSizeY - 550, 150, 50);
		this.add(dropDownList1);

		Integer[] listSeconds = new Integer[60];

		for (int i = 0; i < 60; i++) {
			listSeconds[i] = i;
		}

		dropDownList2 = new JComboBox<Integer>(listMinute);
		dropDownList2.setFont(new Font("Arial", Font.BOLD, 20));
		dropDownList2.setBounds(AppInterface.windowsSizeX - 350, AppInterface.windowsSizeY - 550, 150, 50);
		this.add(dropDownList2);

		applyButton = new JButton("APPLY");
		applyButton.setBounds(AppInterface.windowsSizeX - 600, AppInterface.windowsSizeY - 150, 175, 50);
		applyButton.setFont(new Font("Arial", Font.BOLD, 20));
		applyButton.setForeground(Color.BLACK);
		applyButton.setBackground(Color.LIGHT_GRAY);
		this.add(applyButton);
		applyButton.addActionListener(this);

		returnButton = new JButton("RETURN");
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

	/**
	 * This method gathers the different action of the panel
	 * 
	 * @param e The action that is performed
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == applyButton) {

			if (textZone1.getText().equals("")) {
				if (textZone1.getText().equals(""))
					textZone1.setBorder(new LineBorder(Color.red, 1));
				else
					textZone1.setBorder(new LineBorder(Color.GRAY, 1));

				validateLabel.setText("Some fields are empty");
				validateLabel.setForeground(Color.red.darker());
			} else
				validateLabel.setText("");

			if (!(textZone1.getText().equals(""))) {
				validateLabel.setText("Le clap a été ajouté");
				validateLabel.setForeground(Color.green.darker());
				
				gestionClap.ajouterClap((int) dropDownList1.getSelectedItem(), (int) dropDownList2.getSelectedItem(), textZone1.getText(), codeSetup);
			}

		}

		if (e.getSource() == returnButton) {
			JPanel AffichageClapPanel = new AffichageClapPanel(frame, codeScene, codeSetup);
			frame.repaint();
			frame.revalidate();
		}
	}
	
}
