package com.arnur.database_assignment2.dao;

import com.arnur.database_assignment2.entity.Country;
import com.arnur.database_assignment2.entity.Discover;
import com.arnur.database_assignment2.keys.DiscoverKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DiscoverDao {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Discover> getAllDiscovers() {

        Query query = entityManager.createQuery("from Discover");
        List<Discover> allDiscovers = query.getResultList();
        return allDiscovers;
    }

    @Transactional
    public boolean saveDiscover(Discover discover) {
        try {
            entityManager.merge(discover);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean persistDiscover(Discover discover) {
        String q = "INSERT INTO Discover VALUES (?,?,?)";
        System.out.println(q);

        Query query = entityManager.createQuery("from Discover");
        List<Discover> allDiscovers = query.getResultList();
        for (Discover discover1 : allDiscovers) {
            if (discover.getCname().equals(discover1.getCname()) && discover.getDisease_code().equals(discover.getDisease_code())) return false;
        }

        try {
            entityManager.createNativeQuery(q)
                    .setParameter(1,discover.getCname())
                    .setParameter(2,discover.getDisease_code())
                    .setParameter(3,discover.getFirst_enc_date())
                    .executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public Discover getDiscover(String cname, String disease_code) {
        DiscoverKey discoverKey = new DiscoverKey(cname, disease_code);
        Discover discover = entityManager.find(Discover.class,discoverKey);
        return discover;
    }

    @Transactional
    public void deleteDiscover(String cname, String disease_code) {
        Query query = entityManager.createQuery("delete from Discover " +
                "where cname = " + cname + " and disease_code = " + disease_code);
        query.executeUpdate();
    }
}
