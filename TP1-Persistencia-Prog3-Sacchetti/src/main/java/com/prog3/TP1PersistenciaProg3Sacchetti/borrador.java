package com.prog3.TP1PersistenciaProg3Sacchetti;

import com.prog3.TP1PersistenciaProg3Sacchetti.entidades.*;
import com.prog3.TP1PersistenciaProg3Sacchetti.repositorios.*;
import com.prog3.TP1PersistenciaProg3Sacchetti.util.FormaPago;
import com.prog3.TP1PersistenciaProg3Sacchetti.util.TipoEnvio;
import com.prog3.TP1PersistenciaProg3Sacchetti.util.TipoProducto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

public class borrador {

    @Bean
    CommandLineRunner init(UsuarioRepository usuarioRepository, PedidoRepository pedidoRepository, DetallePedidoRepository detallePedidoRepository, RubroRepository rubroRepository,
                           ClienteRepository clienteRepository, DomicilioRepository domicilioRepository, FacturaRepository facturaRepository) {
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


            // Crear instancia de Persona y agregar domicilios
            Usuario user = Usuario.builder()
                    .nombre("Bruno")
                    .password("1234")
                    .rol("Admin")
                    .build();

            user.agregarPedidoUsuario(pedido1);
            user.agregarPedidoUsuario(pedido2);


            // Guardar el objeto Persona en la base de datos

            usuarioRepository.save(user);

            // Recuperar el objeto Persona desde la base de datos

            Usuario userRecuperado = usuarioRepository.findById(user.getId()).orElse(null);


            if (userRecuperado != null) {
                System.out.println("Nombre: " + userRecuperado.getNombre());
                System.out.println("Password: " + userRecuperado.getPassword());
                System.out.println("Rol: " + userRecuperado.getRol());
                userRecuperado.mostrarPedidosUsuario();


            }

            System.out.println("-----------------PEDIDO // FACTURA ---------");

            Factura factura = Factura.builder()
                    .fecha("6/9/2023")
                    .numero(5487)
                    .descuento(8000.00)
                    .formaPago(FormaPago.TARJETA_CREDITO)
                    .total(10750)
                    .build();

            pedido1.setFactura(factura);

            pedidoRepository.save(pedido1);
            pedidoRepository.save(pedido2);

            Pedido pedidoRecuperado = pedidoRepository.findById(pedido1.getId()).orElse(null);

            if (pedidoRecuperado != null) {
                System.out.println("Fecha: " + pedidoRecuperado.getFecha());
                System.out.println("Estado: " + pedidoRecuperado.getEstado());
                System.out.println("Tipo de Envio: " + pedidoRecuperado.getTipoEnvio());
                pedidoRecuperado.mostrarFactura();


            }

            System.out.println("-----------------PEDIDO // DETALLE PEDIDO ---------");

            DetallePedido detallePedido1 = DetallePedido.builder()
                    .cantidad(5)
                    .subtotal(937.5)
                    .build();

            DetallePedido detallePedido2 = DetallePedido.builder()
                    .cantidad(5)
                    .subtotal(937.5)
                    .build();

            pedido1.agregarDetallePedido(detallePedido1);
            pedido1.agregarDetallePedido(detallePedido2);

            pedidoRepository.save(pedido1);

            Pedido pedidoRecuperado1 = pedidoRepository.findById(pedido1.getId()).orElse(null);

            if (pedidoRecuperado1 != null) {
                System.out.println("Fecha: " + pedidoRecuperado1.getFecha());
                System.out.println("Estado: " + pedidoRecuperado1.getEstado());
                System.out.println("Tipo de Envio: " + pedidoRecuperado1.getTipoEnvio());
                pedidoRecuperado1.mostrarDetallePedido();

            }

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

            detallePedido1.setProducto(producto1);
            detallePedido2.setProducto(producto2);

            detallePedidoRepository.save(detallePedido1);

            DetallePedido detallePedidoRecuperado = detallePedidoRepository.findById(detallePedido1.getId()).orElse(null);

            if (detallePedidoRecuperado != null) {

                pedidoRecuperado1.mostrarDetallePedido();
                detallePedidoRecuperado.mostrarProducto();

            }

            System.out.println("----------------- RUBRO // PRODUCTO ---------");

            Producto producto3 = Producto.builder()
                    .tipo(TipoProducto.MANUFACTURADO)
                    .stockActual(8)
                    .stockMinimo(1)
                    .precioVenta(400.0)
                    .build();

            Rubro rubro = Rubro.builder()
                    .denominacion("Cocina")
                    .build();

            rubro.agregarProducto(producto3);
            //rubro.agregarProducto(producto1);
            //rubro.agregarProducto(producto2);

            rubroRepository.save(rubro);

            Rubro rubroRecuperado = rubroRepository.findById(rubro.getId()).orElse(null);

            if (rubroRecuperado != null) {
                rubroRecuperado.mostrarRubro();
            }

            System.out.println("----------------- CLIENTE // PEDIDO ---------");

            Pedido pedidoCliente = Pedido.builder()
                    .fecha("7/9/2023")
                    .estado("preparado")
                    .horaEstimadaEntregada("12:00")
                    .tipoEnvio(TipoEnvio.RETIRA)
                    .total(158.63)
                    .build();

            Pedido pedidoCliente2 = Pedido.builder()
                    .fecha("2/2/1987")
                    .estado("entregado")
                    .horaEstimadaEntregada("13:00")
                    .tipoEnvio(TipoEnvio.DELIVERY)
                    .total(18750.63)
                    .build();

            Cliente cliente = Cliente.builder()
                    .nombre("Carlos")
                    .apellido("Perez")
                    .telefono("11254639")
                    .build();

            cliente.agregarPedidoCliente(pedidoCliente);
            cliente.agregarPedidoCliente(pedidoCliente2);

            clienteRepository.save(cliente);

            Cliente clienteRecuperado = clienteRepository.findById(cliente.getId()).orElse(null);

            if (clienteRecuperado != null) {
                clienteRecuperado.mostrarPedidosCliente();
            }

            System.out.println("----------------- DOMICILIO // CLIENTE y DOMICILIO // PEDIDO ---------");

            Cliente cliente1 = Cliente.builder()
                    .nombre("Pedro")
                    .apellido("Picapiedra")
                    .telefono("65847821")
                    .email("pedro@gmail.com")
                    .build();

            Domicilio domicilioCliente = Domicilio.builder()
                    .calle("Rodriguez")
                    .localidad("Guaymallen")
                    .numero("695")
                    .build();

            domicilioCliente.setCliente(cliente1);

            domicilioRepository.save(domicilioCliente);

            Domicilio domicilioRecuperado = domicilioRepository.findById(domicilioCliente.getId()).orElse(null);

            if (domicilioRecuperado != null) {
                domicilioRecuperado.mostrarPedidosDomicilio();
            }

            System.out.println("----------------- DOMICILIO // PEDIDO ---------");

            Pedido pedidoDomicilio = Pedido.builder()
                    .fecha("4/1/2010")
                    .estado("preparado")
                    .horaEstimadaEntregada("6:00")
                    .tipoEnvio(TipoEnvio.RETIRA)
                    .total(50.32)
                    .build();

            Pedido pedidoDomicilio2 = Pedido.builder()
                    .fecha("2/2/1987")
                    .estado("entregado")
                    .horaEstimadaEntregada("9:00")
                    .tipoEnvio(TipoEnvio.DELIVERY)
                    .total(789.20)
                    .build();

            Domicilio domicilioPedido = Domicilio.builder()
                    .calle("Colon")
                    .localidad("Mendoza")
                    .numero("100")
                    .build();

            domicilioPedido.agregarPedidoDomicilio(pedidoDomicilio);
            domicilioPedido.agregarPedidoDomicilio(pedidoDomicilio2);

            domicilioRepository.save(domicilioPedido);

            Domicilio domicilioPedidoRecuperado = domicilioRepository.findById(domicilioPedido.getId()).orElse(null);

            if (domicilioPedidoRecuperado != null) {
                domicilioPedidoRecuperado.mostrarPedidosDomicilio();
            }
        };
    }
}