package kiosco.kiosco.service;

import kiosco.kiosco.entidad.Producto;
import kiosco.kiosco.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImplementacion implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public List<Producto> findByCodigo(Long codigo) {
        return productoRepository.findByCodigo(codigo);
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void delete(Long id) {
            productoRepository.deleteById(id);
    }
}
