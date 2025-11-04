package kiosco.kiosco.controller;

import kiosco.kiosco.entidad.Usuario;
import kiosco.kiosco.entidad.Venta;

import kiosco.kiosco.service.UsuarioService;
import kiosco.kiosco.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/caja")
@CrossOrigin(origins = "http://localhost:4200")
public class CierreCajaController {

    private final VentaService ventaService;
    private final UsuarioService usuarioService;

    public CierreCajaController(VentaService ventaService, UsuarioService usuarioService) {
        this.ventaService = ventaService;
        this.usuarioService = usuarioService;
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

    @PostMapping
    public Usuario iniciarSesion(@RequestBody Usuario usuario){
        return usuarioService.iniciarSesion(usuario);
    }
}
