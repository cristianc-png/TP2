package tp.ui;

import java.awt.BorderLayout;
import java.awt.Window;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import tp.services.FlujoMaximoService;

public class PanelFlujoMax extends JPanel{
	
	App menu = new App();
	 FlujoMaximoService flujoMaxService;
	 
	 public void inicializar() {
		    flujoMaxService = FlujoMaximoService.getInstance();
		    List<Map<Integer, List<Integer>>> resultados = flujoMaxService.caminos();

		    // Create a model for the table
		    DefaultTableModel model = new DefaultTableModel();
		    model.addColumn("Flujo Maximo: orden de importancia de los caminos segun cuantos kilos puedan transportar");

		    // Add rows to the model based on resultados
		    int contador = 1;
		    for (Map<Integer, List<Integer>> resultadoCamino : resultados) {
		        int capacidadMinima = resultadoCamino.keySet().iterator().next();
		        List<Integer> camino = resultadoCamino.get(capacidadMinima);

		        // Create a row string based on the current resultadoCamino
		        StringBuilder rowString = new StringBuilder();
		        rowString.append(contador).append("° Camino - Capacidad maxima: ").append(capacidadMinima).append(" - Camino: ");
		        for (int i = 0; i < camino.size(); i++) {
		            rowString.append(camino.get(i));
		            if (i < camino.size() - 1) {
		                rowString.append(" -> ");
		            }
		        }

		        model.addRow(new Object[]{rowString.toString()});
		        contador++;
		    }

		    // Create the table with the model
		    JTable table = new JTable(model);

		    // Center-align the content of all cells
		    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		    table.setDefaultRenderer(Object.class, centerRenderer);

		    // Add the table to a JScrollPane for scrolling if necessary
		    JScrollPane scrollPane = new JScrollPane(table);

		    // Set up the GUI components
		    this.setLayout(new BorderLayout());
		    this.add(scrollPane, BorderLayout.CENTER);
		    JButton btnVolver = new JButton("Volver");
		      this.add(btnVolver, BorderLayout.SOUTH);
		      btnVolver.addActionListener(e -> {
		         	menu.volverAlMenuPrincipal();
		             Window oldWindow = SwingUtilities.getWindowAncestor(this);
		             // Close the old window
		             if (oldWindow != null) {
		                 oldWindow.dispose(); // Use dispose() to close the old window
		             }
		          });
		}
}

