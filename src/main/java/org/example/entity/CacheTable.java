package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="CACHE_TABLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CacheTable {

    @Id
    private String queryname;

    private String queryvalue;

    private String programname;
}
