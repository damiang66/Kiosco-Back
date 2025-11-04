package kiosco.kiosco.service;

import kiosco.kiosco.entidad.Venta;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface VentaService {

    Venta crearVenta(Venta venta);
    Venta actualizarVenta(Long id, Venta venta);
    void eliminarVenta(Long id);
    List<Venta> listarVentas();
    Optional<Venta> buscarPorId(Long id);
    //esta parte manejo el cierre de caja
    List<Venta> obtenerVentasAbiertas();

    void cerrarVentasAbiertas();
    //reportes
    List<Venta> obtenerVentasEntreFechas(Date inicio, Date fin);
}
