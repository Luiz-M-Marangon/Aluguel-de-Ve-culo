package com.mycompany.locadora.service;

import com.mycompany.locadora.dao.AluguelDAO;
import com.mycompany.locadora.model.Aluguel;
import com.mycompany.locadora.model.Veiculo;
import java.util.List;

public class AluguelService {
    private final AluguelDAO aluguelDAO = new AluguelDAO();

    public void salvarAluguel(Aluguel aluguel) {
        // Marcar veículo como indisponível
        Veiculo veiculo = aluguel.getVeiculo();
        veiculo.setDisponivel(false);
        
        aluguelDAO.save(aluguel);
    }

    public void atualizarAluguel(Aluguel aluguel) {
        aluguelDAO.update(aluguel);
    }

    public void fecharAluguel(Aluguel aluguel, Integer kmFinal) {
        aluguel.setKmFinal(kmFinal);
        aluguel.setStatus("FECHADO");
        
        // Liberar veículo
        Veiculo veiculo = aluguel.getVeiculo();
        veiculo.setDisponivel(true);
        
        aluguelDAO.update(aluguel);
    }

    public List<Aluguel> listarTodos() {
        return aluguelDAO.findAll();
    }

    public List<Aluguel> listarAbertos() {
        return aluguelDAO.findAbertos();
    }
}