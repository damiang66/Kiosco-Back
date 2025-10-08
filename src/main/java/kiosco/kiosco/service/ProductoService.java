package kiosco.kiosco.service;

import kiosco.kiosco.entidad.Producto;

import java.util.List;
import java.util.Optional;


public interface ProductoService {
  public List<Producto> findAll();
  public Optional<Producto>findById(Long id);
  public List<Producto>findByCodigo (Long codigo);
  public Producto save(Producto producto);
  public void delete(Long id);
}
