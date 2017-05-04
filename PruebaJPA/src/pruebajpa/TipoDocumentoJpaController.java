/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebajpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import pruebajpa.exceptions.NonexistentEntityException;

/**
 *
 * @author invitado
 */
public class TipoDocumentoJpaController implements Serializable {

    public TipoDocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoDocumento tipoDocumento) {
        if (tipoDocumento.getPersonalCollection() == null) {
            tipoDocumento.setPersonalCollection(new ArrayList<Personal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Personal> attachedPersonalCollection = new ArrayList<Personal>();
            for (Personal personalCollectionPersonalToAttach : tipoDocumento.getPersonalCollection()) {
                personalCollectionPersonalToAttach = em.getReference(personalCollectionPersonalToAttach.getClass(), personalCollectionPersonalToAttach.getIdentificacionPersonal());
                attachedPersonalCollection.add(personalCollectionPersonalToAttach);
            }
            tipoDocumento.setPersonalCollection(attachedPersonalCollection);
            em.persist(tipoDocumento);
            for (Personal personalCollectionPersonal : tipoDocumento.getPersonalCollection()) {
                TipoDocumento oldTpDocumentoOfPersonalCollectionPersonal = personalCollectionPersonal.getTpDocumento();
                personalCollectionPersonal.setTpDocumento(tipoDocumento);
                personalCollectionPersonal = em.merge(personalCollectionPersonal);
                if (oldTpDocumentoOfPersonalCollectionPersonal != null) {
                    oldTpDocumentoOfPersonalCollectionPersonal.getPersonalCollection().remove(personalCollectionPersonal);
                    oldTpDocumentoOfPersonalCollectionPersonal = em.merge(oldTpDocumentoOfPersonalCollectionPersonal);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoDocumento tipoDocumento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocumento persistentTipoDocumento = em.find(TipoDocumento.class, tipoDocumento.getTpIdDocumento());
            Collection<Personal> personalCollectionOld = persistentTipoDocumento.getPersonalCollection();
            Collection<Personal> personalCollectionNew = tipoDocumento.getPersonalCollection();
            Collection<Personal> attachedPersonalCollectionNew = new ArrayList<Personal>();
            for (Personal personalCollectionNewPersonalToAttach : personalCollectionNew) {
                personalCollectionNewPersonalToAttach = em.getReference(personalCollectionNewPersonalToAttach.getClass(), personalCollectionNewPersonalToAttach.getIdentificacionPersonal());
                attachedPersonalCollectionNew.add(personalCollectionNewPersonalToAttach);
            }
            personalCollectionNew = attachedPersonalCollectionNew;
            tipoDocumento.setPersonalCollection(personalCollectionNew);
            tipoDocumento = em.merge(tipoDocumento);
            for (Personal personalCollectionOldPersonal : personalCollectionOld) {
                if (!personalCollectionNew.contains(personalCollectionOldPersonal)) {
                    personalCollectionOldPersonal.setTpDocumento(null);
                    personalCollectionOldPersonal = em.merge(personalCollectionOldPersonal);
                }
            }
            for (Personal personalCollectionNewPersonal : personalCollectionNew) {
                if (!personalCollectionOld.contains(personalCollectionNewPersonal)) {
                    TipoDocumento oldTpDocumentoOfPersonalCollectionNewPersonal = personalCollectionNewPersonal.getTpDocumento();
                    personalCollectionNewPersonal.setTpDocumento(tipoDocumento);
                    personalCollectionNewPersonal = em.merge(personalCollectionNewPersonal);
                    if (oldTpDocumentoOfPersonalCollectionNewPersonal != null && !oldTpDocumentoOfPersonalCollectionNewPersonal.equals(tipoDocumento)) {
                        oldTpDocumentoOfPersonalCollectionNewPersonal.getPersonalCollection().remove(personalCollectionNewPersonal);
                        oldTpDocumentoOfPersonalCollectionNewPersonal = em.merge(oldTpDocumentoOfPersonalCollectionNewPersonal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoDocumento.getTpIdDocumento();
                if (findTipoDocumento(id) == null) {
                    throw new NonexistentEntityException("The tipoDocumento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocumento tipoDocumento;
            try {
                tipoDocumento = em.getReference(TipoDocumento.class, id);
                tipoDocumento.getTpIdDocumento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoDocumento with id " + id + " no longer exists.", enfe);
            }
            Collection<Personal> personalCollection = tipoDocumento.getPersonalCollection();
            for (Personal personalCollectionPersonal : personalCollection) {
                personalCollectionPersonal.setTpDocumento(null);
                personalCollectionPersonal = em.merge(personalCollectionPersonal);
            }
            em.remove(tipoDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoDocumento> findTipoDocumentoEntities() {
        return findTipoDocumentoEntities(true, -1, -1);
    }

    public List<TipoDocumento> findTipoDocumentoEntities(int maxResults, int firstResult) {
        return findTipoDocumentoEntities(false, maxResults, firstResult);
    }

    private List<TipoDocumento> findTipoDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoDocumento.class));
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

    public TipoDocumento findTipoDocumento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoDocumento> rt = cq.from(TipoDocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
