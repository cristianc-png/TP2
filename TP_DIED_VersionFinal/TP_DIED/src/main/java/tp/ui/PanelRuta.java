package tp.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.Time;
import tp.services.RutaService;
import tp.modelo.*;
import java.awt.*;
import java.util.List;

public class PanelRuta extends JPanel{
	
	int controlador = 0;
    JButton btnDarAlta;
    JButton btnDarBaja;
    JButton btnEditar;
    JButton btnBuscarId;
    JButton btnBuscarOD;
    JButton btnBuscar;
    //JButton btnBuscar = new JButton("Buscar ruta");
    //JButton btnBuscarPorOD = new JButton("Buscar ruta por sucursal de origen y destino");
    //JButton btnAceptar = new JButton("Aceptar");
    JTextField txtBusqueda;
    
    RutaService rutaService;
    
    public void inicializar() {
        rutaService = RutaService.getInstance();

	    btnDarAlta = new JButton("Dar de alta");
	    btnDarBaja = new JButton("Dar de baja");
	    btnEditar = new JButton("Editar");
	    btnBuscar = new JButton("Buscar");
	    
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
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(setButtonSize(btnBuscar), gbc);
        gbc.gridy = 1;
        this.add(setButtonSize(btnEditar), gbc);
        App menu = new App();
        JButton btnVolver = new JButton("Volver");
        
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
        btnEditar.addActionListener(e -> {
        	botonEditar();
        });
        btnBuscar.addActionListener(e -> {
        	botonBuscar();
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
    	btnDarAlta = new JButton("Dar de alta");
 	    btnDarBaja = new JButton("Dar de baja");
 	    btnEditar = new JButton("Editar");
 	    btnBuscar = new JButton("Buscar");
 	    
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
         gbc.gridx = 1;
         gbc.gridy = 0;
         this.add(setButtonSize(btnBuscar), gbc);
         gbc.gridy = 1;
         gbc.gridy = 1;
         this.add(setButtonSize(btnEditar), gbc);
         App menu = new App();
         JButton btnVolver = new JButton("Volver");
         
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
         btnEditar.addActionListener(e -> {
         	botonEditar();
         });
         btnBuscar.addActionListener(e -> {
         	botonBuscar();
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

        // JLabel "Complete los datos:"
        JLabel lblCompleteDatos = new JLabel("Complete los datos:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.add(lblCompleteDatos, gbc);
        JLabel lblId = new JLabel("Id");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        this.add(lblId, gbc);
        JTextField txtId = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtId, gbc);
        JLabel lblIdOrigen = new JLabel("Sucursal origen (Id)");
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(lblIdOrigen, gbc);
        JTextField txtIdOrigen = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtIdOrigen, gbc);
        JLabel lblIdDestino = new JLabel("Sucursal destino (Id)");
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(lblIdDestino, gbc);
        JTextField txtIdDestino = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtIdDestino, gbc);
        JLabel lblTiempoTransito = new JLabel("Tiempo de transito");
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(lblTiempoTransito, gbc);
        JTextField txtTiempoTransito = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtTiempoTransito, gbc);
        JLabel lblCapacidadMaxima = new JLabel("Capacidad maxima");
        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(lblCapacidadMaxima, gbc);
        JTextField txtCapacidadMaxima = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtCapacidadMaxima, gbc);
        JLabel lblEstado = new JLabel("Estado");
        gbc.gridx = 0;
        gbc.gridy = 6;
        this.add(lblEstado, gbc);
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
        JButton btnAceptar = new JButton("Aceptar");
        gbc.gridx = 1;
        gbc.gridy = 7;
        //gbc.gridwidth = 2; // Ocupa 2 columnas
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        this.add(btnAceptar, gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridx = 0;
        gbc.gridy = 7;
        //gbc.gridwidth = 2; // Ocupa 2 columnas
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        this.add(btnVolver, gbc);
        revalidate();
        repaint();
        
        btnAceptar.addActionListener(e -> {
        	String idText = txtId.getText();
            String sucursalOrigen = txtIdOrigen.getText();
            String sucursalDestino = txtIdDestino.getText();
            String tiempoTransitoStr = txtTiempoTransito.getText();
            String capacidadStr = txtCapacidadMaxima.getText();

            if (idText.isEmpty() || sucursalOrigen.isEmpty() || sucursalDestino.isEmpty() || 
                    tiempoTransitoStr.isEmpty() || capacidadStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe completar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            try {
                int id = Integer.parseInt(idText);
                int origenId = Integer.parseInt(sucursalOrigen);
                int destinoId = Integer.parseInt(sucursalDestino);
                Time tiempoTransito = Time.valueOf(txtTiempoTransito.getText());
                double capacidad = Double.parseDouble(txtCapacidadMaxima.getText());
                //boolean estado = rbOperativa.isSelected();

                Ruta nuevaRuta = new Ruta(id, origenId, destinoId, tiempoTransito, capacidad, rbOperativa.isSelected());
                serviceDarDeAlta(nuevaRuta);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "El formato de horario debe ser HH:mm:ss.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            this.menuInicio();
        });
        
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    public void serviceDarDeAlta(Ruta ruta) {
    	try {
            // Llamar al servicio para dar de alta la sucursal
            RutaService rutaService = RutaService.getInstance();
            rutaService.darDeAlta(ruta);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Ruta dada de alta correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.menuInicio();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
   private void botonDarDeBaja() {
    	this.controlador = 1;
        buscarRuta();
    }
   
   private void botonEditar() {
	   this.controlador = 2;
	   buscarRuta();
   }
   
   private void buscarRuta() {
	   removeAll();
	   txtBusqueda.setVisible(true);
       JLabel lblMensaje = new JLabel("Inserte el ID de la ruta:  ");
       JButton btnAceptar1 = new JButton("Aceptar");
       this.add(lblMensaje);
       this.add(txtBusqueda);
       this.add(btnAceptar1);
       revalidate();
       repaint();
       if(controlador == 1) {
    	   btnAceptar1.addActionListener(e -> {
       		String idText = txtBusqueda.getText();
               if (idText.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "Debe ingresar el ID de la ruta.", "Error", JOptionPane.ERROR_MESSAGE);
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
       
       if(controlador == 2) {
    	   btnAceptar1.addActionListener(e -> {
               String idText = txtBusqueda.getText();
               if (idText.isEmpty()) {
                   JOptionPane.showMessageDialog(this, "Debe ingresar el ID de la ruta.", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }

               try {
                   int id = Integer.parseInt(idText);
                   Ruta ruta = serviceBuscarPorId(id);
                   if (ruta != null) {
                       mostrarDatosRuta(ruta);
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
   }
   
   public void serviceDarDeBajaPorId(int id) {
       try {
           // Llamar a la función darDeBaja de SucursalService
           RutaService rutaService = RutaService.getInstance();
           rutaService.darDeBaja(id);
           // Mostrar mensaje de éxito
           JOptionPane.showMessageDialog(this, "Ruta dada de baja correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
           this.menuInicio();
       } catch (IllegalArgumentException ex) {
           JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
       }
   }
   
   public Ruta serviceBuscarPorId(int id) {
       // Implementación para buscar la sucursal por ID en la base de datos
       // Usar el método adecuado del repositorio, por ejemplo:
       return rutaService.buscarPorId(id);
   }
   
   private void mostrarDatosRuta(Ruta ruta) {
   	removeAll();
       setLayout(new GridBagLayout());
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.fill = GridBagConstraints.BOTH;
       gbc.insets = new Insets(8, 8, 8, 8);

       JLabel lblId = new JLabel("ID: " + ruta.getId());
       gbc.gridx = 0;
       gbc.gridy = 0;
       this.add(lblId, gbc);
       JLabel lblOrigen = new JLabel("Sucursal origen(Id): ");
       gbc.gridx = 0;
       gbc.gridy = 1;
       this.add(lblOrigen, gbc);
       JTextField txtOrigen = new JTextField(String.valueOf(ruta.getOrigen()));
       gbc.gridx = 1;
       gbc.gridy = 1;
       this.add(txtOrigen, gbc);
       JLabel lblDestino = new JLabel("Sucursal destino(Id): ");
       gbc.gridx = 0;
       gbc.gridy = 2;
       this.add(lblDestino, gbc);
       JTextField txtDestino = new JTextField(String.valueOf(ruta.getDestino()));
       gbc.gridx = 1;
       gbc.gridy = 2;
       this.add(txtDestino, gbc);
       JLabel lblTiempoDeTransito = new JLabel("Tiempo de transito (HH:mm:ss): ");
       gbc.gridx = 0;
       gbc.gridy = 3;
       this.add(lblTiempoDeTransito, gbc);
       JTextField txtTiempoDeTransito = new JTextField(ruta.getTiempoDeTransito().toString());
       gbc.gridx = 1;
       gbc.gridy = 3;
       this.add(txtTiempoDeTransito, gbc);
       
       JLabel lblCapacidad = new JLabel("Capacidada: ");
       gbc.gridx = 0;
       gbc.gridy = 4;
       this.add(lblCapacidad, gbc);
       JTextField txtCapacidad = new JTextField(String.valueOf(ruta.getCapacidad()));
       gbc.gridx = 1;
       gbc.gridy = 4;
       this.add(txtCapacidad, gbc);
       
       
       JLabel lblEstado = new JLabel("Estado: ");
       gbc.gridx = 0;
       gbc.gridy = 5;
       this.add(lblEstado, gbc);

       JRadioButton rbOperativa = new JRadioButton("Operativa", ruta.isEstado());
       JRadioButton rbNoOperativa = new JRadioButton("No operativa", !ruta.isEstado());
       ButtonGroup estadoGroup = new ButtonGroup();
       estadoGroup.add(rbOperativa);
       estadoGroup.add(rbNoOperativa);
       JPanel estadoPanel = new JPanel();
       estadoPanel.add(rbOperativa);
       estadoPanel.add(rbNoOperativa);
       gbc.gridx = 1;
       gbc.gridy = 5;
       this.add(estadoPanel, gbc);

       JButton btnAceptar = new JButton("Aceptar");
       gbc.gridx = 1;
       gbc.gridy = 6;
       this.add(btnAceptar, gbc);
       JButton btnVolver = new JButton("Volver");
       gbc.gridx = 0;
       gbc.gridy = 6;
       this.add(btnVolver, gbc);

       btnAceptar.addActionListener(e -> {
    	   int origenId = Integer.parseInt(txtOrigen.getText());
    	   int destinoId = Integer.parseInt(txtDestino.getText());
           ruta.setOrigen(origenId);
           ruta.setDestino(destinoId);
           
           boolean existenSucursales = rutaService.verificarExistenciaSucursales(origenId, destinoId);
           if (!existenSucursales) {
               JOptionPane.showMessageDialog(this, "Una o ambas sucursales no existen en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
               return;
           }

           try {
               Time tiempoDeTransito = Time.valueOf(txtTiempoDeTransito.getText());
               ruta.setTiempoDeTransito(tiempoDeTransito);
           } catch (IllegalArgumentException ex) {
               JOptionPane.showMessageDialog(this, "El formato de horario debe ser HH:mm:ss.", "Error", JOptionPane.ERROR_MESSAGE);
               return;
           }
           double c = Double.parseDouble(txtCapacidad.getText());
           ruta.setCapacidad(c);
           
           ruta.setEstado(rbOperativa.isSelected());
           serviceEditarRuta(ruta);
           JOptionPane.showMessageDialog(this, "Ruta modificada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
           this.menuInicio();
       });
       btnVolver.addActionListener(e -> {
          	this.menuInicio();
          });

       revalidate();
       repaint();
   }
   
   public void serviceEditarRuta(Ruta ruta) {
	   rutaService.editar(ruta);
   }
    
    private void botonBuscar() {
        removeAll();
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton btnBuscarPorId = new JButton("Buscar por Id");
        JButton btnBuscarPorOrigen = new JButton("Buscar por sucursal origen");
        JButton btnBuscarPorDestino = new JButton("Buscar por sucursal destino");
        JButton btnBuscarPorTiempoDeTransito = new JButton("Buscar por tiempo de transito");
        JButton btnBuscarPorCapacidad = new JButton("Buscar por capacidad");
        JButton btnBuscarPorEstado = new JButton("Buscar por estado");
       
        gbc.fill = GridBagConstraints.BOTH; // Fill all available space
        gbc.insets = new Insets(7, 7, 7, 7); // Spaces between buttons
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(setButtonSize2(btnBuscarPorId), gbc);
        gbc.gridy = 1;
        this.add(setButtonSize2(btnBuscarPorOrigen), gbc);
        gbc.gridy = 2;
        this.add(setButtonSize2(btnBuscarPorDestino), gbc);
        gbc.gridy = 3;
        this.add(setButtonSize2(btnBuscarPorTiempoDeTransito), gbc);
        gbc.gridy = 4;
        this.add(setButtonSize2(btnBuscarPorCapacidad), gbc);
        gbc.gridy = 5;
        this.add(setButtonSize2(btnBuscarPorEstado), gbc);
        JButton btnVolver = new JButton("Volver");
        gbc.gridy = 6;
        this.add(setButtonSize2(btnVolver), gbc);
        this.revalidate();
        this.repaint();
        
        btnBuscarPorId.addActionListener(e -> {
            botonBuscarPorId();
        });
        btnBuscarPorOrigen.addActionListener(e -> {
            botonBuscarPorOrigen();
        });
        btnBuscarPorDestino.addActionListener(e -> {
        	botonBuscarPorDestino();
        });
        btnBuscarPorTiempoDeTransito.addActionListener(e -> {
            botonBuscarPorTiempoDeTransito();
        });
        btnBuscarPorCapacidad.addActionListener(e -> {
            botonBuscarPorCapacidad();
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

        JLabel lblMensaje = new JLabel("Inserte el ID de la ruta:  ");
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
    	RutaService rutaService = RutaService.getInstance();
        Ruta ruta = rutaService.buscarPorId(id);

        if (ruta != null) {
           mostrarRuta(ruta);
        } else {
            // Si la sucursal no fue encontrada, mostrar un mensaje de error
            JOptionPane.showMessageDialog(this, "No se encontró una ruta con el ID proporcionado.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void botonBuscarPorOrigen() {
    	removeAll();

        JLabel lblMensaje = new JLabel("Inserte el ID de la sucursal de origen de la ruta:  ");
        JTextField txtId = new JTextField(20);
        JButton btnAceptarId = new JButton("Aceptar");
        this.add(lblMensaje);
        this.add(txtId);
        this.add(btnAceptarId);
       
        revalidate();
        repaint();

        // Evento al presionar el botón "Aceptar"
        btnAceptarId.addActionListener(e -> {
            int origen = Integer.parseInt(txtId.getText());
            serviceBuscarOrigen(origen);
        });
        JButton btnVolver = new JButton("Volver");
        this.add(btnVolver);
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    private void serviceBuscarOrigen(int sucursalOrigen) {
        List<Ruta> rutas = rutaService.buscarPorSucursalOrigen(sucursalOrigen);
        if (rutas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron rutas con la sucursal de origen proporcionada.",
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        removeAll();
        setLayout(new BorderLayout());

        String[] columnNames = {"Id", "Sucursal origen", "Sucursal destino", "Tiempo de transito","Capacidad", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Llenar la tabla con los datos de las rutas
        for (Ruta ruta : rutas) {
            Object[] rowData = {ruta.getId(), ruta.getOrigen(), ruta.getDestino(), ruta.getTiempoDeTransito(),
                    ruta.getCapacidad(), (ruta.isEstado() ? "Operativa" : "No operativa")};
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
    
    private void botonBuscarPorDestino() {
    	removeAll();
        JLabel lblMensaje = new JLabel("Inserte el ID de la sucursal de destino de la ruta:  ");
        JTextField txtId = new JTextField(20);
        JButton btnAceptarId = new JButton("Aceptar");
        this.add(lblMensaje);
        this.add(txtId);
        this.add(btnAceptarId);
        revalidate();
        repaint();

        // Evento al presionar el botón "Aceptar"
        btnAceptarId.addActionListener(e -> {
            int destino = Integer.parseInt(txtId.getText());
            serviceBuscarDestino(destino);
        });
        JButton btnVolver = new JButton("Volver");
        this.add(btnVolver);
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    private void serviceBuscarDestino(int sucursalDestino) {
        List<Ruta> rutas = rutaService.buscarPorSucursalDestino(sucursalDestino);
        if (rutas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron rutas con la sucursal de destino proporcionada.",
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        removeAll();
        setLayout(new BorderLayout());

        String[] columnNames = {"Id", "Sucursal origen", "Sucursal destino", "Tiempo de transito","Capacidad", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Llenar la tabla con los datos de las rutas
        for (Ruta ruta : rutas) {
            Object[] rowData = {ruta.getId(), ruta.getOrigen(), ruta.getDestino(), ruta.getTiempoDeTransito(),
                    ruta.getCapacidad(), (ruta.isEstado() ? "Operativa" : "No operativa")};
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

    private void botonBuscarPorTiempoDeTransito(){
    	removeAll();
        JLabel lblMensaje = new JLabel("Inserte el tiempo de transito de la ruta:  ");
        JTextField txtHorario = new JTextField(20);
        JButton btnAceptarTiempo = new JButton("Aceptar");
        this.add(lblMensaje);
        this.add(txtHorario);
        this.add(btnAceptarTiempo);   
        revalidate();
        repaint();

        // Evento al presionar el botón "Aceptar"
        btnAceptarTiempo.addActionListener(e -> {
        	Time tiempoTransito = Time.valueOf(txtHorario.getText());
            serviceBuscarTiempoDeTransito(tiempoTransito);
        });
        JButton btnVolver = new JButton("Volver");
        this.add(btnVolver);
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    private void serviceBuscarTiempoDeTransito(Time transito) {
    	List<Ruta> rutas = rutaService.buscarPorTiemporDeTransito(transito);

        if (rutas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron rutas con el tiempo de transito proporcionado.",
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        removeAll();
        setLayout(new BorderLayout());

        String[] columnNames = {"Id", "Sucursal origen", "Sucursal destino", "Tiempo de transito","Capacidad", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Llenar la tabla con los datos de las sucursales
        for (Ruta ruta : rutas) {
            Object[] rowData = {ruta.getId(), ruta.getOrigen(), ruta.getDestino(), ruta.getTiempoDeTransito(),
                    ruta.getCapacidad(), (ruta.isEstado() ? "Operativa" : "No operativa")};
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
    
    private void botonBuscarPorCapacidad() {
    	removeAll();
        JLabel lblMensaje = new JLabel("Inserte la de la ruta:  ");
        JTextField txtCapacidad = new JTextField(20);
        JButton btnAceptarCapacidad = new JButton("Aceptar");
        this.add(lblMensaje);
        this.add(txtCapacidad);
        this.add(btnAceptarCapacidad);
        revalidate();
        repaint();

        // Evento al presionar el botón "Aceptar"
        btnAceptarCapacidad.addActionListener(e -> {
            double capacidad = Double.parseDouble(txtCapacidad.getText());
            serviceBuscarCapacidad(capacidad);
        });
        JButton btnVolver = new JButton("Volver");
        this.add(btnVolver);
        btnVolver.addActionListener(e -> {
        	this.menuInicio();
        });
    }
    private void serviceBuscarCapacidad(double capacidad) {
        List<Ruta> rutas = rutaService.buscarPorCapacidad(capacidad);
        if (rutas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron rutas con la capacidad proporcionada.",
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        removeAll();
        setLayout(new BorderLayout());

        String[] columnNames = {"Id", "Sucursal origen", "Sucursal destino", "Tiempo de transito","Capacidad", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Llenar la tabla con los datos de las rutas
        for (Ruta ruta : rutas) {
            Object[] rowData = {ruta.getId(), ruta.getOrigen(), ruta.getDestino(), ruta.getTiempoDeTransito(),
                    ruta.getCapacidad(), (ruta.isEstado() ? "Operativa" : "No operativa")};
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

        JLabel lblMensaje = new JLabel("Inserte el estado de la ruta:  ");
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
                return;
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
    	List<Ruta> rutas;
        
        // Verificar si el estado ingresado es "Operativa" o "No operativa"
        if (estado) {
            // Si es "Operativa", buscar las sucursales con estado "true" (Operativa)
        	rutas = rutaService.buscarPorEstado(true);
        } else {
            // Si es "No operativa", buscar las sucursales con estado "false" (No operativa)
        	rutas = rutaService.buscarPorEstado(false);
        }

        if (rutas.isEmpty()) {
            String mensaje = estado ? "No se encontraron rutas operativas." : "No se encontraron rutas no operativas.";
            JOptionPane.showMessageDialog(this, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        removeAll();
        setLayout(new BorderLayout());
        
        String[] columnNames = {"Id", "Sucursal origen", "Sucursal destino", "Tiempo de transito","Capacidad", "Estado"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        // Llenar la tabla con los datos de las sucursales
        for (Ruta ruta : rutas) {
            Object[] rowData = {ruta.getId(), ruta.getOrigen(), ruta.getDestino(), ruta.getTiempoDeTransito(),
                    ruta.getCapacidad(), (ruta.isEstado() ? "Operativa" : "No operativa")};
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
    
    private void mostrarRuta(Ruta ruta) {
       removeAll();
       setLayout(new GridBagLayout());
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.fill = GridBagConstraints.BOTH;
       gbc.insets = new Insets(13, 15, 13, 15);
       JLabel lblId = new JLabel("ID: " + ruta.getId());
       this.add(lblId, gbc);
       JLabel lblOrigen = new JLabel("Sucursal origen: " + ruta.getOrigen());
       gbc.gridy = 1;
       this.add(lblOrigen, gbc);
       JLabel lblDestino = new JLabel("Sucursal destino: " + ruta.getDestino());
       gbc.gridy = 2;
       this.add(lblDestino, gbc);
       JLabel lblTiemporDeTransito = new JLabel("Tiempo de transito: " + ruta.getTiempoDeTransito());
       gbc.gridy = 3;
       this.add(lblTiemporDeTransito, gbc);
       JLabel lblCapacidad = new JLabel("Capacidad: " + ruta.getCapacidad());
       gbc.gridy = 4;
       this.add(lblCapacidad, gbc);
       JLabel lblEstado = new JLabel("Estado: " + (ruta.isEstado() ? "Operativa" : "No operativa"));
       gbc.gridy = 5;
       this.add(lblEstado, gbc);
       JButton btnVolver = new JButton("Volver");
       gbc.gridy = 6;
       this.add(setButtonSize2(btnVolver), gbc);
       revalidate();
       repaint();
       btnVolver.addActionListener(e -> {
       	this.menuInicio();
       });
   }
    
    /*@SuppressWarnings("unused")
	private void botonBuscarPorOD() {
    	botonBuscarRutaPorOD();
    	btnAceptar.addActionListener(e -> {
            System.out.println("mostrar la ruta con sus datos");
        });
    }*/
    
    private void botonBuscarRutaPorOD() {
    	removeAll();
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(6, 6, 6, 6);
        // JLabel "Inserte el nombre de la sucursal de origen de la ruta:"
        JLabel lblNombreOrigen = new JLabel("Inserte el nombre de la sucursal de origen de la ruta:  ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Ocupa 2 columnas
        this.add(lblNombreOrigen, gbc);
        // JTextField para ingresar el nombre de la sucursal de origen
        JTextField txtNombreOrigen = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtNombreOrigen, gbc);
        // JLabel "Inserte el nombre de la sucursal de destino de la ruta:"
        JLabel lblNombreDestino = new JLabel("Inserte el nombre de la sucursal de destino de la ruta:  ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        //gbc.gridwidth = 2; // Ocupa 2 columnas
        this.add(lblNombreDestino, gbc);
        // JTextField para ingresar el nombre de la sucursal de destino
        JTextField txtNombreDestino = new JTextField(20);
        gbc.gridx = 1;
        this.add(txtNombreDestino, gbc);
        JButton btnAceptar = new JButton("Aceptar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Ocupa 2 columnas
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        this.add(btnAceptar, gbc);
        revalidate();
        repaint();
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
