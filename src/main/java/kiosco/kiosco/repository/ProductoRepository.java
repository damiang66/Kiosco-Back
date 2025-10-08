package kiosco.kiosco.repository;

import kiosco.kiosco.entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
    public List<Producto>findByCodigo(Long codigo);
}
