/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Sede;
import modelo.Horario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import modelo.Cita;
import modelo.Medico;
import modelo.MedicoDTO;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Ronald
 */
public class MedicoJpaController implements Serializable {

    public MedicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MedicoJpaController() {
        emf = Persistence.createEntityManagerFactory("SistemaCitasMedicasPU");
    }

    public void create(Medico medico) {
        if (medico.getListaHorario() == null) {
            medico.setListaHorario(new ArrayList<Horario>());
        }
        if (medico.getListaCitas() == null) {
            medico.setListaCitas(new ArrayList<Cita>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sede sede = medico.getSede();
            if (sede != null) {
                sede = em.getReference(sede.getClass(), sede.getId());
                medico.setSede(sede);
            }
            List<Horario> attachedListaHorario = new ArrayList<Horario>();
            for (Horario listaHorarioHorarioToAttach : medico.getListaHorario()) {
                listaHorarioHorarioToAttach = em.getReference(listaHorarioHorarioToAttach.getClass(), listaHorarioHorarioToAttach.getId());
                attachedListaHorario.add(listaHorarioHorarioToAttach);
            }
            medico.setListaHorario(attachedListaHorario);
            List<Cita> attachedListaCitas = new ArrayList<Cita>();
            for (Cita listaCitasCitaToAttach : medico.getListaCitas()) {
                listaCitasCitaToAttach = em.getReference(listaCitasCitaToAttach.getClass(), listaCitasCitaToAttach.getId());
                attachedListaCitas.add(listaCitasCitaToAttach);
            }
            medico.setListaCitas(attachedListaCitas);
            em.persist(medico);
            if (sede != null) {
                sede.getListaMedicos().add(medico);
                sede = em.merge(sede);
            }
            for (Horario listaHorarioHorario : medico.getListaHorario()) {
                Medico oldMedicoOfListaHorarioHorario = listaHorarioHorario.getMedico();
                listaHorarioHorario.setMedico(medico);
                listaHorarioHorario = em.merge(listaHorarioHorario);
                if (oldMedicoOfListaHorarioHorario != null) {
                    oldMedicoOfListaHorarioHorario.getListaHorario().remove(listaHorarioHorario);
                    oldMedicoOfListaHorarioHorario = em.merge(oldMedicoOfListaHorarioHorario);
                }
            }
            for (Cita listaCitasCita : medico.getListaCitas()) {
                Medico oldMedicoOfListaCitasCita = listaCitasCita.getMedico();
                listaCitasCita.setMedico(medico);
                listaCitasCita = em.merge(listaCitasCita);
                if (oldMedicoOfListaCitasCita != null) {
                    oldMedicoOfListaCitasCita.getListaCitas().remove(listaCitasCita);
                    oldMedicoOfListaCitasCita = em.merge(oldMedicoOfListaCitasCita);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Medico medico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medico persistentMedico = em.find(Medico.class, medico.getId());
            Sede sedeOld = persistentMedico.getSede();
            Sede sedeNew = medico.getSede();
            List<Horario> listaHorarioOld = persistentMedico.getListaHorario();
            List<Horario> listaHorarioNew = medico.getListaHorario();
            List<Cita> listaCitasOld = persistentMedico.getListaCitas();
            List<Cita> listaCitasNew = medico.getListaCitas();
            if (sedeNew != null) {
                sedeNew = em.getReference(sedeNew.getClass(), sedeNew.getId());
                medico.setSede(sedeNew);
            }
            List<Horario> attachedListaHorarioNew = new ArrayList<Horario>();
            for (Horario listaHorarioNewHorarioToAttach : listaHorarioNew) {
                listaHorarioNewHorarioToAttach = em.getReference(listaHorarioNewHorarioToAttach.getClass(), listaHorarioNewHorarioToAttach.getId());
                attachedListaHorarioNew.add(listaHorarioNewHorarioToAttach);
            }
            listaHorarioNew = attachedListaHorarioNew;
            medico.setListaHorario(listaHorarioNew);
            List<Cita> attachedListaCitasNew = new ArrayList<Cita>();
            for (Cita listaCitasNewCitaToAttach : listaCitasNew) {
                listaCitasNewCitaToAttach = em.getReference(listaCitasNewCitaToAttach.getClass(), listaCitasNewCitaToAttach.getId());
                attachedListaCitasNew.add(listaCitasNewCitaToAttach);
            }
            listaCitasNew = attachedListaCitasNew;
            medico.setListaCitas(listaCitasNew);
            medico = em.merge(medico);
            if (sedeOld != null && !sedeOld.equals(sedeNew)) {
                sedeOld.getListaMedicos().remove(medico);
                sedeOld = em.merge(sedeOld);
            }
            if (sedeNew != null && !sedeNew.equals(sedeOld)) {
                sedeNew.getListaMedicos().add(medico);
                sedeNew = em.merge(sedeNew);
            }
            for (Horario listaHorarioOldHorario : listaHorarioOld) {
                if (!listaHorarioNew.contains(listaHorarioOldHorario)) {
                    listaHorarioOldHorario.setMedico(null);
                    listaHorarioOldHorario = em.merge(listaHorarioOldHorario);
                }
            }
            for (Horario listaHorarioNewHorario : listaHorarioNew) {
                if (!listaHorarioOld.contains(listaHorarioNewHorario)) {
                    Medico oldMedicoOfListaHorarioNewHorario = listaHorarioNewHorario.getMedico();
                    listaHorarioNewHorario.setMedico(medico);
                    listaHorarioNewHorario = em.merge(listaHorarioNewHorario);
                    if (oldMedicoOfListaHorarioNewHorario != null && !oldMedicoOfListaHorarioNewHorario.equals(medico)) {
                        oldMedicoOfListaHorarioNewHorario.getListaHorario().remove(listaHorarioNewHorario);
                        oldMedicoOfListaHorarioNewHorario = em.merge(oldMedicoOfListaHorarioNewHorario);
                    }
                }
            }
            for (Cita listaCitasOldCita : listaCitasOld) {
                if (!listaCitasNew.contains(listaCitasOldCita)) {
                    listaCitasOldCita.setMedico(null);
                    listaCitasOldCita = em.merge(listaCitasOldCita);
                }
            }
            for (Cita listaCitasNewCita : listaCitasNew) {
                if (!listaCitasOld.contains(listaCitasNewCita)) {
                    Medico oldMedicoOfListaCitasNewCita = listaCitasNewCita.getMedico();
                    listaCitasNewCita.setMedico(medico);
                    listaCitasNewCita = em.merge(listaCitasNewCita);
                    if (oldMedicoOfListaCitasNewCita != null && !oldMedicoOfListaCitasNewCita.equals(medico)) {
                        oldMedicoOfListaCitasNewCita.getListaCitas().remove(listaCitasNewCita);
                        oldMedicoOfListaCitasNewCita = em.merge(oldMedicoOfListaCitasNewCita);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = medico.getId();
                if (findMedico(id) == null) {
                    throw new NonexistentEntityException("The medico with id " + id + " no longer exists.");
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
            Medico medico;
            try {
                medico = em.getReference(Medico.class, id);
                medico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medico with id " + id + " no longer exists.", enfe);
            }
            Sede sede = medico.getSede();
            if (sede != null) {
                sede.getListaMedicos().remove(medico);
                sede = em.merge(sede);
            }
            List<Horario> listaHorario = medico.getListaHorario();
            for (Horario listaHorarioHorario : listaHorario) {
                listaHorarioHorario.setMedico(null);
                listaHorarioHorario = em.merge(listaHorarioHorario);
            }
            List<Cita> listaCitas = medico.getListaCitas();
            for (Cita listaCitasCita : listaCitas) {
                listaCitasCita.setMedico(null);
                listaCitasCita = em.merge(listaCitasCita);
            }
            em.remove(medico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Medico> findMedicoEntities() {
        return findMedicoEntities(true, -1, -1);
    }

    public List<Medico> findMedicoEntities(int maxResults, int firstResult) {
        return findMedicoEntities(false, maxResults, firstResult);
    }

    private List<Medico> findMedicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Medico.class));
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

    public Medico findMedico(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Medico.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Medico> rt = cq.from(Medico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<MedicoDTO> getMedicoDTO(int id) {
        EntityManager em = getEntityManager();
        try {
            String sql = "select MEDICO.id, MEDICO.nombres, MEDICO.apellidos, ESPECIALIDAD.nombre as especialidad, SEDE.nombre as sede, HORARIO.fecha\n"
                    + "                         from MEDICO\n"
                    + "                         inner join ESPECIALIDAD on ESPECIALIDAD.id = MEDICO.id_especialidad\n"
                    + "                         inner join SEDE on SEDE.id = MEDICO.id_sede\n"
                    + "                         inner join HORARIO on HORARIO.id_medico = MEDICO.id\n"
                    + "                         where ESPECIALIDAD.id = ?1";
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, id);

            List<Object[]> results = query.getResultList();
            List<MedicoDTO> medicoDTOs = new ArrayList<>();

            for (Object[] result : results) {
                MedicoDTO medicoDTO = new MedicoDTO();
                medicoDTO.setId((Integer) result[0]);
                medicoDTO.setNombres((String) result[1]);
                medicoDTO.setApellidos((String) result[2]);
                medicoDTO.setEspecialidad((String) result[3]);
                medicoDTO.setSede((String) result[4]);

                // Obtener el LocalDateTime de la posición 5 del resultado
                LocalDateTime localDateTime = (LocalDateTime) result[5];

                // Convertir LocalDateTime a Instant y luego a Date
                Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
                Date date = Date.from(instant);

                // Asignar el Date convertido al DTO
                medicoDTO.setFecha(date);

                medicoDTOs.add(medicoDTO);
            }

            return medicoDTOs;
        } finally {
            em.close();
        }
    }
    
     public int obtenerIdMedicoPorNumeroDocumento(long numero) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Integer> query = em.createQuery(
                    "SELECT m.id FROM Medico m WHERE m.numeroDocumento = :numero",
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
