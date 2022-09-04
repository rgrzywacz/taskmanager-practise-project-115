package task;

import java.util.List;

import config.ConnectionManager;
import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * data access object, przedrostek przed dao informuje użytkownika do jakiego obiektu chce uzyskać dostęp w DB.
 * W klasie dao piszemy wszystkie instrukcje, które komunikują się z DB.
 */
public class TaskDao {
    public void insert(Task task) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(task);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Task> findAll() {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        List tasks = entityManager.createQuery("select t from Task t").getResultList();
        entityManager.close();
        return tasks;
    }

    public void deleteById(int taskIdToBeDeleted) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Task t where t.id=:id")
                .setParameter("id", taskIdToBeDeleted).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(String description, int taskId) {
        EntityManager entityManager = ConnectionManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Task t set t.description=:description where t.id=:id")
                             .setParameter("description", description)
                             .setParameter("id", taskId)
                             .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
