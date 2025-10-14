package kiosco.kiosco.entidad.producto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoRequestDto {

    @NotNull(message = "El ID no puede estar vacio")
    private Long id;

    @NotNull(message = "El CODIGO no puede estar vacio")
    private Long codigo;

    @NotBlank(message = "La DESCRIPCION no puede estar vacia")
    private String descripcion;

    @NotNull(message = "La CANTIDAD no puede estar vacio")
    private Long cantidad;

    @NotNull(message = "El PRECIO COMPRA no puede estar vacio")
    private Double precioCompra;

    @NotNull(message = "El PRECIO VENTA no puede estar vacio")
    private Double precioVenta;
}
