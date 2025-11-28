package com.mycompany.locadora.dao;

import com.mycompany.locadora.service.HibernateUtil;
import com.mycompany.locadora.model.Veiculo;
import org.hibernate.Session;
import java.util.List;

public class VeiculoDAO extends GenericDAO<Veiculo> {
    public VeiculoDAO() {
        super(Veiculo.class);
    }

    public List<Veiculo> findDisponiveis() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Veiculo WHERE disponivel = true", Veiculo.class)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Veiculo> findByMarca(String marca) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Veiculo WHERE LOWER(marca) LIKE LOWER(:marca)", Veiculo.class)
                    .setParameter("marca", "%" + marca + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}