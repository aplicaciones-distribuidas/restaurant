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
	private JMenu mnMesas, mnSalir;
	private JMenuItem mnMesasDisponibles;
	private JMenuItem mnSalirItem;
	private JDesktopPane desktop;
	private MesasDisponiblesBuscar mesasDisponibles;

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
		mnMesasDisponibles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mesasDisponibles = new MesasDisponiblesBuscar();
				desktop.add(mesasDisponibles);
			}
		});
	}

	private void configurar() {
		this.setTitle("Restaurant");
		desktop = new JDesktopPane();
		this.setContentPane(desktop);

		barraMenu = new JMenuBar();
		mnMesas = new JMenu("Mesas");
		mnSalir = new JMenu("Salir");
		mnMesasDisponibles = new JMenuItem("Buscar Mesas Disponibles");
		mnSalirItem = new JMenuItem("Salir");

		mnMesas.add(mnMesasDisponibles);
		mnSalir.add(mnSalirItem);
		barraMenu.add(mnMesas);
		barraMenu.add(mnSalir);

		this.setJMenuBar(barraMenu);
	}
}
