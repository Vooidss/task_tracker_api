package may.code.task.tracker.api.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tassk_state")
@FieldDefaults( level = AccessLevel.PRIVATE)
public class TaskStateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(unique = true)
    String name;

    Long ordinal;

    @Builder.Default
    Instant updateAt = Instant.now();

    @Builder.Default
    Instant createdAt = Instant.now();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn (name = "task_state_id", referencedColumnName = "id")
    List<TaskStateEntity> tasksStates = new ArrayList<>();
}
