package io.github.luan1412.Conta.Junto.Grupo.Service;

import io.github.luan1412.Conta.Junto.Grupo.Repository.GrupoRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.luan1412.Conta.Junto.Grupo.Model.GrupoModel;

@Service
public class GrupoService {

    @Autowired
    GrupoRepository grupoRepository;


    public GrupoModel createGrupo(GrupoModel grupoModel){
        return this.grupoRepository.save(grupoModel);
    }

    public List<GrupoModel> readGrupo(){
        return this.grupoRepository.findAll();
    }

    public Optional<GrupoModel> readGrupoById(Long id){
        return this.grupoRepository.findById(id);
    }

    public Optional<GrupoModel> updateGrupo(Long id, GrupoModel novosDadosGrupo){
        return this.grupoRepository.findById(id).map(atualizaGrupo ->{
            
            atualizaGrupo.setNome(novosDadosGrupo.getNome());

            return grupoRepository.save(atualizaGrupo);
        });
    }

    public Boolean deleteGrupo(Long id){
        
        Optional <GrupoModel> grupoOtional = this.grupoRepository.findById(id);

        if (grupoOtional.isPresent()) {
            this.grupoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
    
}


