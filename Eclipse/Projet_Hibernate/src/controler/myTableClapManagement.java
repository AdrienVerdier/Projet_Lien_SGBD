package controler;

import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import model.Clap;

public class myTableClapManagement extends AbstractTableModel {
	
	private static final long serialVersionUID = 10L;
	private String[] columnNames;
	private JTable table;
	private int codeSetup;

	public myTableClapManagement(String[] columnNames, int codeSetup) {
		this.columnNames = columnNames;
		this.codeSetup = codeSetup;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return gestionClap.nombreClap(codeSetup);
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public void removeRow(int row) {
		gestionClap.supprimerClap((int) table.getValueAt(row, 0));
		
		this.fireTableDataChanged();
	}

	public Object getValueAt(int row, int col) {

		if (gestionClap.nombreClap(codeSetup) != 0) {
			Iterator<Clap> iterator = gestionClap.getClap(codeSetup).iterator();

			for (int i = 0; i < row; i++)
				iterator.next();

			Clap clapSelected = iterator.next();
			
			switch (col) {
			case 0:
				return clapSelected.getCodeClap();
			case 1:
				return clapSelected.getDuree();
			case 2:
				return clapSelected.getCodeBobine();
			case 3:
				return clapSelected.getCodeSetup();
			}
		}
		return null;
	}

	public Class<? extends Object> getColumnClass(int c) {
		if (getValueAt(0, c) != null)
			return getValueAt(0, c).getClass();
		else
			return null;
	}

}
