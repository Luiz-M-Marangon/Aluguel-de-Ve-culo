package com.mycompany.locadora.service;

import com.mycompany.locadora.dao.UsuarioDAO;
import com.mycompany.locadora.model.Usuario;
import java.util.List;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void salvarUsuario(Usuario usuario) {
        usuarioDAO.save(usuario);
    }

    public void atualizarUsuario(Usuario usuario) {
        usuarioDAO.update(usuario);
    }

    public void excluirUsuario(Usuario usuario) {
        usuarioDAO.delete(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioDAO.findAll();
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioDAO.findByEmail(email);
    }

    public List<Usuario> buscarPorNome(String nome) {
        return usuarioDAO.findByName(nome);
    }
}