package kiosco.kiosco.service;

import kiosco.kiosco.entidad.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaService {
    Venta crearVenta(Venta venta);
    Venta actualizarVenta(Long id, Venta venta);
    void eliminarVenta(Long id);
    List<Venta> listarVentas();
    Optional<Venta> buscarPorId(Long id);

}
