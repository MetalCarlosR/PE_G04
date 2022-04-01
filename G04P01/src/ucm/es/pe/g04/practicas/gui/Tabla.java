package ucm.es.pe.g04.practicas.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tabla extends JPanel {
    private boolean DEBUG = false;

    public Tabla(){
        super(new GridLayout(1,0));
    }
    public void init(JPanel frame, Object[][] data) {

        String[] columnNames = {"First Name",
                "Last Name",
                "Sport"};

        Object [] [] data2 = {
                {"Kathy", "Smith",
                        "Snowboarding"},
                {"John", "Doe",
                        "Rowing"},
                {"Sue", "Black",
                        "Knitting"},
                {"Jane", "White",
                        "Speed reading"},
                {"Joe", "Brown",
                        "Pool"}
        };

        final JTable table = new JTable(data2, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);

        setOpaque(true); //content panes must be opaque
        setPreferredSize(new Dimension(600, 200));
        frame.add(this);
    }

    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

}