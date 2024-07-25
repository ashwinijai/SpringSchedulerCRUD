package org.example.repo;

import org.example.entity.CacheTable;
import org.example.entity.CronValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CacheTableRepository extends JpaRepository<CacheTable, String> {
    @Query("select t from CacheTable t where t.programname = :program")
    List<CacheTable> findByProgramName(String program);
}
