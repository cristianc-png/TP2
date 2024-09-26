package tp.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import tp.modelo.Producto;
import tp.services.ProductoService;

public class PanelProducto extends JPanel{
	int controlador = 0;
	ProductoService productoService;
    JButton btnDarAlta;
    JButton btnDarBaja;
    JButton btnEditar;
    JButton btnBuscar;
    //JButton btnAceptar = new JButton("Aceptar");
    JButton btnVolver = new JButton("Volver");
    //Componente para la búsqueda de stock
    JTextField txtBusqueda;
    //ProductoService productoService;
    //JButton btnBuscarPorNombre = new JButton("Buscar Producto por nombre");
    public void inicializar() {
        productoService = ProductoService.getInstance();
	    btnDarAlta = new JButton("Dar de alta");
	    btnDarBaja = new JButton("Dar de baja");
	    btnEditar = new JButton("Editar");
	    btnBuscar = new JButton("Buscar");
	
	    this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH; // Rellenar todo el espacio disponible
        gbc.insets = new Insets(13, 15, 13, 15); // Espacios entre botones
        // Coordenadas de los botones
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(setButtonSize(btnDarAlta), gbc);
        gbc.gridy = 1;
        this.add(setButtonSize(btnDarBaja), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(setButtonSize(btnEditar), gbc);
        gbc.gridy = 0;
        this.add(setButtonSize(btnBuscar), gbc);
        App menu = new App();
        btnVolver = new JButton("Volver");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        this.add(setButtonSize(btnVolver), gbc);
        
        btnDarAlta.addActionListener(e -> {
        	botonDarDeAlta();
        });
        btnDarBaja.addActionListener(e -> {
        	botonDarDeBaja();
        });
        btnBuscar.addActionListener(e -> {
            botonBuscar();
        });
        btnEditar.addActionListener(e -> {
        	botonEditar();
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
    
    private void menuInicio() {
    	removeAll();
    	btnDarAlta = new JButton("Dar de alta");
 	    btnDarBaja = new JButton("Dar de baja");
 	    btnEditar = new JButton("Editar");
 	    btnBuscar = new JButton("Buscar");
 	   
 	    this.setLayout(new GridBagLayout());
         GridBagConstraints gbc = new GridBagConstraints();

         gbc.fill = GridBagConstraints.BOTH; // Rellenar todo el espacio disponible
         gbc.insets = new Insets(13, 15, 13, 15); // Espacios entre botones
         // Coordenadas de los botones
         gbc.gridx = 0;
         gbc.gridy = 0;
         this.add(setButtonSize(btnDarAlta), gbc);
         gbc.gridy = 1;
         this.add(setButtonSize(btnDarBaja), gbc);
         gbc.gridx = 1;
         gbc.gridy = 1;
         this.add(setButtonSize(btnEditar), gbc);
         gbc.gridy = 0;
         this.add(setButtonSize(btnBuscar), gbc);
         App menu = new App();
         btnVolver = new JButton("Volver");
         gbc.gridx = 0;
         gbc.gridy = 2;
         gbc.gridwidth = 2;
         this.add(setButtonSize(btnVolver), gbc);
         revalidate();
         repaint();
         btnDarAlta.addActionListener(e -> {
         	botonDarDeAlta();
         });
         btnDarBaja.addActionListener(e -> {
         	botonDarDeBaja();
         });
         btnBuscar.addActionListener(e -> {
             botonBuscar();
         });
         btnEditar.addActionListener(e -> {
         	botonEditar();
         });
         btnVolver.addActionListener(e -> {
         	menu.volverAlMenuPrincipal();
             Window oldWindow = SwingUtilities.getWindowAncestor(this);
             // Close the old window
             if (oldWindow != null) {
                 oldWindow.dispose(); // Use dispose() to close the old window
             }
          });
         revalidate();
         repaint();
    	
    }
    private void botonEditar() {
    	removeAll();
    	
        JLabel lblMensaje = new JLabel("Inserte el nombre del producto:  ");
        JTextField txtNombre = new JTextField(20);
        JButton btnAceptarNombre = new JButton("Aceptar");
       
        this.add(lblMensaje);
        this.add(txtNombre);
        this.add(btnAceptarNombre);
       
        revalidate();
        repaint();

        // Evento al presionar el botón "Aceptar"
        btnAceptarNombre.addActionListener(e -> {
            //String nombre = String.substring(txtId.getText());
        	String nombre = txtNombre.getText();
        	if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el nombre de la sucursal.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Producto producto = serviceBuscarNombre(nombre);
            if (producto != null) {
            	mostrarDatosProductos(producto);
            } else {
                JOptionPane.showMessageDialog(this, "No existe una sucursal con el nombre proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton btnVolver = new JButton("Volver");
        this.add(btnVolver);
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
       
	}
    
    private void mostrarDatosProductos(Producto producto) {
    	removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblNombre = new JLabel("Nombre: " + producto.getNombre());
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(lblNombre, gbc);
        JLabel lblDescripcion = new JLabel("Descripcion: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(lblDescripcion, gbc);
        JTextField txtDescripcion = new JTextField(producto.getDescripcion());
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(txtDescripcion, gbc);
        JLabel lblPrecioUnitario = new JLabel("Precio unitario: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(lblPrecioUnitario, gbc);
        JTextField txtPrecioUnitario = new JTextField(String.valueOf (producto.getPrecioUnitario()));
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(txtPrecioUnitario, gbc);
        JLabel lblPeso = new JLabel("Peso en Kg: ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(lblPeso, gbc);
        JTextField txtPeso = new JTextField(String.valueOf(producto.getPrecioKg()));
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(txtPeso, gbc);

        JButton btnAceptar = new JButton("Aceptar");
        gbc.gridx = 1;
        gbc.gridy = 5;
        this.add(btnAceptar, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        JButton btnVolverr = new JButton("Volver");
        this.add(btnVolverr, gbc);
        revalidate();
        repaint();

        btnAceptar.addActionListener(e -> {
            producto.setDescripcion(txtDescripcion.getText());
            try {
                double precio = Double.parseDouble (txtPrecioUnitario.getText());
                double peso = Double.parseDouble (txtPeso.getText());
                producto.setPrecioKg(peso);
                producto.setPrecioUnitario(precio);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El precio unitario y peso debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }
            serviceEditarProducto(producto);
            JOptionPane.showMessageDialog(this, "Producto modificada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.menuInicio();
            //botonEditar();
        });

        
        btnVolverr.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
	private void serviceEditarProducto(Producto p) {
    	productoService.editar(p);
	}
	

	private void botonBuscarPorNombre() {
    	removeAll();

        JLabel lblMensaje = new JLabel("Inserte el nombre del producto:  ");
        JTextField txtNombre = new JTextField(20);
        JButton btnAceptarNombre = new JButton("Aceptar");
        JButton btnVolverr = new JButton("Volver");
        
        this.add(lblMensaje);
        this.add(txtNombre);
        this.add(btnAceptarNombre);
        this.add(btnVolverr);
       
        revalidate();
        repaint();

        // Evento al presionar el botón "Aceptar"
        btnAceptarNombre.addActionListener(e -> {
            //String nombre = String.substring(txtId.getText());
        	String nombre = txtNombre.getText();
        	if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el nombre de la sucursal.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Producto producto = serviceBuscarNombre(nombre);
            if (producto != null) {
            } else {
                JOptionPane.showMessageDialog(this, "No existe una sucursal con el nombre proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            serviceBuscarNombre(nombre);
        });
        btnVolverr.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
    private Producto serviceBuscarNombre(String nombre) {
    	ProductoService productoService = ProductoService.getInstance();
        Producto producto = productoService.buscarPorNombre(nombre);

        if (producto != null) {
           mostrarProducto(producto);
        } else {
            // Si la sucursal no fue encontrada, mostrar un mensaje de error
            JOptionPane.showMessageDialog(this, "No se encontró una producto con el nombre proporcionado.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
		return producto;
    }
    
    private void mostrarProducto(Producto producto) {
   	 // Mostrar información de la sucursal encontrada
       removeAll();
       setLayout(new GridBagLayout());

       GridBagConstraints gbc = new GridBagConstraints();
       gbc.fill = GridBagConstraints.BOTH;
       gbc.insets = new Insets(13, 15, 13, 15);

       JLabel lblId = new JLabel("Nombre: " + producto.getNombre());
       this.add(lblId, gbc);

       JLabel lblNombre = new JLabel("Descripcion: " + producto.getDescripcion());
       gbc.gridy = 1;
       this.add(lblNombre, gbc);

       JLabel lblApertura = new JLabel("Precio Unitario: " + producto.getPrecioUnitario());
       gbc.gridy = 2;
       this.add(lblApertura, gbc);

       JLabel lblCierre = new JLabel("Peso en kg: " + producto.getPrecioKg());
       gbc.gridy = 3;
       this.add(lblCierre, gbc);
       JButton btnVolverr = new JButton("Volver");
       gbc.gridy = 4;
       this.add(btnVolverr, gbc);

       revalidate();
       repaint();
       
       btnVolverr.addActionListener(e -> {
       	this.menuInicio();
       });
   }

	public void botonBuscar() {
    	removeAll();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton btnBuscarPorNombre = new JButton("Buscar por nombre");
        JButton btnBuscarPorPrecio = new JButton("Buscar por precio");
        JButton btnBuscarPorPeso = new JButton("Buscar por peso en kilos");
        JButton btnVolverr = new JButton("Volver");
        
        gbc.fill = GridBagConstraints.BOTH; // Fill all available space
        gbc.insets = new Insets(13, 15, 13, 15); // Spaces between buttons
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(setButtonSize2(btnBuscarPorNombre), gbc);
        gbc.gridy = 1;
        this.add(setButtonSize2(btnBuscarPorPrecio), gbc);
        gbc.gridy = 2;
        this.add(setButtonSize2(btnBuscarPorPeso), gbc);
        gbc.gridy = 3;
        this.add(setButtonSize2(btnVolverr), gbc);
        this.revalidate();
        this.repaint();
        
        btnBuscarPorNombre.addActionListener(e -> {
            botonBuscarPorNombre();
        });
        
        btnBuscarPorPrecio.addActionListener(e -> {
            botonBuscarPorPrecio();
        });
        
        btnBuscarPorPeso.addActionListener(e -> {
            botonBuscarPorPeso();
        });
        btnVolverr.addActionListener(e -> {
        	this.menuInicio();
        });
        
    }
    
    private void botonBuscarPorPeso() {
    	removeAll();

        JLabel lblMensaje = new JLabel("Inserte el peso en Kg del producto:  ");
        JTextField txtPeso = new JTextField(20);
        JButton btnAceptarHorario = new JButton("Aceptar");
        JButton btnVolver = new JButton("Volver");
        this.add(lblMensaje);
        this.add(txtPeso);
        this.add(btnAceptarHorario);
        this.add(btnVolver);
       
        revalidate();
        repaint();

        // Evento al presionar el botón "Aceptar"
        btnAceptarHorario.addActionListener(e -> {
        	double peso = Double.valueOf(txtPeso.getText());
            serviceBuscarPorPeso(peso);
        });
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
        
	}

	private void serviceBuscarPorPeso(double peso) {
		List<Producto> productos = productoService.buscarPorPeso(peso);

        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron productos con el peso proporcionado.",
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        removeAll();
        setLayout(new BorderLayout());

        String[] columnNames = {"Nombre", "Descripcion", "Precio unitario", "Peso en kg"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Llenar la tabla con los datos de las sucursales
        for (Producto producto : productos) {
            Object[] rowData = {producto.getNombre(), producto.getDescripcion(),
                    producto.getPrecioUnitario(), (producto.getPrecioKg())};
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

	private void botonBuscarPorPrecio() {
    	removeAll();

        JLabel lblMensaje = new JLabel("Inserte el precio del producto:  ");
        JTextField txtPrecio = new JTextField(20);
        JButton btnAceptarHorario = new JButton("Aceptar");
        JButton btnVolver = new JButton("Volver");
        this.add(lblMensaje);
        this.add(txtPrecio);
        this.add(btnAceptarHorario);
        this.add(btnVolver);       
        revalidate();
        repaint();

        // Evento al presionar el botón "Aceptar"
        btnAceptarHorario.addActionListener(e -> {
        	double precio = Double.valueOf(txtPrecio.getText());
            serviceBuscarPorPrecio(precio);
        });
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
	}

	private void serviceBuscarPorPrecio(double precio) {
		List<Producto> productos = productoService.buscarPorPrecio(precio);

        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron productos con el precio proporcionado.",
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        removeAll();
        setLayout(new BorderLayout());

        String[] columnNames = {"Nombre", "Descripcion", "Precio unitario", "Peso en kg"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Llenar la tabla con los datos de las sucursales
        for (Producto producto : productos) {
            Object[] rowData = {producto.getNombre(), producto.getDescripcion(),
                    producto.getPrecioUnitario(), (producto.getPrecioKg())};
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

	private JButton setButtonSize(JButton button) {
        Dimension preferredSize = new Dimension(200, button.getPreferredSize().height);
        button.setMinimumSize(preferredSize);
        button.setPreferredSize(preferredSize);
        return button;
	}

    private JButton setButtonSize2(JButton button) {
        Dimension preferredSize = new Dimension(250, button.getPreferredSize().height);
        button.setMinimumSize(preferredSize);
        button.setPreferredSize(preferredSize);
        return button;
	}
    
    public void botonDarDeAlta() {
        removeAll();
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(12, 12, 12, 12);

        // JLabel "Complete los datos:"
        JLabel lblCompleteDatos = new JLabel("Complete los datos:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa 2 columnas
        this.add(lblCompleteDatos, gbc);
        JLabel lblId = new JLabel("Nombre");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Vuelve a ocupar 1 columna
        this.add(lblId, gbc);
        JTextField txtNombre = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtNombre, gbc);
        JLabel lblNombre = new JLabel("Descripcion");
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(lblNombre, gbc);
        JTextField txtDescripcion = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtDescripcion, gbc);
        JLabel lblApertura = new JLabel("Precio unitario");
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(lblApertura, gbc);
        JTextField txtPrecioUnitario = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtPrecioUnitario, gbc);
        JLabel lblCierre = new JLabel("Peso en Kg");
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(lblCierre, gbc);
        JTextField txtPrecioKg = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtPrecioKg, gbc);

        // Botón "Aceptar"
        JButton btnAceptar = new JButton("Aceptar");
        gbc.gridx = 1;
        gbc.gridy = 6;
        //gbc.gridwidth = 2; // Ocupa 2 columnas
        //gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        this.add(btnAceptar, gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridx = 0;
        gbc.gridy = 6;
        //gbc.anchor = GridBagConstraints.SOUTH;
        this.add(btnVolver, gbc);
        revalidate();
        repaint();
        
        btnAceptar.addActionListener(e -> {
            //System.out.println("verificar los datos y dar de alta");
        	String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();
            String precioUnitario = txtPrecioUnitario.getText();
            String pesoKg = txtPrecioKg.getText();

            if (nombre.isEmpty() || descripcion.isEmpty() || precioUnitario.isEmpty() || pesoKg.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
            	 double precio = Double.parseDouble(precioUnitario);
            	 double peso = Double.parseDouble(pesoKg);
                Producto producto = new Producto(nombre, descripcion, precio, peso);
                serviceDarDeAlta(producto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El precio unitario y peso debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
    public void serviceDarDeAlta(Producto producto) {
    	try {
            // Llamar al servicio para dar de alta la sucursal
            ProductoService productoService = ProductoService.getInstance();
            productoService.darDeAlta(producto);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Producto dada de alta correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.menuInicio();
    	} catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    	
    	}
    
    private void botonDarDeBaja() {
    	 removeAll();

    	 JLabel lblMensaje = new JLabel("Inserte el nombre del producto:  ");
         JTextField txtNombre = new JTextField(20);
         JButton btnAceptarHorario = new JButton("Aceptar");
         this.add(lblMensaje);
         this.add(txtNombre);
         this.add(btnAceptarHorario);
         JButton btnVolverr = new JButton("Volver");
         this.add(btnVolverr);
         revalidate();
         repaint();
         
         btnAceptarHorario.addActionListener(e -> {
         	String nombre = String.valueOf(txtNombre.getText());
             serviceDarDeBajaPorNombre(nombre);
             this.menuInicio();
         });
         btnVolverr.addActionListener(e -> {
         	this.menuInicio();
         });
    }
 
    @SuppressWarnings("unused")
	private void botonBuscarProductoPorNombre() {
        removeAll();

        JLabel lblMensaje = new JLabel("Inserte el nombre del producto:  ");
        this.add(lblMensaje);
        this.add(txtBusqueda);
       
        revalidate();
        repaint();
		if(controlador == 1) {
                String nombre = txtBusqueda.getText();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar el nombre del producto.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                serviceDarDeBajaPorNombre(nombre);
            ;
        }
        
        /*if (controlador == 2) {
            btnAceptar.addActionListener(e -> {
                String nombre = txtBusqueda.getText();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar el nombre del producto.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Sucursal sucursal = serviceBuscarPorNombre(nombre);
                if (sucursal != null) {
                    mostrarDatosSucursal(sucursal);
                } else {
                    JOptionPane.showMessageDialog(this, "No existe un producto con el nombre proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }*/
    }
    
    public void serviceDarDeBajaPorNombre(String nombre) {
        try {
            // Llamar a la función darDeBaja de ProductoService con el nombre proporcionado
            ProductoService productoService = ProductoService.getInstance();
            productoService.darDeBaja(nombre);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Producto dada de baja correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.menuInicio();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}