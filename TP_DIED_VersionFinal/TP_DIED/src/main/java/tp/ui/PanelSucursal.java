package tp.ui;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import tp.services.ProductoService;
import tp.services.SucursalService;
import tp.modelo.*;

public class PanelSucursal extends JPanel{

    JButton btnDarAlta;
    JButton btnDarBaja;
    JButton btnEditar;
    JButton btnBuscar;
    JButton btnStock;
    JButton btnOrden;
    //JButton btnAceptar = new JButton("Aceptar");
    //Componente para la búsqueda de stock
    JTextField txtBusqueda;
    
    SucursalService sucursalService;
    ProductoService productoService;
    
    public void inicializar() {
        sucursalService = SucursalService.getInstance();
        productoService = ProductoService.getInstance();

	    //etiquetaTitulo = new JLabel("SUCURSALES");
	    btnDarAlta = new JButton("Dar de alta");
	    btnDarBaja = new JButton("Dar de baja");
	    btnEditar = new JButton("Editar");
	    btnBuscar = new JButton("Buscar");
	    btnStock = new JButton("Stock");
	    btnOrden = new JButton("Crear orden de provision");
	    
	    txtBusqueda = new JTextField(20);
        txtBusqueda.setVisible(false); // Inicialmente oculto
	
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
        gbc.gridy = 2;
        this.add(setButtonSize(btnEditar), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(setButtonSize(btnBuscar), gbc);
        gbc.gridy = 1;
        this.add(setButtonSize(btnStock), gbc);
        gbc.gridy = 2;
        this.add(setButtonSize(btnOrden), gbc);
        App menu = new App();
        JButton btnVolver = new JButton("Volver");
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        this.add(setButtonSize(btnVolver), gbc);
        revalidate();
        repaint();
        
        //Aca se define lo que sucedera al tocar cada boton
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
        btnStock.addActionListener(e -> {
        	botonStock();
        });
        btnOrden.addActionListener(e -> {
        	botonOrdenDeProvision();
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
    void menuInicio() {
    	removeAll();
    	  //etiquetaTitulo = new JLabel("SUCURSALES");
	    btnDarAlta = new JButton("Dar de alta");
	    btnDarBaja = new JButton("Dar de baja");
	    btnEditar = new JButton("Editar");
	    btnBuscar = new JButton("Buscar");
	    btnStock = new JButton("Stock");
	    btnOrden = new JButton("Crear orden de provision");
	    
	    txtBusqueda = new JTextField(20);
        txtBusqueda.setVisible(false); // Inicialmente oculto
	
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
        gbc.gridy = 2;
        this.add(setButtonSize(btnEditar), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(setButtonSize(btnBuscar), gbc);
        gbc.gridy = 1;
        this.add(setButtonSize(btnStock), gbc);
        gbc.gridy = 2;
        this.add(setButtonSize(btnOrden), gbc);
        App menu = new App();
        JButton btnVolver = new JButton("Volver");
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        this.add(setButtonSize(btnVolver), gbc);
        revalidate();
        repaint();
        
        //Aca se define lo que sucedera al tocar cada boton
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
        btnStock.addActionListener(e -> {
        	botonStock();
        });
        btnOrden.addActionListener(e -> {
        	botonOrdenDeProvision();
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
    public void botonDarDeAlta() {
        removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel lblCompleteDatos = new JLabel("Complete los datos:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa 2 columnas
        this.add(lblCompleteDatos, gbc);
        JLabel lblId = new JLabel("Id");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Vuelve a ocupar 1 columna
        this.add(lblId, gbc);
        JTextField txtId = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtId, gbc);
        JLabel lblNombre = new JLabel("Nombre");
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(lblNombre, gbc);
        JTextField txtNombre = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtNombre, gbc);
        JLabel lblApertura = new JLabel("Horario de apertura");
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(lblApertura, gbc);
        JTextField txtApertura = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtApertura, gbc);
        JLabel lblCierre = new JLabel("Horario de cierre");
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(lblCierre, gbc);
        JTextField txtCierre = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtCierre, gbc);
        JLabel lblEstado = new JLabel("Estado");
        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(lblEstado, gbc);
        // Creamos los JRadioButton y los agrupamos en un ButtonGroup para que sean excluyentes
        JRadioButton rbOperativa = new JRadioButton("Operativa");
        JRadioButton rbNoOperativa = new JRadioButton("No operativa");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbOperativa);
        buttonGroup.add(rbNoOperativa);
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Panel para alinear los radio buttons
        radioPanel.add(rbOperativa);
        radioPanel.add(rbNoOperativa);
        gbc.gridx = 1;
        this.add(radioPanel, gbc);

        // Botón "Aceptar"
        JButton btnAceptar = new JButton("Aceptar");
        gbc.gridx = 1;
        gbc.gridy = 6;
        //gbc.gridwidth = 2; // Ocupa 2 columnas
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        this.add(btnAceptar, gbc);
        
        JButton btnVolver = new JButton("Volver");
        gbc.gridx = 0;
        gbc.gridy = 6;
        //gbc.gridwidth = 2; // Ocupa 2 columnas
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(btnVolver, gbc);
        revalidate();
        repaint();
        
        btnAceptar.addActionListener(e -> {
            //System.out.println("verificar los datos y dar de alta");
        	String idText = txtId.getText();
            String nombre = txtNombre.getText();
            String aperturaText = txtApertura.getText();
            String cierreText = txtCierre.getText();

            if (idText.isEmpty() || nombre.isEmpty() || aperturaText.isEmpty() || cierreText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(idText);
                Time apertura = Time.valueOf(aperturaText);
                Time cierre = Time.valueOf(cierreText);

                Sucursal sucursal = new Sucursal(id, nombre, apertura, cierre, rbOperativa.isSelected());
                serviceDarDeAlta(sucursal);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "El formato de horario debe ser HH:mm:ss.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
    public void serviceDarDeAlta(Sucursal sucursal) {
    	try {
            // Llamar al servicio para dar de alta la sucursal
            SucursalService sucursalService = SucursalService.getInstance();
            sucursalService.darDeAlta(sucursal);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Sucursal dada de alta correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.menuInicio();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void botonDarDeBaja() {
    	removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JButton btnBuscarPorNombreDarDeBaja = new JButton("Buscar sucursal por nombre");
        JButton btnBuscarPorIdDarDeBaja = new JButton("Buscar sucursal por Id");
        JButton btnVolverDarDeBaja = new JButton("Volver");
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(13, 15, 13, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(setButtonSize(btnBuscarPorIdDarDeBaja), gbc);
        gbc.gridy = 1;
        this.add(setButtonSize(btnBuscarPorNombreDarDeBaja), gbc);
        gbc.gridy = 2;
        this.add(setButtonSize(btnVolverDarDeBaja), gbc);
        revalidate();
        repaint();
        
        btnBuscarPorIdDarDeBaja.addActionListener(e -> {
            txtBusqueda.setVisible(true);
            botonBuscarSucursalPorIdDarDeBaja();
        });
        btnBuscarPorNombreDarDeBaja.addActionListener(e -> {
            txtBusqueda.setVisible(true);
            botonBuscarSucursalPorNombreDarDeBaja();
        });
        
        btnVolverDarDeBaja.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
    private void botonBuscarSucursalPorIdDarDeBaja() {
    	removeAll();
        JLabel lblMensaje = new JLabel("Inserte el ID de la sucursal:  ");
        JButton btnAceptarDarBaja = new JButton("Aceptar");
        this.add(lblMensaje);
        this.add(txtBusqueda);
        this.add(btnAceptarDarBaja);
        revalidate();
        repaint();
        
        btnAceptarDarBaja.addActionListener(e -> {
        		String idText = txtBusqueda.getText();
                if (idText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar el ID de la sucursal.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int id = Integer.parseInt(idText);
                    serviceDarDeBajaPorId(id);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        	  JButton btnVolver = new JButton("Volver");
              this.add(btnVolver);
              btnVolver.addActionListener(e -> {
              	this.menuInicio();
              });
    }  
    
    private void botonBuscarSucursalPorNombreDarDeBaja() {
        removeAll();
        JLabel lblMensaje = new JLabel("Inserte el nombre de la sucursal:  ");
        JButton btnAceptarDarBaja2 = new JButton("Aceptar");
        this.add(lblMensaje);
        this.add(txtBusqueda);
        this.add(btnAceptarDarBaja2);
        revalidate();
        repaint();
        
        btnAceptarDarBaja2.addActionListener(e -> {
                String nombre = txtBusqueda.getText();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar el nombre de la sucursal.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                serviceDarDeBajaPorNombre(nombre);
            });
        	  JButton btnVolver = new JButton("Volver");
              this.add(btnVolver);
              btnVolver.addActionListener(e -> {
              	this.menuInicio();
              });
    }
    
    public void serviceDarDeBajaPorId(int id) {
        try {
            // Llamar a la función darDeBaja de SucursalService
            //SucursalService sucursalService = SucursalService.getInstance();
            sucursalService.darDeBaja(id);
            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Sucursal dada de baja correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.menuInicio();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void serviceDarDeBajaPorNombre(String nombre) {
        try {
            // Llamar a la función darDeBaja de SucursalService con el nombre proporcionado
            SucursalService sucursalService = SucursalService.getInstance();
            sucursalService.darDeBaja(nombre);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Sucursal dada de baja correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.menuInicio();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void botonEditar() {
    	removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JButton btnBuscarPorNombreEditar = new JButton("Buscar sucursal por nombre");
        JButton btnBuscarPorIdEditar = new JButton("Buscar sucursal por Id");
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(13, 15, 13, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(setButtonSize(btnBuscarPorIdEditar), gbc);
        gbc.gridy = 1;
        this.add(setButtonSize(btnBuscarPorNombreEditar), gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridy = 2;
        this.add(setButtonSize(btnVolver), gbc);
        revalidate();
        repaint();
        
        btnBuscarPorIdEditar.addActionListener(e -> {
            txtBusqueda.setVisible(true);
            botonBuscarSucursalPorIdEditar();
        });
        btnBuscarPorNombreEditar.addActionListener(e -> {
            txtBusqueda.setVisible(true);
            botonBuscarSucursalPorNombreEditar();
        });
        
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
    private void botonBuscarSucursalPorIdEditar() {
    	removeAll();
        JLabel lblMensaje = new JLabel("Inserte el ID de la sucursal:  ");
        JButton btnAceptar2 = new JButton("Aceptar");
        this.add(lblMensaje);
        this.add(txtBusqueda);
        this.add(btnAceptar2);
        revalidate();
        repaint();
        
        btnAceptar2.addActionListener(e -> {
                String idText = txtBusqueda.getText();
                if (idText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar el ID de la sucursal.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int id = Integer.parseInt(idText);
                    Sucursal sucursal = serviceBuscarPorId(id);
                    if (sucursal != null) {
                        mostrarDatosSucursal(sucursal);
                    } else {
                        JOptionPane.showMessageDialog(this, "No existe una sucursal con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            JButton btnVolver = new JButton("Volver");
            this.add(btnVolver);
            btnVolver.addActionListener(e -> {
            	this.menuInicio();
            });
        
    }  
    
    private void botonBuscarSucursalPorNombreEditar() {
        removeAll();
        JLabel lblMensaje = new JLabel("Inserte el nombre de la sucursal:  ");
        JButton btnAceptar3 = new JButton("Aceptar");
        this.add(lblMensaje);
        this.add(txtBusqueda);
        this.add(btnAceptar3);
        revalidate();
        repaint();

        
        btnAceptar3.addActionListener(e -> {
                String nombre = txtBusqueda.getText();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar el nombre de la sucursal.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Sucursal sucursal = serviceBuscarPorNombre(nombre);
                if (sucursal != null) {
                    mostrarDatosSucursal(sucursal);
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
    
    private void botonBuscar() {
        removeAll();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton btnBuscarPorId = new JButton("Buscar por Id");
        JButton btnBuscarPorNombre = new JButton("Buscar por nombre");
        JButton btnBuscarPorHorarioApertura = new JButton("Buscar por horario de apertura");
        JButton btnBuscarPorHorarioCierre = new JButton("Buscar por horario de cierre");
        JButton btnBuscarPorEstado = new JButton("Buscar por estado");
       
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(13, 15, 13, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(setButtonSize2(btnBuscarPorId), gbc);
        gbc.gridy = 1;
        this.add(setButtonSize2(btnBuscarPorNombre), gbc);
        gbc.gridy = 2;
        this.add(setButtonSize2(btnBuscarPorHorarioApertura), gbc);
        gbc.gridy = 3;
        this.add(setButtonSize2(btnBuscarPorHorarioCierre), gbc);
        gbc.gridy = 4;
        this.add(setButtonSize2(btnBuscarPorEstado), gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridy = 5;
        this.add(setButtonSize2(btnVolver), gbc);
        this.revalidate();
        this.repaint();
        
        btnBuscarPorId.addActionListener(e -> {
            botonBuscarPorId();
        });
        
        btnBuscarPorNombre.addActionListener(e -> {
            botonBuscarPorNombre();
        });
        
        btnBuscarPorHorarioApertura.addActionListener(e -> {
            botonBuscarPorHorarioApertura();
        });
        
        btnBuscarPorHorarioCierre.addActionListener(e -> {
            botonBuscarPorHorarioCierre();
        });
        
        btnBuscarPorEstado.addActionListener(e -> {
            botonBuscarPorEstado();
        });
        
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
    private void botonBuscarPorId() {
    	removeAll();

        JLabel lblMensaje = new JLabel("Inserte el ID de la sucursal:  ");
        JTextField txtId = new JTextField(20);
        JButton btnAceptarId = new JButton("Aceptar");
        this.add(lblMensaje);
        this.add(txtId);
        this.add(btnAceptarId);
       
        revalidate();
        repaint();

        // Evento al presionar el botón "Aceptar"
        btnAceptarId.addActionListener(e -> {
            int id = Integer.parseInt(txtId.getText());
            serviceBuscarId(id);
        });
        JButton btnVolver = new JButton("Volver");
        this.add(btnVolver);
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    } 
    private void serviceBuscarId(int id) {
    	SucursalService sucursalService = SucursalService.getInstance();
        Sucursal sucursal = sucursalService.buscarPorId(id);

        if (sucursal != null) {
           mostrarSucursal(sucursal);
        } else {
            // Si la sucursal no fue encontrada, mostrar un mensaje de error
            JOptionPane.showMessageDialog(this, "No se encontró una sucursal con el ID proporcionado.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void botonBuscarPorNombre() {
    	removeAll();

        JLabel lblMensaje = new JLabel("Inserte el nombre de la sucursal:  ");
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
            serviceBuscarNombre(nombre);
        });
        JButton btnVolver = new JButton("Volver");
        this.add(btnVolver);
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    private void serviceBuscarNombre(String nombre) {
    	SucursalService sucursalService = SucursalService.getInstance();
        Sucursal sucursal = sucursalService.buscarPorNombre(nombre);

        if (sucursal != null) {
           mostrarSucursal(sucursal);
        } else {
            // Si la sucursal no fue encontrada, mostrar un mensaje de error
            JOptionPane.showMessageDialog(this, "No se encontró una sucursal con el nombre proporcionado.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarSucursal(Sucursal sucursal) {
        removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(13, 15, 13, 15);
        JLabel lblId = new JLabel("ID: " + sucursal.getId());
        this.add(lblId, gbc);
        JLabel lblNombre = new JLabel("Nombre: " + sucursal.getNombre());
        gbc.gridy = 1;
        this.add(lblNombre, gbc);
        JLabel lblApertura = new JLabel("Horario de apertura: " + sucursal.getHorarioApertura());
        gbc.gridy = 2;
        this.add(lblApertura, gbc);
        JLabel lblCierre = new JLabel("Horario de cierre: " + sucursal.getHorarioCierre());
        gbc.gridy = 3;
        this.add(lblCierre, gbc);
        JLabel lblEstado = new JLabel("Estado: " + (sucursal.isEstado() ? "Operativa" : "No operativa"));
        gbc.gridy = 4;
        this.add(lblEstado, gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridy = 5;
        this.add(btnVolver, gbc);
        revalidate();
        repaint();
        
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
    private void botonBuscarPorHorarioApertura(){
    	removeAll();

        JLabel lblMensaje = new JLabel("Inserte el horario de apertura de la sucursal:  ");
        JTextField txtHorario = new JTextField(20);
        JButton btnAceptarHorario = new JButton("Aceptar");
        this.add(lblMensaje);
        this.add(txtHorario);
        this.add(btnAceptarHorario);
       
        revalidate();
        repaint();

        // Evento al presionar el botón "Aceptar"
        btnAceptarHorario.addActionListener(e -> {
        	Time horarioApertura = Time.valueOf(txtHorario.getText());
            serviceBuscarHorarioApertura(horarioApertura);
        });
        JButton btnVolver = new JButton("Volver");
        this.add(btnVolver);
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    } 
    private void serviceBuscarHorarioApertura(Time apertura) {
    	List<Sucursal> sucursales = sucursalService.buscarPorHorarioApertura(apertura);

        if (sucursales.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron sucursales con el horario de apertura proporcionado.",
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        removeAll();
        setLayout(new BorderLayout());

        String[] columnNames = {"Id", "Nombre", "Horario Apertura", "Horario Cierre", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Llenar la tabla con los datos de las sucursales
        for (Sucursal sucursal : sucursales) {
            Object[] rowData = {sucursal.getId(), sucursal.getNombre(), sucursal.getHorarioApertura(),
                    sucursal.getHorarioCierre(), (sucursal.isEstado() ? "Operativa" : "No operativa")};
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
    
    private void botonBuscarPorHorarioCierre(){
    	removeAll();

        JLabel lblMensaje = new JLabel("Inserte el horario de cierre de la sucursal:  ");
        JTextField txtHorario = new JTextField(20);
        JButton btnAceptarHorario = new JButton("Aceptar");
        this.add(lblMensaje);
        this.add(txtHorario);
        this.add(btnAceptarHorario);
       
        revalidate();
        repaint();

        // Evento al presionar el botón "Aceptar"
        btnAceptarHorario.addActionListener(e -> {
        	Time horarioCierre = Time.valueOf(txtHorario.getText());
            serviceBuscarHorarioCierre(horarioCierre);
        });
        JButton btnVolver = new JButton("Volver");
        this.add(btnVolver);
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    private void serviceBuscarHorarioCierre(Time cierre) {
    	List<Sucursal> sucursales = sucursalService.buscarPorHorarioCierre(cierre);

        if (sucursales.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron sucursales con el horario de cierre proporcionado.",
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        removeAll();
        setLayout(new BorderLayout());

        String[] columnNames = {"Id", "Nombre", "Horario Apertura", "Horario Cierre", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Llenar la tabla con los datos de las sucursales
        for (Sucursal sucursal : sucursales) {
            Object[] rowData = {sucursal.getId(), sucursal.getNombre(), sucursal.getHorarioApertura(),
                    sucursal.getHorarioCierre(), (sucursal.isEstado() ? "Operativa" : "No operativa")};
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
    
    private void botonBuscarPorEstado() {
    	removeAll();

        JLabel lblMensaje = new JLabel("Inserte el estado de la sucursal:  ");
        JTextField txtEstado = new JTextField(20);
        JButton btnAceptarEstado = new JButton("Aceptar");
        this.add(lblMensaje);
        this.add(txtEstado);
        this.add(btnAceptarEstado);
       
        revalidate();
        repaint();

        btnAceptarEstado.addActionListener(e -> {
            String estadoText = txtEstado.getText();
            boolean estado = false;

            // Verificar si el texto coincide con "Operativa" o "No operativa"
            if (estadoText.equalsIgnoreCase("Operativa")) {
                estado = true;
            } else if (estadoText.equalsIgnoreCase("No operativa")) {
                estado = false;
            } else {
                // Si el texto ingresado no coincide con ninguna de las opciones, mostrar mensaje de error
                JOptionPane.showMessageDialog(this, "Por favor, ingrese 'Operativa' o 'No operativa' en el cuadro de texto.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del evento sin hacer nada más
            }

            serviceBuscarEstado(estado);
        });
        JButton btnVolver = new JButton("Volver");
        this.add(btnVolver);
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    private void serviceBuscarEstado(boolean estado) {
    	List<Sucursal> sucursales;
        
        // Verificar si el estado ingresado es "Operativa" o "No operativa"
        if (estado) {
            // Si es "Operativa", buscar las sucursales con estado "true" (Operativa)
            sucursales = sucursalService.buscarPorEstado(true);
        } else {
            // Si es "No operativa", buscar las sucursales con estado "false" (No operativa)
            sucursales = sucursalService.buscarPorEstado(false);
        }

        if (sucursales.isEmpty()) {
            String mensaje = estado ? "No se encontraron sucursales operativas." : "No se encontraron sucursales no operativas.";
            JOptionPane.showMessageDialog(this, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        removeAll();
        setLayout(new BorderLayout());

        String[] columnNames = {"Id", "Nombre", "Horario Apertura", "Horario Cierre", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Llenar la tabla con los datos de las sucursales
        for (Sucursal sucursal : sucursales) {
            Object[] rowData = {sucursal.getId(), sucursal.getNombre(), sucursal.getHorarioApertura(),
                    sucursal.getHorarioCierre(), (sucursal.isEstado() ? "Operativa" : "No operativa")};
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
    
    private void botonStock() {
    	removeAll();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton btnActualizarStock = new JButton("Actualizar stock");
        JButton btnDarDeAlta = new JButton("Dar de alta producto");
        JButton btnDarDeBaja = new JButton("Dar de baja producto");
        JButton btnVerStock = new JButton("Ver stock");
       
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(13, 15, 13, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(setButtonSize2(btnActualizarStock), gbc);
        gbc.gridy = 1;
        this.add(setButtonSize2(btnDarDeAlta), gbc);
        gbc.gridy = 2;
        this.add(setButtonSize2(btnDarDeBaja), gbc);
        gbc.gridy = 3;
        this.add(setButtonSize2(btnVerStock), gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridy = 4;
        this.add(setButtonSize2(btnVolver), gbc);
        this.revalidate();
        this.repaint();
        
        btnActualizarStock.addActionListener(e -> {
            buscarSucursalYProductoActualizarStock();
        });
        btnDarDeAlta.addActionListener(e -> {
        	buscarSucursalYProducto2();
        });
        btnDarDeBaja.addActionListener(e -> {
        	buscarSucursalYProductoDarDeBajaStock();
        });
        btnVerStock.addActionListener(e -> {
        	buscarSucursal();
        });
        
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
    private void buscarSucursalYProductoActualizarStock() {
    	removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JButton btnBuscarPorNombreActualizarStock = new JButton("Buscar sucursal por nombre");
        JButton btnBuscarPorIdActualizarStock = new JButton("Buscar sucursal por Id");
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(13, 15, 13, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(setButtonSize(btnBuscarPorIdActualizarStock), gbc);
        gbc.gridy = 1;
        this.add(setButtonSize(btnBuscarPorNombreActualizarStock), gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridy = 2;
        this.add(setButtonSize(btnVolver), gbc);
        revalidate();
        repaint();
        
        btnBuscarPorIdActualizarStock.addActionListener(e -> {
            txtBusqueda.setVisible(true);
            botonBuscarSucursalPorIdYProductoActualizarStock();
        });
        btnBuscarPorNombreActualizarStock.addActionListener(e -> {
            txtBusqueda.setVisible(true);
            botonBuscarSucursalPorNombreYProductoActualizarStock();
        });
        
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
    private void botonBuscarSucursalPorIdYProductoActualizarStock() {
    	removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JTextField txtSucursal = new JTextField();
        JTextField txtProducto = new JTextField();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel lblMensaje = new JLabel("Inserte el ID de la sucursal:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblMensaje, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtSucursal, gbc);
        JLabel lblProducto = new JLabel("Inserte el nombre del producto:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        add(lblProducto, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtProducto, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        //gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        //gbc.fill = GridBagConstraints.NONE;
        JButton btnAceptar4 = new JButton("Aceptar");
        add(btnAceptar4, gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridx = 0;
        gbc.gridy = 2;
        //gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        //gbc.fill = GridBagConstraints.NONE;
        this.add(btnVolver, gbc);
        revalidate();
        repaint();
        
        btnAceptar4.addActionListener(e -> {
                String idText = txtSucursal.getText();
                String idText2 = txtProducto.getText();
                if (idText.isEmpty() || idText2.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe completar todos los datos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int id = Integer.parseInt(idText);
                    Sucursal sucursal = serviceBuscarPorId(id);
                    Producto producto = serviceBuscarProducto(idText2);
                    if (sucursal != null && producto != null) {
                    	//mostrarDatosProducto(id, producto.getNombre());
                    	mostrarDatosStock(sucursalService.obtenerStock(id, idText2));
                    	//JOptionPane.showMessageDialog(this, "Stock actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "No existe una sucursal o un producto con los datos proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        	  
              btnVolver.addActionListener(e -> {
              	this.menuInicio();
              });
    }
    
    private void botonBuscarSucursalPorNombreYProductoActualizarStock() {
    	removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JTextField txtSucursal = new JTextField();
        JTextField txtProducto = new JTextField();
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel lblMensaje = new JLabel("Inserte el nombre de la sucursal:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblMensaje, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtSucursal, gbc);
        JLabel lblProducto = new JLabel("Inserte el nombre del producto:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        add(lblProducto, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtProducto, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        //gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        //gbc.fill = GridBagConstraints.NONE;
        JButton btnAceptar5 = new JButton("Aceptar");
        add(btnAceptar5, gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridx = 0;
        gbc.gridy = 2;
        //gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        //gbc.fill = GridBagConstraints.NONE;
        this.add(btnVolver, gbc);
        revalidate();
        repaint();
        
        btnAceptar5.addActionListener(e -> {
                String idText = txtSucursal.getText();
                String idText2 = txtProducto.getText();
                if (idText.isEmpty() || idText2.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe completar todos los datos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Sucursal sucursal = serviceBuscarPorNombre(idText);
                    Producto producto = serviceBuscarProducto(idText2);
                    if (sucursal != null && producto != null) {
                    	//mostrarDatosProducto(id, producto.getNombre());
                    	mostrarDatosStock(sucursalService.obtenerStock(sucursal.getId(), idText2));
                    	//JOptionPane.showMessageDialog(this, "Stock actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "No existe una sucursal o un producto con los datos proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        	  
              btnVolver.addActionListener(e -> {
              	this.menuInicio();
              });
    }
    
    private void buscarSucursalYProductoDarDeBajaStock() {
    	removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JButton btnBuscarPorNombreStock = new JButton("Buscar sucursal por nombre");
        JButton btnBuscarPorIdStock = new JButton("Buscar sucursal por Id");
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(13, 15, 13, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(setButtonSize(btnBuscarPorIdStock), gbc);
        gbc.gridy = 1;
        this.add(setButtonSize(btnBuscarPorNombreStock), gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridy = 2;
        this.add(setButtonSize(btnVolver), gbc);
        revalidate();
        repaint();
        
        btnBuscarPorIdStock.addActionListener(e -> {
            txtBusqueda.setVisible(true);
            botonBuscarSucursalPorIdYProductoDarDeBajaStock();
        });
        btnBuscarPorNombreStock.addActionListener(e -> {
            txtBusqueda.setVisible(true);
            botonBuscarSucursalPorNombreYProductoDarDeBajaStock();
        });
        
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
    private void botonBuscarSucursalPorIdYProductoDarDeBajaStock() {
    	removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JTextField txtSucursal = new JTextField();
        JTextField txtProducto = new JTextField();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel lblMensaje = new JLabel("Inserte el ID de la sucursal:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblMensaje, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtSucursal, gbc);
        JLabel lblProducto = new JLabel("Inserte el nombre del producto:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        add(lblProducto, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtProducto, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        //gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        //gbc.fill = GridBagConstraints.NONE;
        JButton btnAceptar6 = new JButton("Aceptar");
        add(btnAceptar6, gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridx = 0;
        gbc.gridy = 2;
        //gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        //gbc.fill = GridBagConstraints.NONE;
        this.add(btnVolver, gbc);
        revalidate();
        repaint();
        
        btnAceptar6.addActionListener(e -> {
                String idText = txtSucursal.getText();
                String idText2 = txtProducto.getText();
                if (idText.isEmpty() || idText2.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe completar todos los datos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int id = Integer.parseInt(idText);
                    Sucursal sucursal = serviceBuscarPorId(id);
                    Producto producto = serviceBuscarProducto(idText2);
                    if (sucursal != null && producto != null) {
                    	serviceDarDeBajaProducto(id, producto.getNombre());
                    	JOptionPane.showMessageDialog(this, "Producto dado de baja correctamente de la sucursal.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    	this.menuInicio();
                    } else {
                        JOptionPane.showMessageDialog(this, "No existe una sucursal o un producto con los datos proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        	  
              btnVolver.addActionListener(e -> {
              	this.menuInicio();
              });
    }
    
    private void buscarSucursalYProducto2() {
    	removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JButton btnBuscarPorNombreDarDeAltaStock = new JButton("Buscar sucursal por nombre");
        JButton btnBuscarPorIdDarDeAltaStock = new JButton("Buscar sucursal por Id");
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(13, 15, 13, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(setButtonSize(btnBuscarPorIdDarDeAltaStock), gbc);
        gbc.gridy = 1;
        this.add(setButtonSize(btnBuscarPorNombreDarDeAltaStock), gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridy = 2;
        this.add(setButtonSize(btnVolver), gbc);
        revalidate();
        repaint();
        
        btnBuscarPorIdDarDeAltaStock.addActionListener(e -> {
            txtBusqueda.setVisible(true);
            botonBuscarSucursalPorIdYProducto2();
        });
        btnBuscarPorNombreDarDeAltaStock.addActionListener(e -> {
            txtBusqueda.setVisible(true);
            botonBuscarSucursalPorNombreYProducto2();
        });
        
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
    private void botonBuscarSucursalPorIdYProducto2() {
    	removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JTextField txtSucursal = new JTextField();
        JTextField txtProducto = new JTextField();
        JTextField txtCantidad = new JTextField();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel lblMensaje = new JLabel("Inserte el ID de la sucursal:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblMensaje, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtSucursal, gbc);
        JLabel lblProducto = new JLabel("Inserte el nombre del producto:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        add(lblProducto, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtProducto, gbc);
        JLabel lblCantidad = new JLabel("Inserte cantidad:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        add(lblCantidad, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtCantidad, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        //gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        //gbc.fill = GridBagConstraints.NONE;
        JButton btnAceptar7 = new JButton("Aceptar");
        add(btnAceptar7, gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridx = 0;
        gbc.gridy = 3;
        //gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        //gbc.fill = GridBagConstraints.NONE;
        this.add(btnVolver, gbc);
        revalidate();
        repaint(); 

        btnAceptar7.addActionListener(e -> {
                String idText = txtSucursal.getText();
                String idText2 = txtProducto.getText();
                String idText3 = txtCantidad.getText();
                if (idText.isEmpty() || idText2.isEmpty() || idText3.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe completar todos los datos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    int id = Integer.parseInt(idText);
                    int cantidad = Integer.parseInt(idText3);
                    Sucursal sucursal = serviceBuscarPorId(id);
                    Producto producto = serviceBuscarProducto(idText2);
                    if (sucursal != null && producto != null) {
                    	Stock stockNuevo = new Stock(id, producto.getNombre(), cantidad);
                    	serviceDarDeAltaProducto(stockNuevo);
                    	JOptionPane.showMessageDialog(this, "Producto dado de alta correctamente en la sucursal.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    	this.menuInicio();
                    } else {
                        JOptionPane.showMessageDialog(this, "No existe una sucursal o un producto con los datos proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El ID y la cantidad deben ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
    private void botonBuscarSucursalPorNombreYProducto2() {
    	removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JTextField txtSucursal = new JTextField();
        JTextField txtProducto = new JTextField();
        JTextField txtCantidad = new JTextField();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel lblMensaje = new JLabel("Inserte el nombre de la sucursal:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblMensaje, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtSucursal, gbc);
        JLabel lblProducto = new JLabel("Inserte el nombre del producto:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        add(lblProducto, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtProducto, gbc);
        JLabel lblCantidad = new JLabel("Inserte cantidad:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        add(lblCantidad, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtCantidad, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        //gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        //gbc.fill = GridBagConstraints.NONE;
        JButton btnAceptar8 = new JButton("Aceptar");
        add(btnAceptar8, gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridx = 0;
        gbc.gridy = 3;
        //gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        //gbc.fill = GridBagConstraints.NONE;
        this.add(btnVolver, gbc);
        revalidate();
        repaint(); 

        btnAceptar8.addActionListener(e -> {
                String idText = txtSucursal.getText();
                String idText2 = txtProducto.getText();
                String idText3 = txtCantidad.getText();
                if (idText.isEmpty() || idText2.isEmpty() || idText3.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe completar todos los datos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    int cantidad = Integer.parseInt(idText3);
                    Sucursal sucursal = serviceBuscarPorNombre(idText);
                    Producto producto = serviceBuscarProducto(idText2);
                    if (sucursal != null && producto != null) {
                    	Stock stockNuevo = new Stock(sucursal.getId(), producto.getNombre(), cantidad);
                    	serviceDarDeAltaProducto(stockNuevo);
                    	JOptionPane.showMessageDialog(this, "Producto dado de alta correctamente en la sucursal.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    	this.menuInicio();
                    } else {
                        JOptionPane.showMessageDialog(this, "No existe una sucursal o un producto con los datos proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "La cantidad deben ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
    private void mostrarDatosSucursal(Sucursal sucursal) {
    	removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblId = new JLabel("ID: " + sucursal.getId());
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(lblId, gbc);
        JLabel lblNombre = new JLabel("Nombre: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(lblNombre, gbc);
        JTextField txtNombre = new JTextField(sucursal.getNombre());
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(txtNombre, gbc);
        JLabel lblApertura = new JLabel("Horario de apertura (HH:mm:ss): ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(lblApertura, gbc);
        JTextField txtApertura = new JTextField(sucursal.getHorarioApertura().toString());
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(txtApertura, gbc);
        JLabel lblCierre = new JLabel("Horario de cierre (HH:mm:ss): ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(lblCierre, gbc);
        JTextField txtCierre = new JTextField(sucursal.getHorarioCierre().toString());
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(txtCierre, gbc);
        JLabel lblEstado = new JLabel("Estado: ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(lblEstado, gbc);

        JRadioButton rbOperativa = new JRadioButton("Operativa", sucursal.isEstado());
        JRadioButton rbNoOperativa = new JRadioButton("No operativa", !sucursal.isEstado());
        ButtonGroup estadoGroup = new ButtonGroup();
        estadoGroup.add(rbOperativa);
        estadoGroup.add(rbNoOperativa);
        JPanel estadoPanel = new JPanel();
        estadoPanel.add(rbOperativa);
        estadoPanel.add(rbNoOperativa);
        gbc.gridx = 1;
        gbc.gridy = 4;
        this.add(estadoPanel, gbc);

        JButton btnAceptar = new JButton("Aceptar");
        gbc.gridx = 1;
        gbc.gridy = 5;
        this.add(btnAceptar, gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(btnVolver, gbc);
        revalidate();
        repaint();
        

        btnAceptar.addActionListener(e -> {
            sucursal.setNombre(txtNombre.getText());
            try {
                Time apertura = Time.valueOf(txtApertura.getText());
                Time cierre = Time.valueOf(txtCierre.getText());
                sucursal.setHorarioApertura(apertura);
                sucursal.setHorarioCierre(cierre);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "El formato de horario debe ser HH:mm:ss.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            sucursal.setEstado(rbOperativa.isSelected());
            serviceEditarSucursal(sucursal);
            JOptionPane.showMessageDialog(this, "Sucursal modificada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.menuInicio();
        });

        
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
    public Producto serviceBuscarProducto(String nombre) {
    	return productoService.buscarPorNombre(nombre);
    }
    
    public void serviceDarDeBajaProducto(int id, String nombre) {
    	sucursalService.darDeBajaProducto(id, nombre);
    }
    
    public void serviceDarDeAltaProducto(Stock stock) {
    	sucursalService.darDeAltaProducto(stock);
    }
    
    private void botonBuscarSucursalPorNombreYProductoDarDeBajaStock() {
    	removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JTextField txtSucursal = new JTextField();
        JTextField txtProducto = new JTextField();
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel lblMensaje = new JLabel("Inserte el nombre de la sucursal:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblMensaje, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtSucursal, gbc);
        JLabel lblProducto = new JLabel("Inserte el nombre del producto:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        add(lblProducto, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtProducto, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        //gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        //gbc.fill = GridBagConstraints.NONE;
        JButton btnAceptar9 = new JButton("Aceptar");
        add(btnAceptar9, gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridx = 0;
        gbc.gridy = 2;
        //gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        //gbc.fill = GridBagConstraints.NONE;
        this.add(btnVolver, gbc);
        revalidate();
        repaint();
        
        btnAceptar9.addActionListener(e -> {
                String idText = txtSucursal.getText();
                String idText2 = txtProducto.getText();
                if (idText.isEmpty() || idText2.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe completar todos los datos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Sucursal sucursal = serviceBuscarPorNombre(idText);
                    Producto producto = serviceBuscarProducto(idText2);
                    if (sucursal != null && producto != null) {
                    	serviceDarDeBajaProducto(sucursal.getId(), producto.getNombre());
                    	JOptionPane.showMessageDialog(this, "Producto dado de baja correctamente de la sucursal.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    	this.menuInicio();
                    } else {
                        JOptionPane.showMessageDialog(this, "No existe una sucursal o un producto con los datos proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        	  
              btnVolver.addActionListener(e -> {
              	this.menuInicio();
              });
    }
    
    private void buscarSucursal() {
        removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JButton btnBuscarPorNombre2 = new JButton("Buscar sucursal por nombre");
        JButton btnBuscarPorId2 = new JButton("Buscar sucursal por Id");
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(13, 15, 13, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(setButtonSize(btnBuscarPorId2), gbc);
        gbc.gridy = 1;
        this.add(setButtonSize(btnBuscarPorNombre2), gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridy = 2;
        this.add(setButtonSize(btnVolver), gbc);
        revalidate();
        repaint();
        
        btnBuscarPorId2.addActionListener(e -> {
            txtBusqueda.setVisible(true);
            botonBuscarSucursalPorId();
        });
        btnBuscarPorNombre2.addActionListener(e -> {
            txtBusqueda.setVisible(true);
            botonBuscarSucursalPorNombre();
        });
        
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
    private void botonBuscarSucursalPorId() {
    	removeAll();
        JLabel lblMensaje = new JLabel("Inserte el ID de la sucursal:  ");
        JButton btnAceptar10 = new JButton("Aceptar");
        this.add(lblMensaje);
        this.add(txtBusqueda);
        this.add(btnAceptar10);
        revalidate();
        repaint();
        
        
        btnAceptar10.addActionListener(e -> {
                String idText = txtBusqueda.getText();
                if (idText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar el ID de la sucursal.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int id = Integer.parseInt(idText);
                    Sucursal sucursal = serviceBuscarPorId(id);
                    if (sucursal != null) {
                    	serviceVerStock(sucursal);
                    } else {
                        JOptionPane.showMessageDialog(this, "No existe una sucursal con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        	  JButton btnVolver = new JButton("Volver");
              this.add(btnVolver);
              btnVolver.addActionListener(e -> {
              	this.menuInicio();
              });
    }  
    
    private void botonBuscarSucursalPorNombre() {
        removeAll();
        JLabel lblMensaje = new JLabel("Inserte el nombre de la sucursal:  ");
        JButton btnAceptar11 = new JButton("Aceptar");
        this.add(lblMensaje);
        this.add(txtBusqueda);
        this.add(btnAceptar11);
        revalidate();
        repaint();
        
        	btnAceptar11.addActionListener(e -> {
        		String nombre = txtBusqueda.getText();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar el nombre de la sucursal.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Sucursal sucursal = serviceBuscarPorNombre(nombre);
                    if (sucursal != null) {
                    	serviceVerStock(sucursal);
                    } else {
                        JOptionPane.showMessageDialog(this, "No existe una sucursal con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        	  JButton btnVolver = new JButton("Volver");
              this.add(btnVolver);
              btnVolver.addActionListener(e -> {
              	this.menuInicio();
              });
    }
    
    private void serviceVerStock(Sucursal sucursal) {
    	List<Stock> stocks = sucursalService.verStock(sucursal);

        if (stocks.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La sucursal no tiene productos cargados.",
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        removeAll();
        setLayout(new BorderLayout());

        String[] columnNames = {"Id Sucursal", "Producto", "Cantidad"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Llenar la tabla con los datos de las sucursales
        for (Stock stock : stocks) {
            Object[] rowData = {stock.getSucursal(), stock.getProducto(),
            		stock.getCantidad()};
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
    
    private void mostrarDatosStock(Stock stock) {
    	removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel lblId = new JLabel("ID sucursal: " + stock.getSucursal());
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(lblId, gbc);
        JLabel lblNombre = new JLabel("Producto: " + stock.getProducto());
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(lblNombre, gbc);
        JLabel lblApertura = new JLabel("Cantidad: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(lblApertura, gbc);
        JTextField txtCapacidad = new JTextField(String.valueOf(stock.getCantidad()));
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(txtCapacidad, gbc);
        JButton btnAceptar = new JButton("Aceptar");
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(btnAceptar, gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(btnVolver, gbc);
        revalidate();
        repaint();
        
    	
        btnAceptar.addActionListener(e -> {
            try {
                int nuevaCantidad = Integer.parseInt(txtCapacidad.getText());
                if (nuevaCantidad >= 0) {
                    stock.setCantidad(nuevaCantidad);
                    serviceActualizarStock(stock);
                    JOptionPane.showMessageDialog(this, "Stock actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    this.menuInicio();
                    
                } else {
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero mayor o igual a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }

    public void serviceActualizarStock(Stock stock) {
    	sucursalService.actualizarStock(stock);
    }
    
    public Sucursal serviceBuscarPorId(int id) {
        return sucursalService.buscarPorId(id);
    }
    
    public Sucursal serviceBuscarPorNombre(String nombre) {
        return sucursalService.buscarPorNombre(nombre);
    }
    
    public void serviceEditarSucursal(Sucursal sucursal) {
    	sucursalService.editar(sucursal);
    }
    
    private void botonOrdenDeProvision() {
    	removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(11, 11, 11, 11);
        JLabel lblCompleteDatos = new JLabel("Complete los datos:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa 2 columnas
        this.add(lblCompleteDatos, gbc);
        JLabel lblId = new JLabel("Id");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Vuelve a ocupar 1 columna
        this.add(lblId, gbc);
        JTextField txtId = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtId, gbc);
        JLabel lblFecha = new JLabel("Fecha");
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(lblFecha, gbc);
        JTextField txtFecha = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtFecha, gbc);
        JLabel lblDestino = new JLabel("Sucursal destino (Id)");
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(lblDestino, gbc);
        JTextField txtDestino = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtDestino, gbc);
        JLabel lblTiempo = new JLabel("Tiempo maximo");
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(lblTiempo, gbc);
        JTextField txtTiempo = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtTiempo, gbc);

        // Botón "Aceptar"
        JButton btnAceptar = new JButton("Aceptar");
        gbc.gridx = 1;
        gbc.gridy = 5;
        //gbc.gridwidth = 2; // Ocupa 2 columnas
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        this.add(btnAceptar, gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridx = 0;
        gbc.gridy = 5;
        //gbc.gridwidth = 2; // Ocupa 2 columnas
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        this.add(btnVolver, gbc);
        revalidate();
        repaint();
        
        btnAceptar.addActionListener(e -> {
        	String idText = txtId.getText();
            String fechaText = txtFecha.getText();
            String destinoText = txtDestino.getText();
            String tiempoText = txtTiempo.getText();

            if (idText.isEmpty() || fechaText.isEmpty() || destinoText.isEmpty() || tiempoText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(idText);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilFecha = sdf.parse(fechaText);
                Date fecha = new Date(utilFecha.getTime());
                int sucursalDestino = Integer.parseInt(destinoText);
                Time tiempoMaximo = Time.valueOf(tiempoText);
                boolean pendiente = true;
                
                Sucursal sucursal = serviceBuscarPorId(sucursalDestino);
                if (sucursal != null) {
                	OrdenDeProvision orden = new OrdenDeProvision(id, fecha, sucursalDestino, tiempoMaximo, pendiente);
                    serviceOrdenDeProvision(orden);
                } else {
                    JOptionPane.showMessageDialog(this, "No existe una sucursal o un producto con los datos proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "El formato de horario debe ser HH:mm:ss.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (ParseException ex) {
                System.out.println("Error al analizar la fecha");
                ex.printStackTrace();
            }
            
        });
        
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    
    public void serviceOrdenDeProvision(OrdenDeProvision orden) {
    	try {
            // Llamar al servicio para dar de alta la sucursal
    		SucursalService sucursalService = SucursalService.getInstance();
            sucursalService.ordenDeProvision(orden);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Orden de provision dada de alta correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            botonProductosOrdenDeProvision(orden);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void botonProductosOrdenDeProvision(OrdenDeProvision orden) {
    	removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(6, 6, 6, 6);
        JLabel lblMensaje = new JLabel("Agregar a la orden de provision");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.add(lblMensaje, gbc);
        JLabel lblProducto = new JLabel("Producto");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        this.add(lblProducto, gbc);
        JTextField txtProducto = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtProducto, gbc);
        JLabel lblCantidad = new JLabel("Cantidad");
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(lblCantidad, gbc);
        JTextField txtCantidad = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtCantidad, gbc);
        JButton btnAgregarProducto = new JButton("Agregar otro producto");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        this.add(btnAgregarProducto, gbc);
        JButton btnFinalizarOrden = new JButton("Finalizar orden");
        gbc.gridx = 1;
        this.add(btnFinalizarOrden, gbc);

        btnAgregarProducto.addActionListener(e1 -> {
        	String nombreText = txtProducto.getText();
            String cantidadText = txtCantidad.getText();

            if (nombreText.isEmpty() || cantidadText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
            	int id = orden.getId();
                int cantidad = Integer.parseInt(cantidadText);
                
                Producto producto = serviceBuscarProducto(nombreText);
                
                if (producto != null) {
                	ProductosOrdenDeProvision productoOrden = new ProductosOrdenDeProvision(id, producto.getNombre(), cantidad);
                	serviceAgregarProductoOrden(productoOrden);
                } else {
                    JOptionPane.showMessageDialog(this, "No existe un producto con el nombre proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        	
        	botonProductosOrdenDeProvision(orden);
        });

        btnFinalizarOrden.addActionListener(e2 -> {
        	String nombreText = txtProducto.getText();
            String cantidadText = txtCantidad.getText();

            if (nombreText.isEmpty() || cantidadText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
            	int id = orden.getId();
                int cantidad = Integer.parseInt(cantidadText);
                
                Producto producto = serviceBuscarProducto(nombreText);
                
                if (producto != null) {
                	ProductosOrdenDeProvision productoOrden = new ProductosOrdenDeProvision(id, producto.getNombre(), cantidad);
                	serviceAgregarProductoOrden(productoOrden);
                	JOptionPane.showMessageDialog(this, "Datos guardados con exito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    this.menuInicio();               
                } else {
                    JOptionPane.showMessageDialog(this, "No existe un producto con el nombre proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        revalidate();
        repaint();
    }
    
    public void serviceAgregarProductoOrden(ProductosOrdenDeProvision productoOrden) {
    	try {
            // Llamar al servicio para dar de alta la sucursal
    		SucursalService sucursalService = SucursalService.getInstance();
            sucursalService.agregarProductoOrden(productoOrden);

            // Mostrar mensaje de éxito
            //JOptionPane.showMessageDialog(this, "Orden de provision dada de alta correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
}