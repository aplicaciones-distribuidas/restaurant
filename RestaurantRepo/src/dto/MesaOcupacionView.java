package dto;

import java.io.Serializable;

public class MesaOcupacionView implements Serializable {
	private static final long serialVersionUID = 1980780449161077413L;
	private Long id;

	public MesaOcupacionView(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
