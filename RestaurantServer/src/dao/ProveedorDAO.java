package dao;

import entities.ProveedorEntity;
import negocio.Proveedor;

public class ProveedorDAO {
	private static ProveedorDAO instancia;

	private ProveedorDAO() {
	}

	public static ProveedorDAO getInstancia() {
		if (instancia == null)
			instancia = new ProveedorDAO();
		return instancia;
	}

	public Proveedor toBusiness(ProveedorEntity entity) {
		return new Proveedor(entity.getNombre(), entity.getTelefono(), entity.getDireccion());
	}

	public ProveedorEntity toEntity(Proveedor business) {
		return new ProveedorEntity(business.getNombre(), business.getTelefono(), business.getDireccion());
	}

}
