package management_service.dao;

import management_service.entity.Report;
import management_service.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReportsDAOImpl implements ReportsDAO<Report> {

    @Override
    public Report getById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Report.class, id);
    }

    @Override
    public List<Report> getAll() {
        return (List<Report>) HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("From Report").list();
    }

    @Override
    public Report create(Report report) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(report);
        tx1.commit();
        session.close();
        return report;
    }

    @Override
    public void update(Report report) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(report);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Report report) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(report);
        tx1.commit();
        session.close();
    }

}
