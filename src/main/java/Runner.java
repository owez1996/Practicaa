import entity.Appeal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        List<Object[]> list = null;
        try {
            session.beginTransaction();

            // Query<Object[]> query = session.createSQLQuery("SELECT id, round(cost/1.9556,2) AS Цена_в_USD, round (cost/2.3897,2) AS Цена_в_EUR, round(cost/3.4693*100,2) AS Цена_в_RUB FROM Conclusion\n");
            Query<Object[]> query = session.createSQLQuery("SELECT Doctors.Name as Имя_доктора, Avg(Conclusion.Cost) as Цена_лечения, 'Мало' as Статус\n" +
                    "FROM Doctors INNER JOIN Conclusion\n" +
                    "ON Doctors.ID=Conclusion.ID_Doctor\n" +
                    "WHERE Conclusion.Cost<100\n" +
                    "GROUP BY Doctors.Name\n" +
                    "UNION\n" +
                    "SELECT Doctors.Name as Имя_доктора, Avg(Conclusion.Cost) as Цена_лечения, 'Средне' as Статус\n" +
                    "FROM Doctors INNER JOIN Conclusion\n" +
                    "ON Doctors.ID=Conclusion.ID_Doctor\n" +
                    "WHERE Conclusion.Cost>=100 AND Conclusion.Cost<300\n" +
                    "GROUP BY Doctors.Name\n" +
                    "UNION\n" +
                    "SELECT Doctors.Name as Имя_доктора, Avg(Conclusion.Cost) as Цена_лечения, 'Много' as Статус\n" +
                    "FROM Doctors INNER JOIN Conclusion\n" +
                    "ON Doctors.ID=Conclusion.ID_Doctor\n" +
                    "WHERE Conclusion.Cost>=300\n" +
                    "GROUP BY Doctors.Name");
            list = query.list();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
        } finally {
            session.close();
            sessionFactory.close();
        }

        //list.forEach(p -> System.out.println("Id: "+p[0]+" Цена в USD: "+p[1] + " Цена в EUR: " + p[2] + " Цена в RUB: " + p[3]));
        list.forEach(p -> System.out.println("Имя доктора: "+p[0]+" Цена лечения: "+p[1] + " Статус: " + p[2]));
    }
}
