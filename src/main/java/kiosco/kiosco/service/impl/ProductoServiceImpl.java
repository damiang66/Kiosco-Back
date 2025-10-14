package kiosco.kiosco.service.impl;

import kiosco.kiosco.entidad.producto.Producto;
import kiosco.kiosco.entidad.producto.dto.ProductoRequestDto;
import kiosco.kiosco.entidad.producto.dto.ProductoResponseDto;
import kiosco.kiosco.exception.IllegalArgumentException;
import kiosco.kiosco.exception.ResourceNotFoundException;
import kiosco.kiosco.mapper.producto.ProductMapper;
import kiosco.kiosco.repository.ProductoRepository;
import kiosco.kiosco.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductoResponseDto> findAll() {
        List<Producto> productoList=productoRepository.findAll();
        if (productoList.isEmpty()){
            throw new ResourceNotFoundException("LA LISTA ESTA VACIA");
        }
        return productoRepository.findAll().stream().map(productMapper::toResponse).toList();
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
    public ProductoResponseDto update(ProductoRequestDto productoRequestDto, Long id) {
        Producto prodcutoDB=productoRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Error en la busqueda de producto"));

        Producto producto=productMapper.toEntity(productoRequestDto);
        prodcutoDB.setCodigo(producto.getCodigo());
        prodcutoDB.setCantidad(producto.getCantidad());
        prodcutoDB.setDescripcion(producto.getDescripcion());
        prodcutoDB.setPrecioCompra(producto.getPrecioCompra());
        prodcutoDB.setPrecioVenta(producto.getPrecioVenta());
        productoRepository.save(prodcutoDB);

        return productMapper.toResponse(prodcutoDB);
    }

    @Override
    public void delete(Long id) {
            productoRepository.deleteById(id);
    }
}
