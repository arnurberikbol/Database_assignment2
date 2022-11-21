package com.arnur.database_assignment2.dao;

import com.arnur.database_assignment2.entity.Record;
import com.arnur.database_assignment2.entity.Specialize;
import com.arnur.database_assignment2.keys.SpecializeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class SpecializeDao {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Specialize> getAllSpecializes() {

        Query query = entityManager.createQuery("from Specialize");
        List<Specialize> allSpecializes = query.getResultList();
        return allSpecializes;
    }

    @Transactional
    public boolean saveSpecialize(Specialize specialize) {
        try {
            entityManager.merge(specialize);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean persistSpecialize(Specialize specialize) {
        String q = "INSERT INTO Specialize VALUES (?,?)";
        System.out.println(q);

        Query query = entityManager.createQuery("from Specialize");
        List<Specialize> allSpecializes = query.getResultList();
        for (Specialize specialize1 : allSpecializes) {
            if (specialize.getEmail().equals(specialize1.getEmail()) &&
                    specialize.getId() == specialize1.getId()) return false;
        }

        try {
            entityManager.createNativeQuery(q)
                    .setParameter(1,specialize.getId())
                    .setParameter(2,specialize.getEmail())
                    .executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public Specialize getSpecialize(int id, String email) {
        SpecializeKey specializeKey = new SpecializeKey(id, email);
        Specialize specialize = entityManager.find(Specialize.class,specializeKey);
        return specialize;
    }

    @Transactional
    public void deleteSpecialize(int id, String email) {
        Query query = entityManager.createQuery("delete from Specialize " +
                "where id =" + id + " and email = " + email);
        query.executeUpdate();
    }
}
