package com.arnur.database_assignment2.dao;

import com.arnur.database_assignment2.entity.Discover;
import com.arnur.database_assignment2.entity.Diseasetype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.UnexpectedRollbackException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DiseaseTypeDao {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Diseasetype> getAllDiseaseTypes() {

        Query query = entityManager.createQuery("from Diseasetype");
        List<Diseasetype> allDiseaseTypes = query.getResultList();

        return allDiseaseTypes;
    }

    @Transactional
    public boolean saveDiseaseType(Diseasetype diseaseType) {
        try {
            entityManager.merge(diseaseType);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean persistDiseaseType(Diseasetype diseaseType) {
        String q = "INSERT INTO DiseaseType VALUES (?,?)";
        System.out.println(q);

        Query query = entityManager.createQuery("from Diseasetype");
        List<Diseasetype> allDiseaseTypes = query.getResultList();
        for (Diseasetype diseaseType1 : allDiseaseTypes) {
            if (diseaseType.getId() == diseaseType1.getId())  return false;
        }

        try {
            entityManager.createNativeQuery(q)
                    .setParameter(1,diseaseType.getId())
                    .setParameter(2,diseaseType.getDescription())
                    .executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public Diseasetype getDiseaseType(int id) {
        Diseasetype diseaseType = entityManager.find(Diseasetype.class,id);
        return diseaseType;
    }

    @Transactional
    public void deleteDiseaseType(int id) {
        Query query = entityManager.createQuery("delete from Diseasetype " +
                "where id = " + id);
        query.executeUpdate();
    }
}
