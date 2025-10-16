package kiosco.kiosco.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "venta_detalle")
public class VentaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private Integer cantidad;

    private Double precioUnitario;

    private Double subtotal;



    @ManyToOne
    @JoinColumn(name = "venta_id")
    @JsonBackReference
    private Venta venta;
}