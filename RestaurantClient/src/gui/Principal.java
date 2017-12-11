package gui;

import business_delegate.BusinessDelegate;
import dto.SucursalView;
import excepciones.BaseDeDatosException;
import excepciones.ConexionException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Principal extends JFrame {
	private static final long serialVersionUID = -7189647070719732198L;
	private JMenuBar barraMenu;
	private JMenu mnMesas, mnSalir;
	private JMenuItem mnMesasDisponibles;
	private JMenuItem mnMesasOcupadas;
	private JMenuItem mnAbrirMesa;
	private JMenuItem mnReservarMesa;
	private JMenuItem mnSalirItem;
	private JDesktopPane desktop;
	private MesasDisponiblesBuscar mesasDisponibles;
	private MesasOcupadasBuscar mesasOcupadasBuscar;
	private MesaAbrir mesaAbrir;
	private MesaReservar mesaReservar;
	private String[] sucursales = {};

	public Principal() {
		traerDatos();
		configurar();
		asignarEventos();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setSize(600, 400);
		this.setResizable(false);
	}

	private void asignarEventos() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Principal aux = this;
		mnSalirItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnMesasDisponibles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mesasDisponibles = new MesasDisponiblesBuscar(aux.sucursales);
				desktop.add(mesasDisponibles);
			}
		});
		mnMesasOcupadas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mesasOcupadasBuscar = new MesasOcupadasBuscar(aux.sucursales);
				desktop.add(mesasOcupadasBuscar);
			}
		});
		mnAbrirMesa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mesaAbrir = new MesaAbrir(aux.sucursales);
				desktop.add(mesaAbrir);
			}
		});
		mnReservarMesa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mesaReservar = new MesaReservar(aux.sucursales);
				desktop.add(mesaReservar);
			}
		});
	}

	private void traerDatos() {
		try {
			List<SucursalView> sucursales = BusinessDelegate.getInstancia().getSucursales();
			List<String> sucursalesStrings = new ArrayList<>();
			for (SucursalView suc : sucursales) {
				sucursalesStrings.add(suc.getNombre());
			}
			this.sucursales = new String[sucursalesStrings.size()];
			sucursalesStrings.toArray(this.sucursales);
		} catch (ConexionException | BaseDeDatosException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void configurar() {
		this.setTitle("Restaurant");
		desktop = new JDesktopPane();
		this.setContentPane(desktop);

		barraMenu = new JMenuBar();
		mnMesas = new JMenu("Mesas");
		mnSalir = new JMenu("Salir");
		mnMesasDisponibles = new JMenuItem("Mesas Disponibles");
		mnMesasOcupadas = new JMenuItem("Mesas Ocupadas");
		mnAbrirMesa = new JMenuItem("Abrir Mesa");
		mnReservarMesa = new JMenuItem("Reservar Mesa");
		mnSalirItem = new JMenuItem("Salir");

		mnMesas.add(mnMesasDisponibles);
		mnMesas.add(mnMesasOcupadas);
		mnMesas.add(mnAbrirMesa);
		mnMesas.add(mnReservarMesa);
		mnSalir.add(mnSalirItem);
		barraMenu.add(mnMesas);
		barraMenu.add(mnSalir);

		this.setJMenuBar(barraMenu);
	}
}
