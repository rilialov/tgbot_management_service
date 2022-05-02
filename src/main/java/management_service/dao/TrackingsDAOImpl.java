package management_service.dao;

import management_service.entity.Tracking;
import management_service.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TrackingsDAOImpl implements TrackingsDAO<Tracking> {

    @Override
    public Tracking getById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Tracking.class, id);
    }

    @Override
    public List<Tracking> getAll() {
        return (List<Tracking>) HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("From Tracking").list();
    }

    @Override
    public Tracking create(Tracking tracking) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(tracking);
        tx1.commit();
        session.close();
        return tracking;
    }

    @Override
    public void update(Tracking tracking) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(tracking);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Tracking tracking) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(tracking);
        tx1.commit();
        session.close();
    }

}
