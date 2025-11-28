package com.mycompany.locadora.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alugueis")
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;
    
    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;
    
    @Column(name = "data_fim")
    private LocalDateTime dataFim;
    
    @Column(name = "km_inicial")
    private Integer kmInicial;
    
    @Column(name = "km_final")
    private Integer kmFinal;
    
    private String status; // ABERTO, FECHADO
    
    // Construtores
    public Aluguel() {}
    
    public Aluguel(Usuario usuario, Veiculo veiculo, LocalDateTime dataInicio, 
                   LocalDateTime dataFim, Integer kmInicial) {
        this.usuario = usuario;
        this.veiculo = veiculo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.kmInicial = kmInicial;
        this.status = "ABERTO";
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }
    
    public LocalDateTime getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDateTime dataInicio) { this.dataInicio = dataInicio; }
    
    public LocalDateTime getDataFim() { return dataFim; }
    public void setDataFim(LocalDateTime dataFim) { this.dataFim = dataFim; }
    
    public Integer getKmInicial() { return kmInicial; }
    public void setKmInicial(Integer kmInicial) { this.kmInicial = kmInicial; }
    
    public Integer getKmFinal() { return kmFinal; }
    public void setKmFinal(Integer kmFinal) { this.kmFinal = kmFinal; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    // Método auxiliar
    public Integer getQuilometragemPercorrida() {
        if (kmFinal != null && kmInicial != null) {
            return kmFinal - kmInicial;
        }
        return null;
    }
}