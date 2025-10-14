package kiosco.kiosco.entidad.producto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponseDto {
    private Long id;
    private Long codigo;
    private String descripcion;
    private Long cantidad;
    private Double precioCompra;
    private Double precioVenta;
}
