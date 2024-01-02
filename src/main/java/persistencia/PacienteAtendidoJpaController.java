/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.PacienteAtendido;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Ronald
 */
public class PacienteAtendidoJpaController implements Serializable {

    public PacienteAtendidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public PacienteAtendidoJpaController(){
        emf = Persistence.createEntityManagerFactory("SistemaCitasMedicasPU");
    }

    public void create(PacienteAtendido pacienteAtendido) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pacienteAtendido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PacienteAtendido pacienteAtendido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pacienteAtendido = em.merge(pacienteAtendido);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = pacienteAtendido.getId();
                if (findPacienteAtendido(id) == null) {
                    throw new NonexistentEntityException("The pacienteAtendido with id " + id + " no longer exists.");
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
            PacienteAtendido pacienteAtendido;
            try {
                pacienteAtendido = em.getReference(PacienteAtendido.class, id);
                pacienteAtendido.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pacienteAtendido with id " + id + " no longer exists.", enfe);
            }
            em.remove(pacienteAtendido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PacienteAtendido> findPacienteAtendidoEntities() {
        return findPacienteAtendidoEntities(true, -1, -1);
    }

    public List<PacienteAtendido> findPacienteAtendidoEntities(int maxResults, int firstResult) {
        return findPacienteAtendidoEntities(false, maxResults, firstResult);
    }

    private List<PacienteAtendido> findPacienteAtendidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PacienteAtendido.class));
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

    public PacienteAtendido findPacienteAtendido(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PacienteAtendido.class, id);
        } finally {
            em.close();
        }
    }

    public int getPacienteAtendidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PacienteAtendido> rt = cq.from(PacienteAtendido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<PacienteAtendido> historialCitasPorMedico(long documentoMedico){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<PacienteAtendido> query = em.createQuery("SELECT pa FROM PacienteAtendido pa WHERE pa.documento_medico = :documentoMedico", PacienteAtendido.class);
            query.setParameter("documentoMedico", documentoMedico);
            return query.getResultList();
        }finally{
            em.close();
        }
    }
    
}
