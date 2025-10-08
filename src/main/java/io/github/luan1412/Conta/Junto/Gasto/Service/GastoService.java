package io.github.luan1412.Conta.Junto.Gasto.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.luan1412.Conta.Junto.Gasto.Repository.GastoRepository;
import io.github.luan1412.Conta.Junto.Gasto.Model.GastoModel;
@Service
public class GastoService {

    @Autowired
    GastoRepository gastoRepository;

    public GastoModel createGasto(GastoModel gasto){
        return gastoRepository.save(gasto);
    }

    public List<GastoModel> readGasto(){
        return this.gastoRepository.findAll();
    }

    public Optional<GastoModel> readGastosById(Long id){
        return this.gastoRepository.findById(id);
    }

    public Optional<GastoModel> updateGasto(Long id, GastoModel novosDadoGasto){

            return this.gastoRepository.findById(id).map(atualizaGasto ->{

                atualizaGasto.setTitulo(novosDadoGasto.getTitulo());
                atualizaGasto.setDescricao(novosDadoGasto.getDescricao());
                atualizaGasto.setValor(novosDadoGasto.getValor());
                atualizaGasto.setData(novosDadoGasto.getData());

                 return this.gastoRepository.save(atualizaGasto);
            });
    }

    public Boolean deleteGasto(Long id){

        Optional<GastoModel> gastoOptional = this.gastoRepository.findById(id);

        if (gastoOptional.isPresent()) {
            this.gastoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

}
