package io.github.luan1412.Conta.Junto.Grupo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.luan1412.Conta.Junto.Grupo.Model.GrupoModel;
import io.github.luan1412.Conta.Junto.Grupo.Service.GrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    GrupoService grupoService;

    @PostMapping
    public GrupoModel createGrupo(@RequestBody GrupoModel grupo){
        return this.grupoService.createGrupo(grupo);
    }

    @GetMapping
    public List<GrupoModel> readGrupo(){
        return this.grupoService.readGrupo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoModel> readGrupoById(@PathVariable Long id){
        Optional<GrupoModel> grupoOptional = this.grupoService.readGrupoById(id);

        if (grupoOptional.isPresent()) {
            return ResponseEntity.ok(grupoOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrupoModel> updateGrupo(@PathVariable Long id, @RequestBody GrupoModel grupo){
        Optional<GrupoModel> grupoOptional = this.grupoService.updateGrupo(id, grupo);

        if (grupoOptional.isPresent()) {
            return ResponseEntity.ok(grupoOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGrupo(@PathVariable Long id){
        boolean grupoApagado = this.grupoService.deleteGrupo(id);

        if (grupoApagado) {

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{grupoId}/adicionarUsuario/{usuarioId}")
    public ResponseEntity<GrupoModel> adicionarUsuarioAoGrupo(@PathVariable Long grupoId,@PathVariable Long usuarioId){

        Optional<GrupoModel> grupoOptional = this.grupoService.adicionarUsuarioAoGrupo(grupoId, usuarioId);

        if (grupoOptional.isPresent()) {
            return ResponseEntity.ok(grupoOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }

}
