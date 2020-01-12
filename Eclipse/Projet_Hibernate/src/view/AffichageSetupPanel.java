package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import controler.gestionSetup;
import controler.myTableSetupManagement;
import java.util.ArrayList;

public class AffichageSetupPanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 4L;
	private JButton returnButton, searchButton, addButton, suppressButton, affichageTemps, numeroBobine, ouvrir;
	private JFrame frame;
	private JTextField textZone1;
	private JComboBox<String> dropDownList;
	private JTable table;
	private myTableSetupManagement tablemodel;
	private JLabel label;
	private TableRowSorter<myTableSetupManagement> sorter;
	private int codeScene;

	public AffichageSetupPanel(JFrame frame, int codeScene) {
		this.codeScene = codeScene;
		this.frame = frame;

		this.setLayout(null);
		this.frame.setContentPane(this);

		label = new JLabel("Affichage des Setups");
		label.setLayout(null);
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setBounds(25, 0, 250, 40);
		this.add(label);

		searchButton = new JButton("Rechercher");
		searchButton.setLayout(new BorderLayout());
		searchButton.setFont(new Font("Arial", Font.BOLD, 20));
		searchButton.setBounds(50, 75, 100, 50);
		searchButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		searchButton.setBackground(Color.LIGHT_GRAY);
		searchButton.setOpaque(true);
		this.add(searchButton);
		searchButton.addActionListener(this);

		textZone1 = new JTextField();
		textZone1.setFont(new Font("Arial", Font.PLAIN, 18));
		textZone1.setLayout(null);
		textZone1.setBounds(150, 75, 300, 50);
		this.add(textZone1);
		textZone1.setHorizontalAlignment(JTextField.CENTER);

		dropDownList = new JComboBox<String>();
		dropDownList.setBounds(450, 75, 100, 50);
		dropDownList.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(dropDownList);
		dropDownList.addItem("codeSetup");

		addButton = new JButton("Ajouter");
		addButton.setBounds(AppInterface.windowsSizeX - 225, 75, 175, 50);
		addButton.setFont(new Font("Arial", Font.BOLD, 20));
		addButton.setForeground(Color.BLACK);
		addButton.setBackground(Color.LIGHT_GRAY);
		this.add(addButton);
		addButton.addActionListener(this);

		String title[] = { "codeSetup", "paramètre", "codeScene"};

		tablemodel = new myTableSetupManagement(title, codeScene);
		table = new JTable(tablemodel);
		tablemodel.setTable(table);
		JScrollPane tableContainer = new JScrollPane(table);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setBackground(new Color(51, 153, 255));
		table.setFont(new Font("Arial", Font.BOLD, 13));
		table.setAutoCreateRowSorter(true);
		table.getTableHeader().setReorderingAllowed(false);
		tableContainer.setBounds(50, 200, 700, 400);
		this.add(tableContainer, BorderLayout.CENTER);

		sorter = new TableRowSorter<myTableSetupManagement>(tablemodel);

		suppressButton = new JButton("Supprimer");
		suppressButton.setBounds(AppInterface.windowsSizeX - 225, 225, 175, 50);
		suppressButton.setFont(new Font("Arial", Font.BOLD, 20));
		suppressButton.setForeground(Color.BLACK);
		suppressButton.setBackground(Color.LIGHT_GRAY);
		this.add(suppressButton);
		suppressButton.addActionListener(this);

		returnButton = new JButton("RETOUR");
		returnButton.setBounds(AppInterface.windowsSizeX - 225, AppInterface.windowsSizeY - 125, 175, 50);
		returnButton.setFont(new Font("Arial", Font.BOLD, 20));
		returnButton.setForeground(Color.BLACK);
		returnButton.setBackground(Color.LIGHT_GRAY);
		this.add(returnButton);
		returnButton.addActionListener(this);
		
		affichageTemps = new JButton("Affichage du temps d'enregistrement");
		affichageTemps.setBounds(AppInterface.windowsSizeX - 225, AppInterface.windowsSizeY - 100, 175, 50);
		affichageTemps.setFont(new Font("Arial", Font.BOLD, 20));
		affichageTemps.setForeground(Color.BLACK);
		affichageTemps.setBackground(Color.LIGHT_GRAY);
		this.add(affichageTemps);
		affichageTemps.addActionListener(this);
		
		numeroBobine = new JButton("Affichage du temps d'enregistrement");
		numeroBobine.setBounds(AppInterface.windowsSizeX - 225, AppInterface.windowsSizeY - 100, 175, 50);
		numeroBobine.setFont(new Font("Arial", Font.BOLD, 20));
		numeroBobine.setForeground(Color.BLACK);
		numeroBobine.setBackground(Color.LIGHT_GRAY);
		this.add(numeroBobine);
		numeroBobine.addActionListener(this);
		
		ouvrir = new JButton("Accéder à la scène");
		ouvrir.setBounds(AppInterface.windowsSizeX - 275, 75, 225, 50);
		ouvrir.setFont(new Font("Arial", Font.BOLD, 20));
		ouvrir.setForeground(Color.BLACK);
		ouvrir.setBackground(Color.LIGHT_GRAY);
		this.add(ouvrir);
		ouvrir.addActionListener(this);
	}

	/**
	 * This method gathers the different action of the panel
	 * 
	 * @param e The action that is performed
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == returnButton) {
			JPanel AffichageScenePanel = new AffichageScenePanel(frame);
			frame.repaint();
			frame.revalidate();
		}

		if (e.getSource() == searchButton) {

			if (dropDownList.getSelectedItem().equals("codeSetup")) {
				if (textZone1.getText() != null) {
					sorter.setRowFilter(RowFilter.regexFilter(textZone1.getText(), 0));
					table.setRowSorter(sorter);
				} else
					table.setAutoCreateRowSorter(true);
			}
		}

		if (e.getSource() == addButton) {

			JPanel AjoutSetupPanel = new AjoutSetupPanel(frame, codeScene);
			frame.repaint();
			frame.revalidate();
		}

		if (e.getSource() == suppressButton) {
			if (table.getSelectedRow() != -1) {

				int retour = JOptionPane.showConfirmDialog(this,
						"Êtes-vous sûr de vouloir supprimer ce setups",
						"TITRE", JOptionPane.YES_NO_OPTION);

				if (retour == JOptionPane.OK_OPTION)
					tablemodel.removeRow(table.getSelectedRow());
			}
		}
		
		if (e.getSource() == affichageTemps) {
			int retour = JOptionPane.showConfirmDialog(this,
					"Le temps total enregistré pour cette scène est : " + (gestionSetup.getTimeScene(codeScene)%60) + "Minute" + (gestionSetup.getTimeScene(codeScene) - ((gestionSetup.getTimeScene(codeScene)%60)*60)) +"Seconds",
					"CONFIRM", JOptionPane.YES_OPTION);
		}
		
		if (e.getSource() == numeroBobine) {
			ArrayList<Integer> listeBobine = gestionSetup.getBobine(codeScene);
		}
		
		if (e.getSource() == ouvrir) {
			JPanel AffichageClapPanel = new AffichageClapPanel(frame, codeScene, (int)table.getValueAt(table.getSelectedRow(), 0));
			frame.repaint();
			frame.revalidate();
		}
	}

}
