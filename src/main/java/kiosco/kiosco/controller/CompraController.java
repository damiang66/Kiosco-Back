package kiosco.kiosco.controller;
import kiosco.kiosco.entidad.Compra;
import kiosco.kiosco.service.CompraService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "http://localhost:4200")
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping
    public List<Compra> listar() {
        return compraService.finfAll();
    }

    @PostMapping
    public Compra guardar(@RequestBody Compra compra) {
        return compraService.save(compra);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        compraService.delete(id);
    }

    @GetMapping("/{id}")
    public Compra buscarPorId(@PathVariable Long id) {
        return compraService.findById(id);
    }
}
