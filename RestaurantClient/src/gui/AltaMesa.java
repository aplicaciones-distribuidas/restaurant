package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import business_delegate.BusinessDelegate;
import excepciones.MesaYaExisteException;
import excepciones.ConexionException;

public class AltaMesa extends JInternalFrame {
	private static final long serialVersionUID = -7885298908000683951L;
	private JLabel lblNumeroMesa;
	private JTextField txtNumeroMesa;
	private JButton btnAceptar, btnSalir;
	private JInternalFrame aux;

	public AltaMesa(String titulo, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
		super(titulo, resizable, closable, maximizable, iconifiable);
		configurar();
		this.setVisible(true);
		this.pack();
		aux = this;
	}

	private void configurar() {
		this.setLayout(new GridLayout(2, 2));
		lblNumeroMesa = new JLabel(" Nueva Mesa ");
		txtNumeroMesa = new JTextField();
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtNumeroMesa.getText() == null || txtNumeroMesa.getText().length() == 0) {
					JOptionPane.showMessageDialog(aux, "Debe ingresar un numero");
					return;
				}

				try {
					int numero = Integer.parseInt(txtNumeroMesa.getText());
					BusinessDelegate.getInstancia().agregarMesa(numero);
				} catch (MesaYaExisteException | ConexionException ex) {
					JOptionPane.showMessageDialog(aux, ex.getMessage());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(aux, "Debe ingresar un número");
				}
			}
		});

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					aux.setClosed(true);
				} catch (PropertyVetoException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(aux, "Error al salir");
				}
			}
		});

		this.add(lblNumeroMesa);
		this.add(txtNumeroMesa);
		this.add(btnAceptar);
		this.add(btnSalir);
	}
}
