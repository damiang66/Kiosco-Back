package kiosco.kiosco.service;

import kiosco.kiosco.entidad.producto.Producto;
import kiosco.kiosco.entidad.producto.dto.ProductoRequestDto;
import kiosco.kiosco.entidad.producto.dto.ProductoResponseDto;

import java.util.List;
import java.util.Optional;


public interface ProductoService {
  List<ProductoResponseDto> findAll();
  Optional<Producto>findById(Long id);
  List<Producto>findByCodigo (Long codigo);
  Producto save(Producto producto);
  ProductoResponseDto update(ProductoRequestDto productoRequestDto, Long id);
  void delete(Long id);
}
