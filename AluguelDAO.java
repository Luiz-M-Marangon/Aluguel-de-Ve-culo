package com.mycompany.locadora.dao;

import com.mycompany.locadora.model.Aluguel;
import com.mycompany.locadora.service.HibernateUtil;
import org.hibernate.Session;
import java.util.List;

public class AluguelDAO extends GenericDAO<Aluguel> {
    public AluguelDAO() {
        super(Aluguel.class);
    }

    public List<Aluguel> findAbertos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Aluguel WHERE status = 'ABERTO'", Aluguel.class)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Aluguel> findByUsuario(Long usuarioId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Aluguel WHERE usuario.id = :usuarioId", Aluguel.class)
                    .setParameter("usuarioId", usuarioId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}