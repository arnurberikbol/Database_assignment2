package com.arnur.database_assignment2.dao;

import com.arnur.database_assignment2.entity.Country;
import com.arnur.database_assignment2.entity.Disease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CountryDao {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Country> getAllCountries() {

        Query query = entityManager.createQuery("from Country");
        List<Country> allCountries = query.getResultList();
        return allCountries;
    }

    @Transactional
    public boolean saveCountry(Country country) {
        try {
            entityManager.merge(country);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean persistCountry(Country country) {
        String q = "INSERT INTO country VALUES (?,?)";
        System.out.println(q);

        Query query = entityManager.createQuery("from Country");
        List<Country> allCountries = query.getResultList();
        for (Country country2 : allCountries) {
            if (country.getCname().equals(country2.getCname())) return false;
        }

        try {
            entityManager.createNativeQuery(q)
                    .setParameter(1,country.getCname())
                    .setParameter(2,country.getPopulation())
                    .executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public Country getCountry(String cname) {
        Country country = entityManager.find(Country.class,cname);
        return country;
    }

    @Transactional
    public void deleteCountry(String cname) {
        Query query = entityManager.createQuery("delete from Country " +
                "where cname = " + cname);
        query.executeUpdate();
    }
}
