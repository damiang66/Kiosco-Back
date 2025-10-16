package kiosco.kiosco.entidad;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  //  private String numeroFactura;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha = new Date();

    //private String cliente;

    private Double total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<VentaDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<VentaDetalle> detalles) {
        this.detalles = detalles;
    }

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VentaDetalle> detalles = new ArrayList<>();
}



