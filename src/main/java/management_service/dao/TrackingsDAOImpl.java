package management_service.dao;

import management_service.entity.Tracking;
import management_service.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TrackingsDAOImpl implements TrackingsDAO<Tracking> {

    @Override
    public Tracking getById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Tracking.class, id);
    }

    @Override
    public List<Tracking> getUserTrackings(long user) {
        CriteriaBuilder cb = HibernateSessionFactoryUtil.getSessionFactory().openSession().getCriteriaBuilder();
        CriteriaQuery<Tracking> cq = cb.createQuery(Tracking.class);
        Root<Tracking> tracking = cq.from(Tracking.class);
        cq.select(tracking).where(tracking.get("user").in(user));
        TypedQuery<Tracking> tq = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(cq);
        try {
            return tq.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
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
