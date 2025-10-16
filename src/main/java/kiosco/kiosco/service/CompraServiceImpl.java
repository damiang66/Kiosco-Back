package kiosco.kiosco.service;

import kiosco.kiosco.entidad.*;
import kiosco.kiosco.repository.CompraRepository;
import kiosco.kiosco.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class CompraServiceImpl implements CompraService{
    private final CompraRepository compraRepository;
    private final ProductoRepository productoRepository;

    @Override
    public List<Compra> finfAll() {
        return compraRepository.findAll();
    }

    @Override
    public Compra save(Compra compra) {
        for (CompraDetalle detalle : compra.getDetalles()) {
            detalle.setCompra(compra);
            Producto producto = detalle.getProducto();
            producto.setCantidad(producto.getCantidad() + detalle.getCantidad());
            productoRepository.save(producto);
        }
        return compraRepository.save(compra);
    }

    @Override
    public void delete(Long id) {
        compraRepository.deleteById(id);
    }

    @Override
    public Compra findById(Long id) {
        return compraRepository.findById(id).orElse(null);
    }

    @Override
    public Compra actualizarCompra(Long id, Compra compra) {
        Compra ventaExistente = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        // Restaurar stock previo
        for (CompraDetalle det : ventaExistente.getDetalles()) {
            Producto producto = productoRepository.findById(det.getProducto().getId())
                    .orElseThrow();
            producto.setCantidad(producto.getCantidad() + det.getCantidad());
            productoRepository.save(producto);
        }

        ventaExistente.getDetalles().clear();

        ventaExistente.setFecha(compra.getFecha());
        double total = 0.0;

        for (CompraDetalle det : compra.getDetalles()) {
            Producto producto = productoRepository.findById(det.getProducto().getId())
                    .orElseThrow();

            if (producto.getCantidad() < det.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para " + producto.getDescripcion());
            }

            producto.setCantidad(producto.getCantidad() - det.getCantidad());
            productoRepository.save(producto);

            det.setPrecioUnitario(producto.getPrecioVenta());
            det.setSubtotal(det.getPrecioUnitario() * det.getCantidad());
            det.setCompra(ventaExistente);
            total += det.getSubtotal();
            ventaExistente.getDetalles().add(det);
        }

        ventaExistente.setTotal(total);
        return compraRepository.save(ventaExistente);
    }
}
