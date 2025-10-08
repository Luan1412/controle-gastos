package io.github.luan1412.Conta.Junto.Gasto.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.luan1412.Conta.Junto.Gasto.Model.GastoModel;
import io.github.luan1412.Conta.Junto.Gasto.Service.GastoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/gastos")
public class GastoController {

    @Autowired
    GastoService gastoService;

    @PostMapping
    public GastoModel createGasto(@RequestBody GastoModel gasto){
        return this.gastoService.createGasto(gasto);
    }

    @GetMapping
    public List<GastoModel> readGasto() {
        return this.gastoService.readGasto();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GastoModel> readGastoModelbyId(@PathVariable Long id) {
        Optional<GastoModel> gastoOptional = this.gastoService.readGastosById(id);

        if (gastoOptional.isPresent()) {
            return ResponseEntity.ok(gastoOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GastoModel> updateGasto(@PathVariable Long id, @RequestBody GastoModel gasto){

        Optional<GastoModel> gastoAtualizadoOptional = this.gastoService.updateGasto(id,gasto);

        if (gastoAtualizadoOptional.isPresent()) {
            return ResponseEntity.ok(gastoAtualizadoOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGasto(@PathVariable Long id){
        
        Boolean gastoApagado = this.gastoService.deleteGasto(id);

        if (gastoApagado) {
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
