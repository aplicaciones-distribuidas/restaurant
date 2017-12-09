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
import dto.MesaOcupacionView;
import excepciones.BaseDeDatosException;
import excepciones.ConexionException;
import excepciones.SucursalNoExisteException;

public class MesasOcupadasBuscar extends JInternalFrame {
	private static final long serialVersionUID = -52201933573009057L;
	private JLabel lblSucursal;
	private JTextField txtSucursal;
	private JButton btnBuscar, btnSalir;
	private MesasOcupadasBuscar aux;

	public MesasOcupadasBuscar() {
		super("Mesas Ocupadas", false, true, false, true);
		configurar();
		this.setVisible(true);
		this.pack();
		aux = this;
	}

	private void configurar() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 2));

		lblSucursal = new JLabel("Sucursal", JLabel.TRAILING);
		txtSucursal = new JTextField(10);
		lblSucursal.setLabelFor(txtSucursal);
		p.add(lblSucursal);
		p.add(txtSucursal);

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

				try {
					List<MesaOcupacionView> mesas = BusinessDelegate.getInstancia().mesasOcupadas(sucursal);
					if (mesas.size() == 0) {
						JOptionPane.showMessageDialog(aux, "No hay mesas ocupadas");
						return;
					}
					MesasOcupadasLista mesasOcupadasLista = new MesasOcupadasLista(mesas);
					aux.getParent().add(mesasOcupadasLista);
					aux.cerrar();
				} catch (BaseDeDatosException | SucursalNoExisteException | ConexionException ex) {
					JOptionPane.showMessageDialog(aux, ex.getMessage());
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
