package org.example.repo;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.CacheTable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.BatchUpdateException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
@Slf4j
@Repository
public class CacheRepository {

    private JdbcTemplate jdbcTemplate;

    public CacheRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int[] batchInsert(List<CacheTable> books) {
try {
    return this.jdbcTemplate.batchUpdate(
            "insert into Cache_Table (queryname, queryvalue, programname) values(?,?,?)",
            new BatchPreparedStatementSetter() {

                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, books.get(i).getQueryname());
                    ps.setString(2, books.get(i).getQueryvalue());
                    ps.setString(3, books.get(i).getProgramname());
                }

                public int getBatchSize() {
                    return books.size();
                }

            });
}
catch(DataAccessException e){
    if(e.getCause() instanceof BatchUpdateException){
        BatchUpdateException ex = (BatchUpdateException) e.getCause();
        int[] count = ex.getUpdateCounts();
        for (int j =0; j < count.length; j++){
            if(count[j]== Statement.EXECUTE_FAILED) {
log.error("Batch failed in Query name - "+books.get(j).getQueryname());
            }
        }
    }
}
        return new int[0];
    }
}
