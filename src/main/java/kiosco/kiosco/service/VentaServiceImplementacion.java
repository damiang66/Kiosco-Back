package kiosco.kiosco.service;

import kiosco.kiosco.entidad.Producto;
import kiosco.kiosco.entidad.Venta;
import kiosco.kiosco.entidad.VentaDetalle;
import kiosco.kiosco.repository.ProductoRepository;
import kiosco.kiosco.repository.VentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImplementacion implements VentaService{
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;

    public VentaServiceImplementacion(VentaRepository ventaRepository, ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
    }
    @Transactional
    @Override
    public Venta crearVenta(Venta venta) {
        double total = 0.0;

        for (VentaDetalle det : venta.getDetalles()) {
            Producto producto = productoRepository.findById(det.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (producto.getCantidad() < det.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para " + producto.getDescripcion());
            }

            producto.setCantidad(producto.getCantidad() - det.getCantidad());
            productoRepository.save(producto);

            det.setPrecioUnitario(producto.getPrecioVenta());
            det.setSubtotal(det.getPrecioUnitario() * det.getCantidad());
            det.setVenta(venta);
            total += det.getSubtotal();
        }

        venta.setTotal(total);
        return ventaRepository.save(venta);
    }

    @Transactional
    @Override
    public Venta actualizarVenta(Long id, Venta ventaNueva) {
        Venta ventaExistente = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        // Restaurar stock previo
        for (VentaDetalle det : ventaExistente.getDetalles()) {
            Producto producto = productoRepository.findById(det.getProducto().getId())
                    .orElseThrow();
            producto.setCantidad(producto.getCantidad() + det.getCantidad());
            productoRepository.save(producto);
        }

        ventaExistente.getDetalles().clear();

        ventaExistente.setFecha(ventaNueva.getFecha());
        double total = 0.0;

        for (VentaDetalle det : ventaNueva.getDetalles()) {
            Producto producto = productoRepository.findById(det.getProducto().getId())
                    .orElseThrow();

            if (producto.getCantidad() < det.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para " + producto.getDescripcion());
            }

            producto.setCantidad(producto.getCantidad() - det.getCantidad());
            productoRepository.save(producto);

            det.setPrecioUnitario(producto.getPrecioVenta());
            det.setSubtotal(det.getPrecioUnitario() * det.getCantidad());
            det.setVenta(ventaExistente);
            total += det.getSubtotal();
            ventaExistente.getDetalles().add(det);
        }

        ventaExistente.setTotal(total);
        return ventaRepository.save(ventaExistente);
    }

    @Transactional
    @Override
    public void eliminarVenta(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        // Restaurar stock
        for (VentaDetalle det : venta.getDetalles()) {
            Producto producto = productoRepository.findById(det.getProducto().getId()).orElseThrow();
            producto.setCantidad(producto.getCantidad() + det.getCantidad());
            productoRepository.save(producto);
        }

        ventaRepository.delete(venta);
    }

    @Override
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> buscarPorId(Long id) {
        return ventaRepository.findById(id);
    }
    // cierre de caja
    public List<Venta> obtenerVentasAbiertas() {
        return ventaRepository.findByCerradaFalse();
    }

    public Double obtenerTotalVentasAbiertas() {
        return ventaRepository.getTotalVentasAbiertas();
    }

    public void cerrarVentasAbiertas() {
        List<Venta> abiertas = ventaRepository.findByCerradaFalse();
        abiertas.forEach(v -> v.setCerrada(true));
        ventaRepository.saveAll(abiertas);
    }
    //reportes
    public List<Venta> obtenerVentasEntreFechas(Date inicio, Date fin) {
        return ventaRepository.findByFechaBetween(inicio, fin);
    }
}
