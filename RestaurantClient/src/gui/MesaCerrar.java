package gui;

import business_delegate.BusinessDelegate;
import excepciones.BaseDeDatosException;
import excepciones.ConexionException;
import excepciones.FormaDePagoNoExisteException;
import excepciones.MesaOcupacionNoExisteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

public class MesaCerrar extends JInternalFrame {
	private static final long serialVersionUID = 3623782223140267378L;
	private JLabel lblTotal;
	private JLabel lblTotalValor;
	private JLabel lblFormaDePago;
	private JTextField txtFormaDePago;
	private JButton btnCerrar, btnSalir;
	private MesaCerrar aux;

	private Long idMesaOcupacion;

	public MesaCerrar(Long idMesaOcupacion) {
		super("Cerrar Mesa", false, true, false, true);

		this.idMesaOcupacion = idMesaOcupacion;

		configurar();
		this.setVisible(true);
		this.pack();
		aux = this;
	}

	private void configurar() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 2));

		lblTotal = new JLabel("Total", JLabel.TRAILING);
		lblTotalValor = new JLabel("$ X,XX"); // TODO: get value from server
		lblTotal.setLabelFor(lblTotalValor);
		p.add(lblTotal);
		p.add(lblTotalValor);

		lblFormaDePago = new JLabel("Forma de Pago", JLabel.TRAILING);
		txtFormaDePago = new JTextField("1", 10);
		lblFormaDePago.setLabelFor(txtFormaDePago);
		p.add(lblFormaDePago);
		p.add(txtFormaDePago);

		btnCerrar = new JButton("Cerrar");
		btnSalir = new JButton("Salir");
		p.add(btnCerrar);
		p.add(btnSalir);

		p.setOpaque(true);

		this.setContentPane(p);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnCerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String formaDePago = txtFormaDePago.getText();
				if (formaDePago == null || formaDePago.length() == 0) {
					JOptionPane.showMessageDialog(aux, "Debe ingresar una forma de pago");
					return;
				}

				Long idFormaDePago;
				try {
					idFormaDePago = Long.parseLong(formaDePago);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(aux, "Forma de pago debe ser un número");
					return;
				}

				try {
					BusinessDelegate.getInstancia().cerrarMesa(aux.idMesaOcupacion, idFormaDePago);
					JOptionPane.showMessageDialog(aux, "Mesa cerrada correctamente");
					aux.cerrar();
				} catch (BaseDeDatosException | MesaOcupacionNoExisteException | FormaDePagoNoExisteException | ConexionException ex) {
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
