package org.example.repo;

import org.example.entity.CronValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CronValuesRepository extends JpaRepository<CronValues, String> {
@Query("select t from CronValues t where t.type = :type")
    CronValues findByType(String type);
}
