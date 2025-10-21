package kiosco.kiosco.repository;

import kiosco.kiosco.entidad.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta,Long> {
    List<Venta> findByCerradaFalse();

    @Query("SELECT SUM(v.total) FROM Venta v WHERE v.cerrada = false")
    Double getTotalVentasAbiertas();
    @Query("SELECT v FROM Venta v WHERE v.fecha BETWEEN :inicio AND :fin")
    List<Venta> findByFechaBetween(@Param("inicio") Date inicio, @Param("fin") Date fin);
}
