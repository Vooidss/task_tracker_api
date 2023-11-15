package may.code.task.tracker.api.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@Table(name = "project")
@FieldDefaults( level = AccessLevel.PRIVATE)
public class TaskStateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(unique = true)
    String name;

    @Builder.Default
    Instant createdAt = Instant.now();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY)
    List<TaskStateEntity> taskStates;
}
