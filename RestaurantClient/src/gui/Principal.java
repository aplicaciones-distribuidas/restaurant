package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Principal extends JFrame {
	private static final long serialVersionUID = -7189647070719732198L;
	private JMenuBar barraMenu;
	private JMenu mnAlta, mnSalir;
	/* items del menu Mesa */
	private JMenuItem mnMesa;
	/* items del menu Salir */
	private JMenuItem mnSalirItem;
	private JDesktopPane desktop;
	private AltaMesa agregarMesa;

	public Principal() {
		configurar();
		asignarEventos();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setSize(600, 400);
		this.setResizable(false);
	}

	private void asignarEventos() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		mnSalirItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnMesa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarMesa = new AltaMesa("Alta Mesa", false, true, false, true);
				desktop.add(agregarMesa);
			}
		});
	}

	private void configurar() {
		this.setTitle("Inscripciones");
		desktop = new JDesktopPane();
		this.setContentPane(desktop);

		barraMenu = new JMenuBar();
		mnAlta = new JMenu("Altas");
		mnSalir = new JMenu("Salir");
		mnMesa = new JMenuItem("Mesa");
		mnSalirItem = new JMenuItem("Salir");

		mnAlta.add(mnMesa);
		mnSalir.add(mnSalirItem);
		barraMenu.add(mnAlta);
		barraMenu.add(mnSalir);

		this.setJMenuBar(barraMenu);
	}
}
