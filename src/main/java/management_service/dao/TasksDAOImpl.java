package management_service.dao;

import management_service.entity.Task;
import management_service.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TasksDAOImpl implements TasksDAO<Task> {

    @Override
    public Task getById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Task.class, id);
    }

    @Override
    public List<Task> getAll() {
        return (List<Task>) HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("From Task").list();
    }

    @Override
    public Task create(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(task);
        tx1.commit();
        session.close();
        return task;
    }

    @Override
    public void update(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(task);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(task);
        tx1.commit();
        session.close();
    }

}
