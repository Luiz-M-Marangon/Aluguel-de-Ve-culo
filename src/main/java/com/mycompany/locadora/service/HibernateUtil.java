package com.mycompany.locadora.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.mycompany.locadora.model.Usuario;
import com.mycompany.locadora.model.Veiculo;
import com.mycompany.locadora.model.Aluguel;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Usuario.class);
            configuration.addAnnotatedClass(Veiculo.class);
            configuration.addAnnotatedClass(Aluguel.class);
            
            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Falha na criação da SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}