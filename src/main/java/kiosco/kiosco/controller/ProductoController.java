package kiosco.kiosco.controller;

import jakarta.validation.Valid;
import kiosco.kiosco.entidad.Producto;
import kiosco.kiosco.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    ProductoService productoService;
    private ResponseEntity<?>validar(BindingResult result){
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(e->{
            errores.put(e.getField(), "El campo: " + e.getField() + " " + e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
    @GetMapping
    public ResponseEntity<?>findAll(){
        return ResponseEntity.ok(productoService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        Optional<Producto> productoOptional = productoService.findById(id);
        if (productoOptional.isPresent()){
            return ResponseEntity.ok(productoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<?>findByCodigo(@PathVariable Long codigo){
        return ResponseEntity.ok(productoService.findByCodigo(codigo));
    }
    @PostMapping
    public ResponseEntity<?>save (@Valid @RequestBody Producto producto, BindingResult result){
        if (result.hasErrors()){
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>update (@Valid @RequestBody Producto producto, BindingResult result, @PathVariable Long id) {
    if (result.hasErrors()){
        return validar(result);
        }
    Producto productodb = null;
    Optional<Producto>optionalProducto = productoService.findById(id);
    if (optionalProducto.isPresent()){
        productodb = optionalProducto.get();
        productodb.setDescripcion(producto.getDescripcion());
        productodb.setCantidad(producto.getCantidad());
        productodb.setCodigo(producto.getCodigo());
        productodb.setPrecioCompra(producto.getPrecioCompra());
        productodb.setPrecioVenta(producto.getPrecioVenta());
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(productodb));
    }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete (@PathVariable Long id){
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
