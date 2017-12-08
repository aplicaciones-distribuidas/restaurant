package dto;

import java.io.Serializable;
import java.util.List;

public class MesaOcupacionView implements Serializable {
	private static final long serialVersionUID = -8294287495190172666L;
	private Long id;
	private List<MesaView> mesaItems;

	public MesaOcupacionView(Long id, List<MesaView> mesaItems) {
		this.id = id;
		this.mesaItems = mesaItems;
	}

	public Long getId() {
		return id;
	}

	public List<MesaView> getMesaItems() {
		return mesaItems;
	}

}
