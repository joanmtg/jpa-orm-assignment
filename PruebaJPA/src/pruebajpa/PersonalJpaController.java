/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebajpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pruebajpa.exceptions.NonexistentEntityException;
import pruebajpa.exceptions.PreexistingEntityException;

/**
 *
 * @author invitado
 */
public class PersonalJpaController implements Serializable {

    public PersonalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Personal personal) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocumento tpDocumento = personal.getTpDocumento();
            if (tpDocumento != null) {
                tpDocumento = em.getReference(tpDocumento.getClass(), tpDocumento.getTpIdDocumento());
                personal.setTpDocumento(tpDocumento);
            }
            em.persist(personal);
            if (tpDocumento != null) {
                tpDocumento.getPersonalCollection().add(personal);
                tpDocumento = em.merge(tpDocumento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersonal(personal.getIdentificacionPersonal()) != null) {
                throw new PreexistingEntityException("Personal " + personal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Personal personal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personal persistentPersonal = em.find(Personal.class, personal.getIdentificacionPersonal());
            TipoDocumento tpDocumentoOld = persistentPersonal.getTpDocumento();
            TipoDocumento tpDocumentoNew = personal.getTpDocumento();
            if (tpDocumentoNew != null) {
                tpDocumentoNew = em.getReference(tpDocumentoNew.getClass(), tpDocumentoNew.getTpIdDocumento());
                personal.setTpDocumento(tpDocumentoNew);
            }
            personal = em.merge(personal);
            if (tpDocumentoOld != null && !tpDocumentoOld.equals(tpDocumentoNew)) {
                tpDocumentoOld.getPersonalCollection().remove(personal);
                tpDocumentoOld = em.merge(tpDocumentoOld);
            }
            if (tpDocumentoNew != null && !tpDocumentoNew.equals(tpDocumentoOld)) {
                tpDocumentoNew.getPersonalCollection().add(personal);
                tpDocumentoNew = em.merge(tpDocumentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = personal.getIdentificacionPersonal();
                if (findPersonal(id) == null) {
                    throw new NonexistentEntityException("The personal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personal personal;
            try {
                personal = em.getReference(Personal.class, id);
                personal.getIdentificacionPersonal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personal with id " + id + " no longer exists.", enfe);
            }
            TipoDocumento tpDocumento = personal.getTpDocumento();
            if (tpDocumento != null) {
                tpDocumento.getPersonalCollection().remove(personal);
                tpDocumento = em.merge(tpDocumento);
            }
            em.remove(personal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Personal> findPersonalEntities() {
        return findPersonalEntities(true, -1, -1);
    }

    public List<Personal> findPersonalEntities(int maxResults, int firstResult) {
        return findPersonalEntities(false, maxResults, firstResult);
    }

    private List<Personal> findPersonalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Personal.class));
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

    public Personal findPersonal(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Personal.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Personal> rt = cq.from(Personal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
