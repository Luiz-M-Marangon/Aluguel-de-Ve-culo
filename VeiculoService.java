package com.mycompany.locadora.service;

import com.mycompany.locadora.dao.VeiculoDAO;
import com.mycompany.locadora.model.Veiculo;
import java.util.List;

public class VeiculoService {
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();

    public void salvarVeiculo(Veiculo veiculo) {
        veiculoDAO.save(veiculo);
    }

    public void atualizarVeiculo(Veiculo veiculo) {
        veiculoDAO.update(veiculo);
    }

    public void excluirVeiculo(Veiculo veiculo) {
        veiculoDAO.delete(veiculo);
    }

    public List<Veiculo> listarTodos() {
        return veiculoDAO.findAll();
    }

    public List<Veiculo> listarDisponiveis() {
        return veiculoDAO.findDisponiveis();
    }

    public Veiculo buscarPorPlaca(String placa) {
        return veiculoDAO.findById(placa);
    }
}