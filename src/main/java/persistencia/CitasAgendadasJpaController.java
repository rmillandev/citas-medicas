/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.CitasAgendadas;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Ronald
 */
public class CitasAgendadasJpaController implements Serializable {

    public CitasAgendadasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CitasAgendadasJpaController() {
        emf = Persistence.createEntityManagerFactory("SistemaCitasMedicasPU");
    }

    public void create(CitasAgendadas citasAgendadas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(citasAgendadas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CitasAgendadas citasAgendadas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            citasAgendadas = em.merge(citasAgendadas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = citasAgendadas.getId();
                if (findCitasAgendadas(id) == null) {
                    throw new NonexistentEntityException("The citasAgendadas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CitasAgendadas citasAgendadas;
            try {
                citasAgendadas = em.getReference(CitasAgendadas.class, id);
                citasAgendadas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The citasAgendadas with id " + id + " no longer exists.", enfe);
            }
            em.remove(citasAgendadas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CitasAgendadas> findCitasAgendadasEntities() {
        return findCitasAgendadasEntities(true, -1, -1);
    }

    public List<CitasAgendadas> findCitasAgendadasEntities(int maxResults, int firstResult) {
        return findCitasAgendadasEntities(false, maxResults, firstResult);
    }

    private List<CitasAgendadas> findCitasAgendadasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CitasAgendadas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CitasAgendadas findCitasAgendadas(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CitasAgendadas.class, id);
        } finally {
            em.close();
        }
    }

    public int getCitasAgendadasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CitasAgendadas> rt = cq.from(CitasAgendadas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public boolean existeCitaAgendada(int identificador, Date fecDate) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM CitasAgendadas c WHERE c.identificador = :identificador OR c.fecha = :fecDate", Long.class);
            query.setParameter("identificador", identificador);
            query.setParameter("fecDate", fecDate);
            Long count = (Long) query.getSingleResult();
            System.out.println(count);
            return count > 0; // Devuelve true si hay al menos una cita agendada
        } catch (Exception ex) {
            return false;
        } finally {
            em.close();
        }
    }

}
