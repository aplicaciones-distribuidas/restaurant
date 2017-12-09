package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;

import business_delegate.BusinessDelegate;
import dto.MesaOcupacionView;
import dto.MesaView;
import excepciones.BaseDeDatosException;
import excepciones.ConexionException;

import javax.swing.*;

public class MesasOcupadasLista extends JInternalFrame {
	private static final long serialVersionUID = -644489175035527974L;
	private MesasOcupadasLista aux;

	public MesasOcupadasLista(List<MesaOcupacionView> mesas) {
		super("Mesas Ocupadas", true, true, false, true);
		configurar(mesas);
		this.setVisible(true);
		this.pack();
		aux = this;
	}

	private void configurar(List<MesaOcupacionView> mesasOcupacion) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(mesasOcupacion.size() + 1, 6));

		JLabel lblTituloNumero = new JLabel("Mesas");
		p.add(lblTituloNumero);

		JLabel lblTituloSectorSalon = new JLabel("Sector Salón");
		p.add(lblTituloSectorSalon);

		JLabel lblTituloProductos = new JLabel("Productos");
		p.add(lblTituloProductos);

		JLabel lblTituloCerrar = new JLabel("Cerrar");
		p.add(lblTituloCerrar);

		JLabel lblTituloCobrar = new JLabel("Cobrar");
		p.add(lblTituloCobrar);

		JLabel lblTituloLiberar = new JLabel("Liberar");
		p.add(lblTituloLiberar);

		for (MesaOcupacionView mesaOcupacion : mesasOcupacion) {
			String mesasAsignadas = "";
			String sectorSalon = "";
			for (MesaView mesa : mesaOcupacion.getMesaItems()) {
				if (mesasAsignadas.length() > 0)
					mesasAsignadas += ", ";
				mesasAsignadas += mesa.getNumero();
				sectorSalon = mesa.getSectorSalon().getNombre();
			}
			p.add(new JLabel(mesasAsignadas));
			p.add(new JLabel(sectorSalon));

			JButton btnProductos = new JButton("Agregar");
			p.add(btnProductos);

			JButton btnCerrar = new JButton("Cerrar");
			p.add(btnCerrar);

			JButton btnCobrar = new JButton("Cobrar");
			p.add(btnCobrar);

			JButton btnLiberar = new JButton("Liberar");
			p.add(btnLiberar);

			btnProductos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MesaAgregarProducto mesaAgregarProducto = new MesaAgregarProducto(mesaOcupacion.getId());
					aux.getParent().add(mesaAgregarProducto);
				}
			});

			btnCerrar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						BusinessDelegate.getInstancia().cerrarMesa(mesaOcupacion.getId());
						JOptionPane.showMessageDialog(aux, "Mesa cerrada correctamente");
						aux.cerrar();
					} catch (BaseDeDatosException | ConexionException ex) {
						JOptionPane.showMessageDialog(aux, ex.getMessage());
					}
				}
			});

			btnCobrar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						BusinessDelegate.getInstancia().cobrarMesa(mesaOcupacion.getId());
						JOptionPane.showMessageDialog(aux, "Mesa cobrada correctamente");
						aux.cerrar();
					} catch (BaseDeDatosException | ConexionException ex) {
						JOptionPane.showMessageDialog(aux, ex.getMessage());
					}
				}
			});

			btnLiberar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						BusinessDelegate.getInstancia().liberarMesa(mesaOcupacion.getId());
						JOptionPane.showMessageDialog(aux, "Mesa liberada correctamente");
						aux.cerrar();
					} catch (BaseDeDatosException | ConexionException ex) {
						JOptionPane.showMessageDialog(aux, ex.getMessage());
					}
				}
			});
		}

		p.setOpaque(true);

		this.setContentPane(p);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
