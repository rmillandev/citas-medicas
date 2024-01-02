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
import modelo.Cita;
import modelo.Paciente;
import modelo.Medico;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Ronald
 */
public class CitaJpaController implements Serializable {

    public CitaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CitaJpaController() {
        emf = Persistence.createEntityManagerFactory("SistemaCitasMedicasPU");
    }

    public void create(Cita cita) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente paciente = cita.getPaciente();
            if (paciente != null) {
                paciente = em.getReference(paciente.getClass(), paciente.getId());
                cita.setPaciente(paciente);
            }
            Medico medico = cita.getMedico();
            if (medico != null) {
                medico = em.getReference(medico.getClass(), medico.getId());
                cita.setMedico(medico);
            }
            em.persist(cita);
            if (paciente != null) {
                paciente.getListaCitas().add(cita);
                paciente = em.merge(paciente);
            }
            if (medico != null) {
                medico.getListaCitas().add(cita);
                medico = em.merge(medico);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cita cita) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cita persistentCita = em.find(Cita.class, cita.getId());
            Paciente pacienteOld = persistentCita.getPaciente();
            Paciente pacienteNew = cita.getPaciente();
            Medico medicoOld = persistentCita.getMedico();
            Medico medicoNew = cita.getMedico();
            if (pacienteNew != null) {
                pacienteNew = em.getReference(pacienteNew.getClass(), pacienteNew.getId());
                cita.setPaciente(pacienteNew);
            }
            if (medicoNew != null) {
                medicoNew = em.getReference(medicoNew.getClass(), medicoNew.getId());
                cita.setMedico(medicoNew);
            }
            cita = em.merge(cita);
            if (pacienteOld != null && !pacienteOld.equals(pacienteNew)) {
                pacienteOld.getListaCitas().remove(cita);
                pacienteOld = em.merge(pacienteOld);
            }
            if (pacienteNew != null && !pacienteNew.equals(pacienteOld)) {
                pacienteNew.getListaCitas().add(cita);
                pacienteNew = em.merge(pacienteNew);
            }
            if (medicoOld != null && !medicoOld.equals(medicoNew)) {
                medicoOld.getListaCitas().remove(cita);
                medicoOld = em.merge(medicoOld);
            }
            if (medicoNew != null && !medicoNew.equals(medicoOld)) {
                medicoNew.getListaCitas().add(cita);
                medicoNew = em.merge(medicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cita.getId();
                if (findCita(id) == null) {
                    throw new NonexistentEntityException("The cita with id " + id + " no longer exists.");
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
            Cita cita;
            try {
                cita = em.getReference(Cita.class, id);
                cita.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cita with id " + id + " no longer exists.", enfe);
            }
            Paciente paciente = cita.getPaciente();
            if (paciente != null) {
                paciente.getListaCitas().remove(cita);
                paciente = em.merge(paciente);
            }
            Medico medico = cita.getMedico();
            if (medico != null) {
                medico.getListaCitas().remove(cita);
                medico = em.merge(medico);
            }
            em.remove(cita);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cita> findCitaEntities() {
        return findCitaEntities(true, -1, -1);
    }

    public List<Cita> findCitaEntities(int maxResults, int firstResult) {
        return findCitaEntities(false, maxResults, firstResult);
    }

    private List<Cita> findCitaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cita.class));
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

    public Cita findCita(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cita.class, id);
        } finally {
            em.close();
        }
    }

    public int getCitaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cita> rt = cq.from(Cita.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Cita> obtenerCitasPorIdPaciente(int id) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cita> query = em.createQuery("SELECT c FROM Cita c WHERE c.paciente.id = :id", Cita.class);
            query.setParameter("id", id);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Cita> obtenerCitasPorIdMedico(int id) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cita> query = em.createQuery("SELECT c FROM Cita c WHERE c.medico.id = :id AND c.estado = 'Pendiente'", Cita.class);
            query.setParameter("id", id);
            return query.getResultList();
        } finally {
            em.close();
        }
    }


}
