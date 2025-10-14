package kiosco.kiosco.entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroFactura;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha = new Date();

    private String cliente;

    private Double total;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VentaDetalle> detalles;
}



