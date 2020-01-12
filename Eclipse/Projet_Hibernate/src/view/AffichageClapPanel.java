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
import java.util.ArrayList;
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

import controler.gestionClap;
import controler.myTableClapManagement;

public class AffichageClapPanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 6L;
	private JButton returnButton, searchButton, addButton, suppressButton;
	private JFrame frame;
	private JTextField textZone1;
	private JComboBox<String> dropDownList;
	private JTable table;
	private myTableClapManagement tablemodel;
	private JLabel label;
	private TableRowSorter<myTableClapManagement> sorter;
	private int codeSetup;
	private int codeScene;

	public AffichageClapPanel(JFrame frame, int codeScene, int codeSetup) {
		this.codeSetup = codeSetup;
		this.frame = frame;
		this.codeScene = codeScene;

		this.setLayout(null);
		this.frame.setContentPane(this);

		label = new JLabel("Affichage des Claps");
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
		dropDownList.addItem("codeClap");
		dropDownList.addItem("codeBobine");

		addButton = new JButton("Ajouter");
		addButton.setBounds(AppInterface.windowsSizeX - 225, 75, 175, 50);
		addButton.setFont(new Font("Arial", Font.BOLD, 20));
		addButton.setForeground(Color.BLACK);
		addButton.setBackground(Color.LIGHT_GRAY);
		this.add(addButton);
		addButton.addActionListener(this);

		String title[] = { "codeClap", "duree", "codeBobine", "codeSetup"};

		tablemodel = new myTableClapManagement(title, codeSetup);
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

		sorter = new TableRowSorter<myTableClapManagement>(tablemodel);

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
		
	}

	/**
	 * This method gathers the different action of the panel
	 * 
	 * @param e The action that is performed
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == returnButton) {
			JPanel AffichageSetupPanel = new AffichageSetupPanel(frame, codeScene);
			frame.repaint();
			frame.revalidate();
		}

		if (e.getSource() == searchButton) {

			if (dropDownList.getSelectedItem().equals("codeClap")) {
				if (textZone1.getText() != null) {
					sorter.setRowFilter(RowFilter.regexFilter(textZone1.getText(), 0));
					table.setRowSorter(sorter);
				} else
					table.setAutoCreateRowSorter(true);
			}
			
			if (dropDownList.getSelectedItem().equals("codeBobine")) {
				if (textZone1.getText() != null) {
					sorter.setRowFilter(RowFilter.regexFilter(textZone1.getText(), 2));
					table.setRowSorter(sorter);
				} else
					table.setAutoCreateRowSorter(true);
			}
		}

		if (e.getSource() == addButton) {

			JPanel AjoutClapPanel = new AjoutClapPanel(frame, codeScene, codeSetup);
			frame.repaint();
			frame.revalidate();
		}

		if (e.getSource() == suppressButton) {
			if (table.getSelectedRow() != -1) {

				int retour = JOptionPane.showConfirmDialog(this,
						"Êtes-vous sûr de vouloir supprimer ce clap",
						"TITRE", JOptionPane.YES_NO_OPTION);

				if (retour == JOptionPane.OK_OPTION)
					tablemodel.removeRow(table.getSelectedRow());
			}
		}
	}

}
