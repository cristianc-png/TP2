package tp.ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tp.modelo.Tema;
import tp.services.TemaService;

public class PanelTema extends JPanel{

	JLabel etiquetaTitulo;
	JLabel etiquetaTema;
	JTextField inputTema;
	JButton btnGuardar;
	
	TemaService temaServices;
	
	public void inicializar() {
		temaServices = TemaService.getInstance();
		
		etiquetaTitulo = new JLabel("PANEL TEMA");
		etiquetaTema = new JLabel("Tema:");
		inputTema = new JTextField(30); //para escribir algo
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(e->{
			Tema t1 = new Tema();
			t1.setTema(inputTema.getText());
			try {
				temaServices.guardar(t1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		this.add(etiquetaTitulo);
		this.add(etiquetaTema);
		this.add(inputTema);
		this.add(btnGuardar);
	}
}