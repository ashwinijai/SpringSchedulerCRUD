package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="CRON_VALUES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CronValues {

    @Id
    private String type;

    private String cron;
}
