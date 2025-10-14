package kiosco.kiosco.mapper.producto;

import kiosco.kiosco.entidad.producto.Producto;
import kiosco.kiosco.entidad.producto.dto.ProductoRequestDto;
import kiosco.kiosco.entidad.producto.dto.ProductoResponseDto;

public interface ProductMapper {

    ProductoResponseDto toResponse(Producto producto);

    Producto toEntity(ProductoRequestDto productoRequestDto);
}
