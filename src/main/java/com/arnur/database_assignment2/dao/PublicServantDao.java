package com.arnur.database_assignment2.dao;

import com.arnur.database_assignment2.entity.Doctor;
import com.arnur.database_assignment2.entity.Publicservant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PublicServantDao {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Publicservant> getAllPublicServants() {

        Query query = entityManager.createQuery("from Publicservant");
        List<Publicservant> allPublicServants = query.getResultList();
        return allPublicServants;
    }

    @Transactional
    public boolean savePublicServant(Publicservant publicServant) {
        try {
            entityManager.merge(publicServant);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean persistPublicServant(Publicservant publicServant) {
        String q = "INSERT INTO PublicServant VALUES (?,?)";
        System.out.println(q);

        Query query = entityManager.createQuery("from Publicservant");
        List<Publicservant> allPublicServants = query.getResultList();
        for (Publicservant publicServant1 : allPublicServants) {
            if (publicServant.getEmail().equals(publicServant1.getEmail()))  return false;
        }

        try {
            entityManager.createNativeQuery(q)
                    .setParameter(1,publicServant.getEmail())
                    .setParameter(2,publicServant.getDepartment())
                    .executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public Publicservant getPublicServant(String email) {
        Publicservant publicServant = entityManager.find(Publicservant.class,email);
        return publicServant;
    }

    @Transactional
    public void deletePublicServant(String email) {
        Query query = entityManager.createQuery("delete from Publicservant " +
                "where email = " + email);
        query.executeUpdate();
    }
}
