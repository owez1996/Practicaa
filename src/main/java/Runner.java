import entity.Appeal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        List roles = null;
        try {
            session.beginTransaction();

            Query query = session.createQuery("FROM Appeal ");
            roles = query.list();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        } finally {
            session.close();
            sessionFactory.close();
        }
        roles.forEach(System.out::println);

    }
}
