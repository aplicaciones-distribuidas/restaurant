package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.MesaOcupacionView;

public class MesasOcupadasLista extends JInternalFrame {
	private static final long serialVersionUID = -644489175035527974L;
	// private JInternalFrame aux;

	public MesasOcupadasLista(List<MesaOcupacionView> mesas) {
		super("Mesas Ocupadas", true, true, false, true);
		configurar(mesas);
		this.setVisible(true);
		this.pack();
		// aux = this;
	}

	private void configurar(List<MesaOcupacionView> mesas) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(mesas.size() + 1, 2));

		JLabel lblTituloNumero = new JLabel("Número");
		// JLabel lblTituloSectorSalon = new JLabel("Sector Salón");
		JLabel lblTituloAccion = new JLabel("Acción");
		p.add(lblTituloNumero);
		// p.add(lblTituloSectorSalon);
		p.add(lblTituloAccion);

		for (MesaOcupacionView mesa : mesas) {
			p.add(new JLabel(String.valueOf(mesa.getId())));
			JButton btnCerrar = new JButton("Cerrar");
			btnCerrar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(mesa.toString());
				}
			});
			p.add(btnCerrar);
		}

		p.setOpaque(true);

		this.setContentPane(p);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
