package org.example.repo;

import org.example.entity.SchedulerTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerTestRepository extends JpaRepository<SchedulerTest, Long> {
    @Query("select s from SchedulerTest s where s.id=(select max(id) from SchedulerTest)")
    SchedulerTest getMaxIdRow();

}
