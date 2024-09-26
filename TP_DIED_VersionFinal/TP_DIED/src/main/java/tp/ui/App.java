package tp.ui;
//probando //
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class App extends JFrame {
	
	PanelSucursal panelSucursal;
	PanelRuta panelRuta;
	PanelProducto panelProducto;
	PanelOrdenDeProvision panelOrdenDeProvision;
	PanelPageRank panelPageRank;
	PanelFlujoMax panelFlujoMax;

	public void iniciar() {
		menu();
		this.setSize(750, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	public void volverAlMenuPrincipal() {
	    getContentPane().removeAll();
	    iniciar(); // Call the menu() method to open the new menu window
	}
	void menu() {
		// Creación de los botones
		JButton sucursalesButton = new JButton("Sucursales");
        JButton rutasButton = new JButton("Rutas");
        JButton productosButton = new JButton("Productos");
        JButton ordenesButton = new JButton("Ordenes de provision");
        JButton pageRankButton = new JButton("Page rank");
        JButton pageFlujoButton = new JButton("Flujo Maximo");
        // Creación del panel para los botones
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Espacios entre botones

        JLabel label = new JLabel("Ingrese la categoria que desee:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label);
        // Añadir botones al panel con GridBagConstraints para centrarlos
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(setButtonSize(sucursalesButton), gbc);

        gbc.gridy = 2;
        panel.add(setButtonSize(rutasButton), gbc);

        gbc.gridy = 3;
        panel.add(setButtonSize(productosButton), gbc);
        
        gbc.gridy = 4;
        panel.add(setButtonSize(ordenesButton), gbc);
        
        gbc.gridy = 5;
        panel.add(setButtonSize(pageRankButton), gbc);
        
        gbc.gridy = 6;
        panel.add(setButtonSize(pageFlujoButton), gbc);
        
        // Agregar el panel al contenido de la ventana
        getContentPane().add(panel, BorderLayout.CENTER);
        
        // Asignar acciones a los botones
        sucursalesButton.addActionListener(e -> {
            this.getPanelSucursal();
            getContentPane().removeAll();
            getContentPane().add(panelSucursal, BorderLayout.CENTER);
            revalidate();
            repaint();
            setTitle("Sucursales");
        });
        
        rutasButton.addActionListener(e -> {
        	this.getPanelRuta();
            getContentPane().removeAll();
            getContentPane().add(panelRuta, BorderLayout.CENTER);
            revalidate();
            repaint();
            setTitle("Rutas");
        });

        productosButton.addActionListener(e -> {
            this.getPanelProducto();
            getContentPane().removeAll();
            getContentPane().add(panelProducto, BorderLayout.CENTER);
            revalidate();
            repaint();
            setTitle("Productos");
        });
        
        ordenesButton.addActionListener(e -> {
            this.getPanelOrdenDeProvision();
            getContentPane().removeAll();
            getContentPane().add(panelOrdenDeProvision, BorderLayout.CENTER);
            revalidate();
            repaint();
            setTitle("Orden De Provisiones");
        });
        
        pageRankButton.addActionListener(e -> {
            this.getPanelPageRank();
            getContentPane().removeAll();
            getContentPane().add(panelPageRank, BorderLayout.CENTER);
            revalidate();
            repaint();
            setTitle("Page rank");
        });
        pageFlujoButton.addActionListener(e -> {
            this. getPanelFlujoMaximo();
            getContentPane().removeAll();
            getContentPane().add(panelFlujoMax, BorderLayout.CENTER);
            revalidate();
            repaint();
            setTitle("Flujo Maximo");
        });
	}
	void armarMenu(){
		removeAll();
		// Creación de los botones
		JButton sucursalesButton = new JButton("Sucursales");
        JButton rutasButton = new JButton("Rutas");
        JButton productosButton = new JButton("Productos");
        JButton ordenesButton = new JButton("Ordenes de provision");
        JButton pageRankButton = new JButton("Page rank");
        
        // Creación del panel para los botones
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espacios entre botones

        // Añadir botones al panel con GridBagConstraints para centrarlos
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(setButtonSize(sucursalesButton), gbc);

        gbc.gridy = 1;
        panel.add(setButtonSize(rutasButton), gbc);

        gbc.gridy = 2;
        panel.add(setButtonSize(productosButton), gbc);
        
        gbc.gridy = 4;
        panel.add(setButtonSize(ordenesButton), gbc);
        
        gbc.gridy = 5;
        panel.add(setButtonSize(pageRankButton), gbc);
        
        // Agregar el panel al contenido de la ventana
        getContentPane().add(panel, BorderLayout.CENTER);
        
        // Asignar acciones a los botones
        sucursalesButton.addActionListener(e -> {
            this.getPanelSucursal();
            getContentPane().removeAll();
            getContentPane().add(panelSucursal, BorderLayout.CENTER);
            revalidate();
            repaint();
            setTitle("Sucursales");
        });
        
        rutasButton.addActionListener(e -> {
            this.getPanelRuta();
            getContentPane().removeAll();
            getContentPane().add(panelRuta, BorderLayout.CENTER);
            revalidate();
            repaint();
            setTitle("Rutas");
        });

        productosButton.addActionListener(e -> {
            this.getPanelProducto();
            getContentPane().removeAll();
            getContentPane().add(panelProducto, BorderLayout.CENTER);
            revalidate();
            repaint();
            setTitle("Productos");
        });
        
        ordenesButton.addActionListener(e -> {
            this.getPanelOrdenDeProvision();
            getContentPane().removeAll();
            getContentPane().add(panelOrdenDeProvision, BorderLayout.CENTER);
            revalidate();
            repaint();
            setTitle("Orden De Provision");
        });
        pageRankButton.addActionListener(e -> {
            this.getPanelOrdenDeProvision();
            getContentPane().removeAll();
            getContentPane().add(panelPageRank, BorderLayout.CENTER);
            revalidate();
            repaint();
            setTitle("Page rank");
        });
        pageRankButton.addActionListener(e -> {
            this.getPanelOrdenDeProvision();
            getContentPane().removeAll();
            getContentPane().add(panelPageRank, BorderLayout.CENTER);
            revalidate();
            repaint();
            setTitle("Page rank");
        });
        revalidate();
        repaint();
    }
	
	private JButton setButtonSize(JButton button) {
        Dimension preferredSize = new Dimension(200, button.getPreferredSize().height);
        button.setMinimumSize(preferredSize);
        button.setPreferredSize(preferredSize);
        return button;
	}
	
	private JPanel getPanelSucursal() {
		if(this.panelSucursal==null) {
			this.panelSucursal = new PanelSucursal();
			this.panelSucursal.inicializar();
		}
		return panelSucursal;
	}
	
	private JPanel getPanelOrdenDeProvision() {
		if(this.panelOrdenDeProvision == null) {
			this.panelOrdenDeProvision = new PanelOrdenDeProvision();
			this.panelOrdenDeProvision.inicializar();
		}
		return panelOrdenDeProvision;
	}
	
	private JPanel getPanelPageRank() {
		if(this.panelPageRank == null) {
			this.panelPageRank = new PanelPageRank();
			this.panelPageRank.inicializar();
		}
		return panelOrdenDeProvision;
	}
	
	private JPanel getPanelRuta() {
		if(this.panelRuta==null) {
			this.panelRuta = new PanelRuta();
			this.panelRuta.inicializar();
		}
		return panelRuta;
	}
	
	private JPanel getPanelProducto() {
		if(this.panelProducto==null) {
			this.panelProducto = new PanelProducto();
			this.panelProducto.inicializar();
		}
		return panelProducto;
	}
	
	private JPanel getPanelFlujoMaximo() {
		if(this.panelFlujoMax==null) {
			this.panelFlujoMax = new PanelFlujoMax();
			this.panelFlujoMax.inicializar();
		}
		return panelFlujoMax;
	}
	public static void main(String[] args) {
        App app = new App();
		app.iniciar();
    }
}