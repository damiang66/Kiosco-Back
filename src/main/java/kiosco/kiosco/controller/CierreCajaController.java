package kiosco.kiosco.controller;

import kiosco.kiosco.entidad.Venta;

import kiosco.kiosco.service.VentaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/caja")
@CrossOrigin(origins = "http://localhost:4200")
public class CierreCajaController {

    private final VentaService ventaService;

    public CierreCajaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping("/abiertas")
    public List<Venta> getVentasAbiertas() {
        return ventaService.obtenerVentasAbiertas();
    }

    @GetMapping("/total")
    public Double getTotalVentasAbiertas() {
      return getTotalVentasAbiertas();
    }

    @PostMapping("/cerrar")
    public void cerrarVentas() {
        ventaService.cerrarVentasAbiertas();
    }
}
