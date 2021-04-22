import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Runner {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.close();
        sessionFactory.close();
    }
}
