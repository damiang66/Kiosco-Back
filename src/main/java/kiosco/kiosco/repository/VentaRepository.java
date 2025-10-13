package kiosco.kiosco.repository;

import kiosco.kiosco.entidad.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta,Long> {
}
