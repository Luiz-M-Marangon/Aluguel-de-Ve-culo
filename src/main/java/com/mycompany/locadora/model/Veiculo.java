package com.mycompany.locadora.model;

import javax.persistence.*;

@Entity
@Table(name = "veiculos")
public class Veiculo {
    @Id
    private String placa;
    
    @Column(nullable = false)
    private String marca;
    
    @Column(nullable = false)
    private String modelo;
    
    private Integer ano;
    
    private String cor;
    
    private Boolean disponivel = true;
    
    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    private java.util.List<Aluguel> alugueis = new java.util.ArrayList<>();
    
    // Construtores
    public Veiculo() {}
    
    public Veiculo(String placa, String marca, String modelo, Integer ano, String cor) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
    }
    
    // Getters e Setters
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    
    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
    
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    
    public Boolean getDisponivel() { return disponivel; }
    public void setDisponivel(Boolean disponivel) { this.disponivel = disponivel; }
    
    public java.util.List<Aluguel> getAlugueis() { return alugueis; }
    public void setAlugueis(java.util.List<Aluguel> alugueis) { this.alugueis = alugueis; }
    
    @Override
    public String toString() {
        return marca + " " + modelo + " (" + placa + ")";
    }
}