package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import controler.gestionScene;
import controler.myTableSceneManagement;

public class AffichageScenePanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 2L;
	private JButton searchButton, addButtonI, addButtonE, suppressButton, affichageTemps, ouvrir;
	private JFrame frame;
	private JTextField textZone1;
	private JComboBox<String> dropDownList;
	private JTable table;
	private JLabel label;
	private myTableSceneManagement tablemodel;
	private TableRowSorter<myTableSceneManagement> sorter;

	public AffichageScenePanel(JFrame frame) {
		this.frame = frame;

		this.setLayout(null);
		this.frame.setContentPane(this);

		label = new JLabel("Affichage des scènes");
		label.setLayout(null);
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setBounds(25, 0, 250, 40);
		this.add(label);

		addButtonI = new JButton("Ajouter Scène Intérieur");
		addButtonI.setBounds(AppInterface.windowsSizeX - 225, 75, 175, 50);
		addButtonI.setFont(new Font("Arial", Font.BOLD, 20));
		addButtonI.setForeground(Color.BLACK);
		addButtonI.setBackground(Color.LIGHT_GRAY);
		addButtonI.setOpaque(true);
		this.add(addButtonI);
		addButtonI.addActionListener(this);
		
		addButtonE = new JButton("Ajouter Scène Extérieur");
		addButtonE.setLayout(new BorderLayout());
		addButtonE.setBounds(AppInterface.windowsSizeX - 225, 350, 175, 50);
		addButtonE.setFont(new Font("Arial", Font.BOLD, 20));
		addButtonE.setForeground(Color.BLACK);
		addButtonE.setBackground(Color.LIGHT_GRAY);
		addButtonE.setOpaque(true);
		this.add(addButtonE);
		addButtonE.addActionListener(this);

		suppressButton = new JButton("Supprimer");
		suppressButton.setLayout(new BorderLayout());
		suppressButton.setBounds(AppInterface.windowsSizeX - 225, 225, 175, 50);
		suppressButton.setFont(new Font("Arial", Font.BOLD, 20));
		suppressButton.setForeground(Color.BLACK);
		suppressButton.setBackground(Color.LIGHT_GRAY);
		suppressButton.setOpaque(true);
		this.add(suppressButton);
		suppressButton.addActionListener(this);
		
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
		textZone1.setLayout(new BorderLayout());
		textZone1.setFont(new Font("Arial", Font.PLAIN, 18));
		textZone1.setLayout(null);
		textZone1.setBounds(150, 75, 300, 50);
		textZone1.setOpaque(true);
		this.add(textZone1);
		textZone1.setHorizontalAlignment(JTextField.CENTER);

		dropDownList = new JComboBox<String>();
		dropDownList.setLayout(new BorderLayout());
		dropDownList.setBounds(450, 75, 185, 50);
		dropDownList.setFont(new Font("Arial", Font.BOLD, 20));
		dropDownList.setOpaque(true);
		this.add(dropDownList);
		dropDownList.addItem("codeScene");
		dropDownList.addItem("codeTheatre");
		dropDownList.addItem("codeLieu");
		dropDownList.addItem("Adresse");
		
		affichageTemps = new JButton("Affichage du temps total d'enregistrement");
		affichageTemps.setLayout(new BorderLayout());
		affichageTemps.setBounds(AppInterface.windowsSizeX - 225, AppInterface.windowsSizeY - 125, 175, 50);
		affichageTemps.setFont(new Font("Arial", Font.BOLD, 20));
		affichageTemps.setForeground(Color.BLACK);
		affichageTemps.setBackground(Color.LIGHT_GRAY);
		affichageTemps.setOpaque(true);
		this.add(affichageTemps);
		affichageTemps.addActionListener(this);
		
		ouvrir = new JButton("Accéder à la scène");
		ouvrir.setLayout(new BorderLayout());
		ouvrir.setBounds(AppInterface.windowsSizeX - 275, 75, 225, 50);
		ouvrir.setFont(new Font("Arial", Font.BOLD, 20));
		ouvrir.setForeground(Color.BLACK);
		ouvrir.setBackground(Color.LIGHT_GRAY);
		ouvrir.setOpaque(true);
		this.add(ouvrir);
		ouvrir.addActionListener(this);

		String title[] = { "CodeScene", "description", "codeTheatre", "codeLieu", "nocturne?", "adresse", "description"};

		tablemodel = new myTableSceneManagement(title);
		table = new JTable(tablemodel);
		tablemodel.setTable(table);
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			private static final long serialVersionUID = 10L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				c.setForeground(Color.BLACK);

				return c;
			}
		});
		
		JScrollPane tableContainer = new JScrollPane(table);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
		table.getTableHeader().setForeground(Color.blue);
		table.getTableHeader().setBackground(new Color(51, 153, 255));
		table.setFont(new Font("Arial", Font.BOLD, 13));
		table.getTableHeader().setReorderingAllowed(false);
		tableContainer.setBounds(50, 200, 700, 400);
		this.add(tableContainer, BorderLayout.CENTER);

		sorter = new TableRowSorter<myTableSceneManagement>(tablemodel);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == suppressButton) {
			if (table.getSelectedRow() != -1) {

				int retour = JOptionPane.showConfirmDialog(this,
						"Etes-vous sûr de vouloir supprimer l'utilisateur " + tablemodel.getValueAt(table.getSelectedRow(), 2) + " "
								+ tablemodel.getValueAt(table.getSelectedRow(), 3) + " ?",
						"CONFIRM", JOptionPane.YES_NO_OPTION);

				if (retour == JOptionPane.OK_OPTION)
					tablemodel.removeRow(table.getSelectedRow());
			}
		}
		
		if (e.getSource() == affichageTemps) {
			int retour = JOptionPane.showConfirmDialog(this,
					"Le temps total enregistré pour le film est : " + (gestionScene.getTimeFilm()%60) + "Minutes" + (gestionScene.getTimeFilm() - ((gestionScene.getTimeFilm()%60)*60))+ "Secondes",
					"CONFIRM", JOptionPane.YES_OPTION);
		}

		if (e.getSource() == addButtonI) {
			JPanel AjoutSceneInterieurPanel = new AjoutSceneInterieurPanel(frame);
			frame.repaint();
			frame.revalidate();
		}
		
		if (e.getSource() == addButtonE) {
			JPanel AjoutSceneExterieurPanel = new AjoutSceneExterieurPanel(frame);
			frame.repaint();
			frame.revalidate();
		}
		
		if (e.getSource() == ouvrir) {
			JPanel AffichageSetupPanel = new AffichageSetupPanel(frame, (int)table.getValueAt(table.getSelectedRow(), 0));
			frame.repaint();
			frame.revalidate();
		}

		if (e.getSource() == searchButton) {
			if (dropDownList.getSelectedItem().equals("CodeScene")) {
				if (textZone1.getText() != null) {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textZone1.getText(), 0));
					table.setRowSorter(sorter);
				} else
					table.setAutoCreateRowSorter(true);
			}

			if (dropDownList.getSelectedItem().equals("codeTheatre")) {
				if (textZone1.getText() != null) {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textZone1.getText(), 2));
					table.setRowSorter(sorter);
				} else
					table.setAutoCreateRowSorter(true);

			}

			if (dropDownList.getSelectedItem().equals("codeLieu")) {
				if (textZone1.getText() != null) {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textZone1.getText(), 3));
					table.setRowSorter(sorter);
				} else
					table.setAutoCreateRowSorter(true);
			}

			if (dropDownList.getSelectedItem().equals("adresse")) {
				if (textZone1.getText() != null) {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textZone1.getText(), 5));
					table.setRowSorter(sorter);
				} else
					table.setAutoCreateRowSorter(true);
			}
		}
	}
	
}
