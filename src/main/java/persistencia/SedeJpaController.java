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
import modelo.Medico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Sede;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Ronald
 */
public class SedeJpaController implements Serializable {

    public SedeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public SedeJpaController(){
       emf =  Persistence.createEntityManagerFactory("SistemaCitasMedicasPU");
    }

    public void create(Sede sede) {
        if (sede.getListaMedicos() == null) {
            sede.setListaMedicos(new ArrayList<Medico>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Medico> attachedListaMedicos = new ArrayList<Medico>();
            for (Medico listaMedicosMedicoToAttach : sede.getListaMedicos()) {
                listaMedicosMedicoToAttach = em.getReference(listaMedicosMedicoToAttach.getClass(), listaMedicosMedicoToAttach.getId());
                attachedListaMedicos.add(listaMedicosMedicoToAttach);
            }
            sede.setListaMedicos(attachedListaMedicos);
            em.persist(sede);
            for (Medico listaMedicosMedico : sede.getListaMedicos()) {
                Sede oldSedeOfListaMedicosMedico = listaMedicosMedico.getSede();
                listaMedicosMedico.setSede(sede);
                listaMedicosMedico = em.merge(listaMedicosMedico);
                if (oldSedeOfListaMedicosMedico != null) {
                    oldSedeOfListaMedicosMedico.getListaMedicos().remove(listaMedicosMedico);
                    oldSedeOfListaMedicosMedico = em.merge(oldSedeOfListaMedicosMedico);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sede sede) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sede persistentSede = em.find(Sede.class, sede.getId());
            List<Medico> listaMedicosOld = persistentSede.getListaMedicos();
            List<Medico> listaMedicosNew = sede.getListaMedicos();
            List<Medico> attachedListaMedicosNew = new ArrayList<Medico>();
            for (Medico listaMedicosNewMedicoToAttach : listaMedicosNew) {
                listaMedicosNewMedicoToAttach = em.getReference(listaMedicosNewMedicoToAttach.getClass(), listaMedicosNewMedicoToAttach.getId());
                attachedListaMedicosNew.add(listaMedicosNewMedicoToAttach);
            }
            listaMedicosNew = attachedListaMedicosNew;
            sede.setListaMedicos(listaMedicosNew);
            sede = em.merge(sede);
            for (Medico listaMedicosOldMedico : listaMedicosOld) {
                if (!listaMedicosNew.contains(listaMedicosOldMedico)) {
                    listaMedicosOldMedico.setSede(null);
                    listaMedicosOldMedico = em.merge(listaMedicosOldMedico);
                }
            }
            for (Medico listaMedicosNewMedico : listaMedicosNew) {
                if (!listaMedicosOld.contains(listaMedicosNewMedico)) {
                    Sede oldSedeOfListaMedicosNewMedico = listaMedicosNewMedico.getSede();
                    listaMedicosNewMedico.setSede(sede);
                    listaMedicosNewMedico = em.merge(listaMedicosNewMedico);
                    if (oldSedeOfListaMedicosNewMedico != null && !oldSedeOfListaMedicosNewMedico.equals(sede)) {
                        oldSedeOfListaMedicosNewMedico.getListaMedicos().remove(listaMedicosNewMedico);
                        oldSedeOfListaMedicosNewMedico = em.merge(oldSedeOfListaMedicosNewMedico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = sede.getId();
                if (findSede(id) == null) {
                    throw new NonexistentEntityException("The sede with id " + id + " no longer exists.");
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
            Sede sede;
            try {
                sede = em.getReference(Sede.class, id);
                sede.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sede with id " + id + " no longer exists.", enfe);
            }
            List<Medico> listaMedicos = sede.getListaMedicos();
            for (Medico listaMedicosMedico : listaMedicos) {
                listaMedicosMedico.setSede(null);
                listaMedicosMedico = em.merge(listaMedicosMedico);
            }
            em.remove(sede);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sede> findSedeEntities() {
        return findSedeEntities(true, -1, -1);
    }

    public List<Sede> findSedeEntities(int maxResults, int firstResult) {
        return findSedeEntities(false, maxResults, firstResult);
    }

    private List<Sede> findSedeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sede.class));
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

    public Sede findSede(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sede.class, id);
        } finally {
            em.close();
        }
    }

    public int getSedeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sede> rt = cq.from(Sede.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
