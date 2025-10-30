package io.github.luan1412.Conta.Junto.Gasto.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.github.luan1412.Conta.Junto.Grupo.Model.GrupoModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_gasto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GastoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    
    @ManyToOne
    @JoinColumn(name= "grupo_id")
    private GrupoModel grupo;

}
