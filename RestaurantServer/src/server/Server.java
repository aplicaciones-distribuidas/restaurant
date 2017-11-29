package server;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entities.AreaEntity;
import entities.BarraEntity;
import entities.CafeteriaEntity;
import entities.CajaEntity;
import entities.CartaEntity;
import entities.CocinaEntity;
import entities.ComandaEntity;
import entities.ComisionEntity;
import entities.DepositoAreaEntity;
import entities.DepositoEntity;
import entities.DepositoGeneralEntity;
import entities.DepositoRestaurantEntity;
import entities.DirectoEntity;
import entities.EmpleadoEntity;
import entities.FacturaEntity;
import entities.FormaPagoEntity;
import entities.InsumoEntity;
import entities.InsumoProductoEntity;
import entities.ItemCartaEntity;
import entities.ItemFacturaEntity;
import entities.ItemPedidoEntity;
import entities.MesaCompuestaEntity;
import entities.MesaEntity;
import entities.MesaSimpleEntity;
import entities.PedidoEntity;
import entities.PedidoReposicionEntity;
import entities.ProductoEntity;
import entities.ProveedorEntity;
import entities.ReservaEntity;
import entities.RolEntity;
import entities.SectorSalonEntity;
import entities.SemiElaboradoEntity;
import entities.SucursalEntity;
import entities.TareaEntity;

public class Server {
	
	public static void main(String[] args) {
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(AreaEntity.class);
		config.addAnnotatedClass(BarraEntity.class);
		config.addAnnotatedClass(CafeteriaEntity.class);
		config.addAnnotatedClass(CajaEntity.class);
		config.addAnnotatedClass(CartaEntity.class);
		config.addAnnotatedClass(ItemCartaEntity.class);
		config.addAnnotatedClass(ProductoEntity.class);
		config.addAnnotatedClass(InsumoProductoEntity.class);
		config.addAnnotatedClass(InsumoEntity.class);
		config.addAnnotatedClass(ProveedorEntity.class);
		config.addAnnotatedClass(CocinaEntity.class);
		config.addAnnotatedClass(ComandaEntity.class);
		config.addAnnotatedClass(ItemPedidoEntity.class);
		config.addAnnotatedClass(ComisionEntity.class);
		config.addAnnotatedClass(DepositoAreaEntity.class);
		config.addAnnotatedClass(DepositoEntity.class);
		config.addAnnotatedClass(DepositoGeneralEntity.class);
		config.addAnnotatedClass(EmpleadoEntity.class);
		config.addAnnotatedClass(PedidoReposicionEntity.class);
		config.addAnnotatedClass(RolEntity.class);
		config.addAnnotatedClass(DepositoRestaurantEntity.class);
		config.addAnnotatedClass(DirectoEntity.class);
		config.addAnnotatedClass(FacturaEntity.class);
		config.addAnnotatedClass(FormaPagoEntity.class);
		config.addAnnotatedClass(ItemFacturaEntity.class);
		config.addAnnotatedClass(MesaCompuestaEntity.class);
		config.addAnnotatedClass(SectorSalonEntity.class);
		config.addAnnotatedClass(MesaEntity.class);
		config.addAnnotatedClass(MesaSimpleEntity.class);
		config.addAnnotatedClass(PedidoEntity.class);
		config.addAnnotatedClass(ReservaEntity.class);
		config.addAnnotatedClass(SemiElaboradoEntity.class);
		config.addAnnotatedClass(SucursalEntity.class);
		config.addAnnotatedClass(TareaEntity.class);
		SessionFactory session = config.buildSessionFactory();
	}
	
	
/*	MesasTDA mesasRemoteObject;

	public Server() {
		start();
	}

	private void start() {
		try {
			//LocateRegistry.createRegistry(1099);
			//mesasRemoteObject = new MesasManager();
			//Naming.rebind("//localhost/mesas", mesasRemoteObject);
			//System.out.println("Binded to //localhost/mesas");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
