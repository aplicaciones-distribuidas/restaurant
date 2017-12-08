package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business_delegate.BusinessDelegate;
import dto.MesaView;
import excepciones.BaseDeDatosException;
import excepciones.ConexionException;
import excepciones.SucursalNoExisteException;

public class MesasDisponiblesBuscar extends JInternalFrame {
	private static final long serialVersionUID = -7885298908000683951L;
	private JLabel lblSucursal;
	private JTextField txtSucursal;
	private JLabel lblCantidadPersonas;
	private JTextField txtCantidadPersonas;
	private JButton btnBuscar, btnSalir;
	private MesasDisponiblesBuscar aux;

	public MesasDisponiblesBuscar() {
		super("Buscar Mesas Disponibles", false, true, false, true);
		configurar();
		this.setVisible(true);
		this.pack();
		aux = this;
	}

	private void configurar() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 2));

		lblSucursal = new JLabel("Sucursal", JLabel.TRAILING);
		txtSucursal = new JTextField(10);
		lblSucursal.setLabelFor(txtSucursal);
		p.add(lblSucursal);
		p.add(txtSucursal);

		lblCantidadPersonas = new JLabel("Cantidad de Personas", JLabel.TRAILING);
		txtCantidadPersonas = new JTextField(10);
		lblCantidadPersonas.setLabelFor(txtCantidadPersonas);
		p.add(lblCantidadPersonas);
		p.add(txtCantidadPersonas);

		btnBuscar = new JButton("Buscar");
		btnSalir = new JButton("Salir");
		p.add(btnBuscar);
		p.add(btnSalir);

		p.setOpaque(true);

		this.setContentPane(p);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sucursal = txtSucursal.getText();
				if (sucursal == null || sucursal.length() == 0) {
					JOptionPane.showMessageDialog(aux, "Debe ingresar una sucursal");
					return;
				}

				String cantidadPersonas = txtCantidadPersonas.getText();
				if (cantidadPersonas == null || cantidadPersonas.length() == 0) {
					JOptionPane.showMessageDialog(aux, "Debe ingresar una cantidad de personas");
					return;
				}

				try {
					int cantPersonas = Integer.parseInt(cantidadPersonas);
					List<MesaView> mesas = BusinessDelegate.getInstancia().mesasDisponibles(sucursal, cantPersonas);
					if (mesas.size() == 0) {
						JOptionPane.showMessageDialog(aux, "No hay mesas disponibles");
						return;
					}
					MesasDisponiblesLista mesasDisponiblesLista = new MesasDisponiblesLista(mesas);
					aux.getParent().add(mesasDisponiblesLista);
					aux.cerrar();
				} catch (BaseDeDatosException | SucursalNoExisteException | ConexionException ex) {
					JOptionPane.showMessageDialog(aux, ex.getMessage());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(aux, "Cantidad de Personas debe ser un número");
				}
			}
		});

		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aux.cerrar();
			}
		});
	}

	private void cerrar() {
		try {
			this.setClosed(true);
		} catch (PropertyVetoException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al cerrar ventana");
		}
	}
}
