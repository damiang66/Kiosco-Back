package kiosco.kiosco.entidad.producto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productos")
@Builder
@Getter
@Setter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long codigo;
    private String descripcion;
    private Long cantidad;
    private Double precioCompra;
    private Double precioVenta;


}