package kiosco.kiosco.service;

import kiosco.kiosco.entidad.Compra;


import java.util.List;

public interface CompraService {
    List<Compra> finfAll();
    Compra save(Compra compra);
    void delete(Long id);
    Compra findById(Long id);
    Compra actualizarCompra(Long id, Compra venta);
}
