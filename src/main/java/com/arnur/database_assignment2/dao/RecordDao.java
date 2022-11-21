package com.arnur.database_assignment2.dao;

import com.arnur.database_assignment2.entity.Record;
import com.arnur.database_assignment2.keys.RecordKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RecordDao {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Record> getAllRecords() {

        Query query = entityManager.createQuery("from Record");
        List<Record> allRecords = query.getResultList();
        return allRecords;
    }

    @Transactional
    public boolean saveRecord(Record record) {
        try {
            entityManager.merge(record);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean persistRecord(Record record) {
        String q = "INSERT INTO Record VALUES (?,?,?,?,?)";
        System.out.println(q);

        Query query = entityManager.createQuery("from Record");
        List<Record> allRecords = query.getResultList();
        for (Record record1 : allRecords) {
            if (record.getEmail().equals(record1.getEmail()) &&
            record.getCname().equals(record1.getCname()) &&
            record.getDisease_code().equals(record.getDisease_code())) return false;
        }

        try {
            entityManager.createNativeQuery(q)
                    .setParameter(1,record.getEmail())
                    .setParameter(2,record.getCname())
                    .setParameter(3,record.getDisease_code())
                    .setParameter(4,record.getTotal_deaths())
                    .setParameter(5,record.getTotal_patients())
                    .executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public Record getRecord(String email, String cname, String disease_code) {
        RecordKey recordKey = new RecordKey(email, cname, disease_code);
        Record record= entityManager.find(Record.class,recordKey);
        return record;
    }

    @Transactional
    public void deleteRecord(String email, String cname, String disease_code) {
        Query query = entityManager.createQuery("delete from Record " +
                "where email = " + email + " and cname = " + cname + " and disease_code = " + disease_code);
        query.executeUpdate();
    }
}
