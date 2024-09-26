package tp.ui;


import java.util.List;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

import tp.services.OrdenDeProvisionService;
import tp.services.PageRankService;

public class PanelPageRank extends JPanel{

	App menu = new App();
	PageRankService pageRankService;
	//OrdenDeProvisionService ordenDeProvisionService;

	public void inicializar() {
        pageRankService = PageRankService.getInstance();
        List<Integer> sucursales = serviceBuscarSucursales();
        System.out.println(sucursales);
        List<Integer> caminosSucursales = serviceBuscarCaminos(sucursales);
        List<Integer> caminosSucursalesOrdenado = ordenar(sucursales, caminosSucursales);
        //.out.println(caminosSucursales);
        List<String> nombreSucursales = this.pageRankService.nombreSucursales(caminosSucursalesOrdenado);
        //System.out.println(nombreSucursales);

        // Crear un modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Page Rank: orden de importancia de las sucursales segun la cantidad de caminos que llegan a ella");

        int contador = 1;
        // Agregar los nombres de las sucursales al modelo de tabla
        for (String sucursal : nombreSucursales) {
            model.addRow(new Object[]{"     " + contador + "°: Sucursal " + sucursal});
            contador++;
        }

        // Crear la tabla con el modelo
        JTable table = new JTable(model);

        // Centrar el contenido de todas las celdas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // Agregar la tabla a un JScrollPane para permitir el desplazamiento si es necesario
        JScrollPane scrollPane = new JScrollPane(table);

        // Colocar el JScrollPane en el panel principal
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
	public List<Integer> ordenar(List<Integer> sucursales, List<Integer> caminos) {
		List<Integer> result = new ArrayList<>();
        List<Integer> positions = new ArrayList<>();
        
        while (true) {
            int maxIndex = -1;
            int maxValue = Integer.MIN_VALUE;

            for (int i = 0; i < caminos.size(); i++) {
                if (caminos.get(i) > maxValue && !positions.contains(i)) {
                    maxIndex = i;
                    maxValue = caminos.get(i);
                }
            }

            if (maxIndex == -1) {
                break;
            }

            positions.add(maxIndex);
            result.add(sucursales.get(maxIndex));
        }
        
        return result;
	}
	
	public List<Integer> serviceBuscarSucursales() {
		return pageRankService.buscarSucursales();
	}
	
	public List<Integer> serviceBuscarCaminos(List<Integer> sucursales) {
		List<Integer> cantidadCaminos = new ArrayList<>();
		for(int i=0; i<sucursales.size(); i++) {
			cantidadCaminos.add(this.pageRankService.caminos(sucursales.get(i)));
		}
		return cantidadCaminos;
	}
}
