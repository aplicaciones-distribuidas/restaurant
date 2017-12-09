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
import dto.MesaView;

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

	private void configurar(List<MesaOcupacionView> mesasOcupacion) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(mesasOcupacion.size() + 1, 4));

		JLabel lblTituloNumero = new JLabel("Mesas");
		p.add(lblTituloNumero);

		JLabel lblTituloSectorSalon = new JLabel("Sector Salón");
		p.add(lblTituloSectorSalon);

		JLabel lblTituloCerrar = new JLabel("Cerrar");
		p.add(lblTituloCerrar);

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

			JButton btnCerrar = new JButton("Cerrar");
			p.add(btnCerrar);

			JButton btnLiberar = new JButton("Liberar");
			p.add(btnLiberar);

			btnCerrar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(mesaOcupacion.toString());
				}
			});

			btnLiberar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(mesaOcupacion.toString());
				}
			});
		}

		p.setOpaque(true);

		this.setContentPane(p);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
