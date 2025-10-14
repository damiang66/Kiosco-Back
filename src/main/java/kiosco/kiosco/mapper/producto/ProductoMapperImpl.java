package kiosco.kiosco.mapper.producto;


import kiosco.kiosco.entidad.producto.Producto;
import kiosco.kiosco.entidad.producto.dto.ProductoRequestDto;
import kiosco.kiosco.entidad.producto.dto.ProductoResponseDto;

public class ProductoMapperImpl implements ProductMapper{


    @Override
    public ProductoResponseDto toResponse(Producto producto) {
        ProductoResponseDto productoResponseDto = new ProductoResponseDto();

        productoResponseDto.setId(producto.getId());
        productoResponseDto.setCodigo(producto.getCodigo());
        productoResponseDto.setDescripcion(producto.getDescripcion());
        productoResponseDto.setCantidad(producto.getCantidad());
        productoResponseDto.setPrecioCompra(producto.getPrecioCompra());
        productoResponseDto.setPrecioVenta(producto.getPrecioVenta());

        return productoResponseDto;
    }

    @Override
    public Producto toEntity(ProductoRequestDto productoRequestDto) {
        return Producto.builder()
                .id(productoRequestDto.getId())
                .codigo(productoRequestDto.getCodigo())
                .descripcion(productoRequestDto.getDescripcion())
                .cantidad(productoRequestDto.getCantidad())
                .precioCompra(productoRequestDto.getPrecioCompra())
                .precioVenta(productoRequestDto.getPrecioVenta())
                .build();
    }
}