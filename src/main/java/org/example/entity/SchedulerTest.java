package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="SCHEDULER_TEST")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchedulerTest {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHEDULER_TEST_SEQ_GEN")
        @SequenceGenerator(name = "SCHEDULER_TEST_SEQ_GEN", sequenceName = "SCHEDULER_TEST_SEQ",  allocationSize = 1)
        private Long id;

        private String name;

        private String comments;
}
