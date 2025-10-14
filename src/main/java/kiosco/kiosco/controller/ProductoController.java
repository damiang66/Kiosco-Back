package kiosco.kiosco.controller;

import jakarta.validation.Valid;
import kiosco.kiosco.entidad.producto.Producto;
import kiosco.kiosco.entidad.producto.dto.ProductoRequestDto;
import kiosco.kiosco.entidad.producto.dto.ProductoResponseDto;
import kiosco.kiosco.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    private ResponseEntity<?>validar(@Valid BindingResult result){
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(e->{
            errores.put(e.getField(), "El campo: " + e.getField() + " " + e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDto>>findAll(){
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
    public ResponseEntity<ProductoResponseDto>update (@Valid @RequestBody ProductoRequestDto producto, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.update(producto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete (@PathVariable Long id){
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
