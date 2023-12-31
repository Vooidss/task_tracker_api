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
@Table(name = "task")
@FieldDefaults( level = AccessLevel.PRIVATE)
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(unique = true)
    String name;

    String description;

    @Builder.Default
    Instant updateAt = Instant.now();

    @Builder.Default
    Instant createdAt = Instant.now();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name ="task_id",referencedColumnName = "id")
    List<TaskStateEntity> task = new ArrayList<>();
}
