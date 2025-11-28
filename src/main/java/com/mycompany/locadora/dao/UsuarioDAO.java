package com.mycompany.locadora.dao;

import com.mycompany.locadora.service.HibernateUtil;
import com.mycompany.locadora.model.Usuario;
import org.hibernate.Session;
import java.util.List;

public class UsuarioDAO extends GenericDAO<Usuario> {
    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Usuario WHERE email = :email", Usuario.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Usuario> findByName(String nome) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Usuario WHERE LOWER(nome) LIKE LOWER(:nome)", Usuario.class)
                    .setParameter("nome", "%" + nome + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}