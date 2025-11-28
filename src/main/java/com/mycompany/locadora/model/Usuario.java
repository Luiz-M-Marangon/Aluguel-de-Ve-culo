package com.mycompany.locadora.model;

import com.mycompany.locadora.model.Aluguel;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(unique = true)
    private String email;
    
    private String telefone;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Aluguel> alugueis = new ArrayList<>();
    
    // Construtores
    public Usuario() {}
    
    public Usuario(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public List<Aluguel> getAlugueis() { return alugueis; }
    public void setAlugueis(List<Aluguel> alugueis) { this.alugueis = alugueis; }
    
    @Override
    public String toString() {
        return nome + " (" + email + ")";
    }
}