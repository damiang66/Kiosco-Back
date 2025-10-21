package kiosco.kiosco.repository;

import kiosco.kiosco.entidad.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CompraRepository extends JpaRepository<Compra,Long> {
    @Query("SELECT c FROM Compra c WHERE c.fecha BETWEEN :inicio AND :fin")
    List<Compra> findByFechaBetween(@Param("inicio") Date inicio, @Param("fin") Date fin);

}
