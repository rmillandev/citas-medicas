/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Cita;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import modelo.Paciente;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Ronald
 */
public class PacienteJpaController implements Serializable {

    public PacienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PacienteJpaController() {
        emf = Persistence.createEntityManagerFactory("SistemaCitasMedicasPU");
    }

    public void create(Paciente paciente) {
        if (paciente.getListaCitas() == null) {
            paciente.setListaCitas(new ArrayList<Cita>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cita> attachedListaCitas = new ArrayList<Cita>();
            for (Cita listaCitasCitaToAttach : paciente.getListaCitas()) {
                listaCitasCitaToAttach = em.getReference(listaCitasCitaToAttach.getClass(), listaCitasCitaToAttach.getId());
                attachedListaCitas.add(listaCitasCitaToAttach);
            }
            paciente.setListaCitas(attachedListaCitas);
            em.persist(paciente);
            for (Cita listaCitasCita : paciente.getListaCitas()) {
                Paciente oldPacienteOfListaCitasCita = listaCitasCita.getPaciente();
                listaCitasCita.setPaciente(paciente);
                listaCitasCita = em.merge(listaCitasCita);
                if (oldPacienteOfListaCitasCita != null) {
                    oldPacienteOfListaCitasCita.getListaCitas().remove(listaCitasCita);
                    oldPacienteOfListaCitasCita = em.merge(oldPacienteOfListaCitasCita);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paciente paciente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente persistentPaciente = em.find(Paciente.class, paciente.getId());
            List<Cita> listaCitasOld = persistentPaciente.getListaCitas();
            List<Cita> listaCitasNew = paciente.getListaCitas();
            List<Cita> attachedListaCitasNew = new ArrayList<Cita>();
            for (Cita listaCitasNewCitaToAttach : listaCitasNew) {
                listaCitasNewCitaToAttach = em.getReference(listaCitasNewCitaToAttach.getClass(), listaCitasNewCitaToAttach.getId());
                attachedListaCitasNew.add(listaCitasNewCitaToAttach);
            }
            listaCitasNew = attachedListaCitasNew;
            paciente.setListaCitas(listaCitasNew);
            paciente = em.merge(paciente);
            for (Cita listaCitasOldCita : listaCitasOld) {
                if (!listaCitasNew.contains(listaCitasOldCita)) {
                    listaCitasOldCita.setPaciente(null);
                    listaCitasOldCita = em.merge(listaCitasOldCita);
                }
            }
            for (Cita listaCitasNewCita : listaCitasNew) {
                if (!listaCitasOld.contains(listaCitasNewCita)) {
                    Paciente oldPacienteOfListaCitasNewCita = listaCitasNewCita.getPaciente();
                    listaCitasNewCita.setPaciente(paciente);
                    listaCitasNewCita = em.merge(listaCitasNewCita);
                    if (oldPacienteOfListaCitasNewCita != null && !oldPacienteOfListaCitasNewCita.equals(paciente)) {
                        oldPacienteOfListaCitasNewCita.getListaCitas().remove(listaCitasNewCita);
                        oldPacienteOfListaCitasNewCita = em.merge(oldPacienteOfListaCitasNewCita);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = paciente.getId();
                if (findPaciente(id) == null) {
                    throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.");
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
            Paciente paciente;
            try {
                paciente = em.getReference(Paciente.class, id);
                paciente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.", enfe);
            }
            List<Cita> listaCitas = paciente.getListaCitas();
            for (Cita listaCitasCita : listaCitas) {
                listaCitasCita.setPaciente(null);
                listaCitasCita = em.merge(listaCitasCita);
            }
            em.remove(paciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paciente> findPacienteEntities() {
        return findPacienteEntities(true, -1, -1);
    }

    public List<Paciente> findPacienteEntities(int maxResults, int firstResult) {
        return findPacienteEntities(false, maxResults, firstResult);
    }

    private List<Paciente> findPacienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paciente.class));
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

    public Paciente findPaciente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getPacienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paciente> rt = cq.from(Paciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int obtenerIdPacientePorNumeroDocumento(long numero) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Integer> query = em.createQuery(
                    "SELECT p.id FROM Paciente p WHERE p.numeroDocumento = :numero",
                    Integer.class
            );
            query.setParameter("numero", numero);
            List<Integer> resultados = query.getResultList();
            if (!resultados.isEmpty()) {
                return resultados.get(0); // Devuelve el primer ID encontrado
            } else {
                return 0; // Si no se encontraron resultados, retorna 0 o un valor que indique "no encontrado"
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Manejo de la excepción
            return 0; // En caso de excepción, retorna 0 o un valor de "error"
        } finally {
            em.close();
        }
    }

}
