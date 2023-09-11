package com.prog3.TP1PersistenciaProg3Sacchetti;

import com.prog3.TP1PersistenciaProg3Sacchetti.entidades.*;
import com.prog3.TP1PersistenciaProg3Sacchetti.repositorios.*;
import com.prog3.TP1PersistenciaProg3Sacchetti.util.FormaPago;
import com.prog3.TP1PersistenciaProg3Sacchetti.util.TipoEnvio;
import com.prog3.TP1PersistenciaProg3Sacchetti.util.TipoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tp1PersistenciaProg3SacchettiApplication {
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	DetallePedidoRepository detallePedidoRepository;
	@Autowired
	RubroRepository rubroRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	DomicilioRepository domicilioRepository;
	@Autowired
	FacturaRepository facturaRepository;

	@Autowired
	ProductoRepository productoRepository;
	public static void main(String[] args) {

		SpringApplication.run(Tp1PersistenciaProg3SacchettiApplication.class, args);
		System.out.println("ESTOY FUNCIONANDO");
	}

	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository, PedidoRepository pedidoRepository, DetallePedidoRepository detallePedidoRepository, RubroRepository rubroRepository,
						   ClienteRepository clienteRepository, DomicilioRepository domicilioRepository, FacturaRepository facturaRepository, ProductoRepository productoRepository) {
		return args -> {
			System.out.println("-----------------USUARIO // PEDIDO ---------");

/*El método builder() se genera automáticamente por Lombok
y te permite crear una instancia de Persona.Builder.
Luego, puedes encadenar llamadas a los métodos
setters generados automáticamente para establecer los
valores de los atributos de la clase.
Finalmente, build() crea la instancia
 de la clase Persona con los valores proporcionados.

 */

			Pedido pedido1 = Pedido.builder()
					.fecha("6/9/2023")
					.estado("preparado")
					.horaEstimadaEntregada("17:45")
					.tipoEnvio(TipoEnvio.DELIVERY)
					.total(18750.63)
					.build();

			Pedido pedido2 = Pedido.builder()
					.fecha("15/2/2022")
					.estado("entregado")
					.horaEstimadaEntregada("17:45")
					.tipoEnvio(TipoEnvio.RETIRA)
					.total(1589.63)
					.build();

			pedidoRepository.save(pedido1);
			pedidoRepository.save(pedido2);

			// Crear instancia de Persona y agregar domicilios
			Usuario user = Usuario.builder()
					.nombre("Bruno")
					.password("1234")
					.rol("Admin")
					.build();

			user.agregarPedidoUsuario(pedido1);
			user.agregarPedidoUsuario(pedido2);

			usuarioRepository.save(user);

			System.out.println("-----------------PEDIDO // FACTURA ---------");

			Factura factura = Factura.builder()
					.fecha("6/9/2023")
					.numero(5487)
					.descuento(8000.00)
					.formaPago(FormaPago.TARJETA_CREDITO)
					.total(10750)
					.build();

			facturaRepository.save(factura);

			pedido1.setFactura(factura);

			pedidoRepository.save(pedido1);
			pedidoRepository.save(pedido2);

			System.out.println("-----------------PEDIDO // DETALLE PEDIDO ---------");

			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(5)
					.subtotal(937.5)
					.build();

			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(5)
					.subtotal(937.5)
					.build();

			detallePedidoRepository.save(detallePedido1);
			detallePedidoRepository.save(detallePedido2);

			pedido1.agregarDetallePedido(detallePedido1);
			pedido1.agregarDetallePedido(detallePedido2);

			pedidoRepository.save(pedido1);

			System.out.println("-----------------DETALLE PEDIDO // PRODUCTO ---------");

			Producto producto1 = Producto.builder()
					.tipo(TipoProducto.MANUFACTURADO)
					.stockActual(5)
					.stockMinimo(2)
					.precioVenta(1500.0)
					.build();

			Producto producto2 = Producto.builder()
					.tipo(TipoProducto.INSUMO)
					.stockActual(15)
					.stockMinimo(7)
					.precioVenta(6900.0)
					.build();

			productoRepository.save(producto1);
			productoRepository.save(producto2);

			detallePedido1.setProducto(producto1);
			detallePedido2.setProducto(producto2);

			detallePedidoRepository.save(detallePedido1);

			System.out.println("----------------- RUBRO // PRODUCTO ---------");

			Rubro rubro = Rubro.builder()
					.denominacion("Cocina")
					.build();

			rubro.agregarProducto(producto1);

			rubroRepository.save(rubro);

			System.out.println("----------------- CLIENTE // PEDIDO ---------");

			Cliente cliente = Cliente.builder()
					.nombre("Carlos")
					.apellido("Perez")
					.telefono("11254639")
					.build();

			cliente.agregarPedidoCliente(pedido1);
			cliente.agregarPedidoCliente(pedido2);

			clienteRepository.save(cliente);

			System.out.println("----------------- DOMICILIO // CLIENTE y DOMICILIO // PEDIDO ---------");

			Domicilio domicilioCliente = Domicilio.builder()
					.calle("Rodriguez")
					.localidad("Guaymallen")
					.numero("695")
					.build();

			domicilioCliente.setCliente(cliente);

			domicilioRepository.save(domicilioCliente);

			System.out.println("----------------- DOMICILIO // PEDIDO ---------");

			domicilioCliente.agregarPedidoDomicilio(pedido1);
			domicilioCliente.agregarPedidoDomicilio(pedido2);

			domicilioRepository.save(domicilioCliente);

		};


	}
}

