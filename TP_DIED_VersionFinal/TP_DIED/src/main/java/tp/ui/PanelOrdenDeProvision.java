package tp.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.List;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import tp.modelo.OrdenDeProvision;
import tp.services.OrdenDeProvisionService;

public class PanelOrdenDeProvision extends JPanel {
	//int controlador = 0;
    JButton btnOrdenesPendientes;
    JButton btnAsignarCamino;
    JButton btnOrdenesEnProceso;
    //JButton btnBuscarPorNombre = new JButton("Buscar sucursal por nombre");
    //JButton btnBuscarPorId = new JButton("Buscar sucursal por Id");
    //JButton btnAceptar = new JButton("Aceptar");
    //Componente para la búsqueda de stock
    JTextField txtBusqueda;
    
    OrdenDeProvisionService ordenDeProvisionService;
    
    public void inicializar() {
    	ordenDeProvisionService = OrdenDeProvisionService.getInstance();

	    //etiquetaTitulo = new JLabel("SUCURSALES");
	    btnOrdenesPendientes = new JButton("Asignar Camino");
	    btnAsignarCamino = new JButton("Ordenes Pendientes");
	    btnOrdenesEnProceso = new JButton("Ordenes En Proceso");
	    
	    txtBusqueda = new JTextField(20);
        txtBusqueda.setVisible(false); // Inicialmente oculto
	
	    this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH; // Rellenar todo el espacio disponible
        gbc.insets = new Insets(13, 15, 13, 15); // Espacios entre botones
        // Coordenadas de los botones
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(setButtonSize(btnOrdenesPendientes), gbc);
        gbc.gridy = 1;
        this.add(setButtonSize(btnAsignarCamino), gbc);
        gbc.gridy = 2;
        this.add(setButtonSize(btnOrdenesEnProceso), gbc);
        App menu = new App();
        JButton btnVolver = new JButton("Volver");
        gbc.gridy = 3;
        this.add(setButtonSize(btnVolver), gbc);
        //Aca se define lo que sucedera al tocar cada boton
        btnOrdenesPendientes.addActionListener(e -> {
        	botonOrdenesPendientes();
        });
        btnAsignarCamino.addActionListener(e -> {
        	botonOrdenesPendientes2();
        });
        btnOrdenesEnProceso.addActionListener(e -> {
        	botonOrdenesEnProceso();
        });
        
        btnVolver.addActionListener(e -> {
        	menu.volverAlMenuPrincipal();
            Window oldWindow = SwingUtilities.getWindowAncestor(this);
            // Close the old window
            if (oldWindow != null) {
                oldWindow.dispose(); // Use dispose() to close the old window
            }
         });
    }
    
    void menuInicio () {
    	removeAll();
    	  //etiquetaTitulo = new JLabel("SUCURSALES");
	    btnOrdenesPendientes = new JButton("Asignar Camino");
	    btnAsignarCamino = new JButton("Ordenes Pendientes");
	    btnOrdenesEnProceso = new JButton("Ordenes En Proceso");
	    
	    txtBusqueda = new JTextField(20);
        txtBusqueda.setVisible(false); // Inicialmente oculto
	
	    this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH; // Rellenar todo el espacio disponible
        gbc.insets = new Insets(13, 15, 13, 15); // Espacios entre botones
        // Coordenadas de los botones
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(setButtonSize(btnOrdenesPendientes), gbc);
        gbc.gridy = 1;
        this.add(setButtonSize(btnAsignarCamino), gbc);
        gbc.gridy = 2;
        this.add(setButtonSize(btnOrdenesEnProceso), gbc);
        App menu = new App();
        JButton btnVolver = new JButton("Volver");
        gbc.gridy = 3;
        this.add(setButtonSize(btnVolver), gbc);
        revalidate();
        repaint();
        btnOrdenesPendientes.addActionListener(e -> {
        	botonOrdenesPendientes();
        });
        btnOrdenesEnProceso.addActionListener(e -> {
        	botonOrdenesEnProceso();
        });
        
        btnAsignarCamino.addActionListener(e -> {
        	botonOrdenesPendientes2();
        });
        btnVolver.addActionListener(e -> {
        	menu.volverAlMenuPrincipal();
            Window oldWindow = SwingUtilities.getWindowAncestor(this);
            // Close the old window
            if (oldWindow != null) {
                oldWindow.dispose(); // Use dispose() to close the old window
            }
         });
        
    }
     
    private void botonOrdenesEnProceso() {
    	removeAll();
        revalidate();
        repaint();
            boolean estado = false;
            serviceBuscarEstado1(estado);
    }
    private void botonOrdenesPendientes2() {
    	removeAll();
        revalidate();
        repaint();
            boolean estado = true;
            serviceBuscarEstado1(estado);
    }
    private void botonOrdenesPendientes() {
    	removeAll();
        revalidate();
        repaint();
            boolean estado = true;
            serviceBuscarEstado(estado);
    }
    
    private void serviceBuscarEstado1(boolean estado) {
    	List<OrdenDeProvision> ordenes;
        
        // Verificar si el estado ingresado es "Pendiende" o "En proceso"
    	 ordenes = OrdenDeProvisionService.buscarPorEstado(estado);
       

        if (ordenes.isEmpty()) {
            String mensaje = estado ? "No se encontraron ordenes en pendientes." : "No se encontraron ordenes en proceso.";
            JOptionPane.showMessageDialog(this, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            this.menuInicio();
            return;
        }

        removeAll();
        setLayout(new BorderLayout());

        String[] columnNames = {"Id", "Fecha", "Sucursal Origen", "Tiempo Maximo", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Llenar la tabla con los datos de las sucursales
        for (OrdenDeProvision orden : ordenes) {
            Object[] rowData = {orden.getId(), orden.getFecha(), orden.getSucursalOrigen(),
                    orden.getTiempoMaximo(), (orden.isEstado() ? "Pendiente" : "En proceso")};
            model.addRow(rowData);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);

        revalidate();
        repaint();
        JButton btnVolver = new JButton("Volver");
        this.add(btnVolver, BorderLayout.SOUTH);
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
    private void serviceBuscarEstado(boolean estado) {
    	List<OrdenDeProvision> ordenes;
        
        // Verificar si el estado ingresado es "Pendiende" o "En proceso"
    	 ordenes = OrdenDeProvisionService.buscarPorEstado(estado);
       

        if (ordenes.isEmpty()) {
            String mensaje = estado ? "No se encontraron ordenes en pendientes." : "No se encontraron ordenes en proceso.";
            JOptionPane.showMessageDialog(this, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            this.menuInicio();
            return;
        }

        //removeAll();
        removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //setLayout(new BorderLayout());

        String[] columnNames = {"Id", "Fecha", "Sucursal Origen", "Tiempo Maximo", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Llenar la tabla con los datos de las sucursales
        for (OrdenDeProvision orden : ordenes) {
            Object[] rowData = {orden.getId(), orden.getFecha(), orden.getSucursalOrigen(),
                    orden.getTiempoMaximo(), (orden.isEstado() ? "Pendiente" : "En proceso")};
            model.addRow(rowData);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // La tabla ocupará tres columnas para cubrir el espacio de ancho
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        add(scrollPane, gbc);

        JLabel label = new JLabel("Ingrese el id de la orden que desea asignarle un camino:");
        JTextField idTextField = new JTextField(10);
        JButton btnAceptar = new JButton("Aceptar");

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        add(idTextField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        add(setButtonSize2(btnAceptar), gbc);

        JButton btnVolver = new JButton("Volver");
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // El botón "Volver" ocupará tres columnas para cubrir el espacio de ancho
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        add(setButtonSize2(btnVolver), gbc);
        revalidate();
        repaint();

        btnAceptar.addActionListener(e -> {
        	
        	try {
        		int id = Integer.parseInt(idTextField.getText());
        		List<List<Integer>> caminos = serviceBuscarOrden(id);
        		String prueba = convertListOfListsToString(caminos);
        		System.out.println(caminos);
        		if (caminos.size() == 0) {
        			JOptionPane.showMessageDialog(this, "No hay caminos disponibles para la orden.", "Fallo", JOptionPane.INFORMATION_MESSAGE);
                	this.menuInicio();
        		}
        		else {
        		List<List<String>> caminosNombres = new ArrayList<>();
        		for(int i=0; i<caminos.size(); i++) {
        			List<String> aux = serviceListaString(caminos.get(i));
        			caminosNombres.add(aux);
        		}
        		//for(int i=0; i<caminosNombres.size(); i++) {
        			//System.out.println(caminosNombres.get(i));
        		//}
        		//System.out.println(caminosNombres);
        		//this.grafo(caminosNombres);
        		
        		//System.out.println(ordenDeProvisionService.caminosRutas(caminos));
        		List<List<Integer>> caminosRutas = ordenDeProvisionService.caminosRutas(caminos);
        		List<Integer> tiempoCaminos = this.ordenDeProvisionService.tiempoCaminos(caminosRutas);
        		//System.out.println(tiempoCaminos);
        		
        		for(int i=0; i<caminosNombres.size(); i++) {
        			caminosNombres.get(i).add("Tiempo: " + String.valueOf(tiempoCaminos.get(i)) + " horas");
        		}
        		this.grafo(caminosNombres, id);
            	
        		}
        	} catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        	
        });
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
    public List<String> serviceListaString(List<Integer> ids) {
    	return this.ordenDeProvisionService.listaString(ids);
    }
    
    public String convertListOfListsToString(List<List<Integer>> listOfLists) {
        StringBuilder sb = new StringBuilder();

        for (List<Integer> innerList : listOfLists) {
            for (Integer item : innerList) {
                sb.append(item).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
    
    private List<List<Integer>> serviceBuscarOrden(int id) {
    	OrdenDeProvision orden = this.ordenDeProvisionService.buscarOrden(id);
    	List<List<Integer>> lista = null;
    	if (orden != null) {
    		lista = serviceBuscarSucursalOrden(orden.getSucursalOrigen());
         } else {
             // Si la sucursal no fue encontrada, mostrar un mensaje de error
             JOptionPane.showMessageDialog(this, "No existe una orden de provision con el ID proporcionado.",
                     "Error", JOptionPane.ERROR_MESSAGE);
         }
    	return lista;
    }
    
    private List<List<Integer>> serviceBuscarSucursalOrden(int idSucursal) {
    	return ordenDeProvisionService.caminos(idSucursal);
    }
    
    private void grafo(List<List<String>> caminos, int id) {
    	removeAll();
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Establecer diseño vertical

    	
		GrafoCanvas canvas = new GrafoCanvas();
		canvas.setPreferredSize(new Dimension(690, 400)); // Establece el tamaño preferido para el canvas

		add(canvas);
		
		JLabel label = new JLabel("Ingrese el camino que prefiera:");
		add(label);
		// JComboBox
        /*String[] peliculas = {
            "Titanic",
            "El Padrino",
            "Pulp Fiction",
            "El Señor de los Anillos",
            "Star Wars",
            "Matrix",
            "El Rey León",
            "Harry Potter",
            "Avatar",
            "Forrest Gump"
        };
        JComboBox<String> comboBox = new JComboBox<>(peliculas);*/
		List<String> valoresParaComboBox = new ArrayList<>();
        for (List<String> lista : caminos) {
            if (!lista.isEmpty()) {
                valoresParaComboBox.add(lista.toString()); // Añadir el primer elemento de cada lista
            }
        }
        String[] valoresArray = valoresParaComboBox.toArray(new String[0]);
        JComboBox<String> comboBox = new JComboBox<>(valoresArray);
		
		
		

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new BoxLayout(comboBoxPanel, BoxLayout.X_AXIS));
        comboBoxPanel.setAlignmentX(LEFT_ALIGNMENT);
        comboBoxPanel.add(comboBox);

        JButton aceptarBoton = new JButton("Aceptar");
        comboBoxPanel.add(setButtonSize2(aceptarBoton));

        add(comboBoxPanel);
        revalidate();
        repaint();
        
        // Acción del botón de agregar
        /*aceptarBoton.addActionListener(/*new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String elemento = (String) comboBox.getSelectedItem();
                //listModel.addElement(elemento);
            }*
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        });*/
        aceptarBoton.addActionListener(e -> {
        	ordenDeProvisionService.cambiar(id);
        	JOptionPane.showMessageDialog(this, "Camino asignado correctamento.", "Exito", JOptionPane.INFORMATION_MESSAGE);
        	this.menuInicio();
        });
		}
    
    
    
    private JButton setButtonSize(JButton button) {
        Dimension preferredSize = new Dimension(200, button.getPreferredSize().height);
        button.setMinimumSize(preferredSize);
        button.setPreferredSize(preferredSize);
        return button;
	}

    private JButton setButtonSize2(JButton button) {
        Dimension preferredSize = new Dimension(100, button.getPreferredSize().height);
        button.setMinimumSize(preferredSize);
        button.setPreferredSize(preferredSize);
        return button;
	}
}