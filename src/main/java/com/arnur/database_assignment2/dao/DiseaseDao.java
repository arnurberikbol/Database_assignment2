package com.arnur.database_assignment2.dao;

import com.arnur.database_assignment2.entity.Disease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DiseaseDao {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Disease> getAllDiseases() {

        Query query = entityManager.createQuery("from Disease");
        List<Disease> allDiseases = query.getResultList();
        return allDiseases;
    }

    @Transactional
    public boolean saveDisease(Disease disease) {
        try {
            entityManager.merge(disease);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean persistDisease(Disease disease) {
        String q = "INSERT INTO disease VALUES (?,?,?,?)";
        System.out.println(q);

        Query query = entityManager.createQuery("from Disease");
        List<Disease> allDiseases = query.getResultList();
        for (Disease disease2 : allDiseases) {
            if (disease.getDisease_code().equals(disease2.getDisease_code())) return false;
        }

        try {
            entityManager.createNativeQuery(q)
                    .setParameter(1,disease.getDisease_code())
                    .setParameter(2,disease.getPathogen())
                    .setParameter(3,disease.getDescription())
                    .setParameter(4,disease.getId())
                    .executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public Disease getDisease(String disease_code) {
        Disease disease = entityManager.find(Disease.class,disease_code);
        return disease;
    }

    @Transactional
    public void deleteDisease(String disease_code) {
        Query query = entityManager.createQuery("delete from Disease " +
                "where disease_code = " + disease_code);
        query.executeUpdate();
    }
}
