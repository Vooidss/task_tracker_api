package may.code.task.tracker.api.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter //( @Data )
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project")
@FieldDefaults ( level = AccessLevel.PRIVATE)
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(unique = true) // Поле должно быть уникальным
    String name;

    @Builder.Default
    Instant createdAt = Instant.now();

    @Builder.Default
    Instant updateAt = Instant.now();


    /*If @Data ->
     @EqualsAndHashCode.Exclude     @ToString.Exclude*/
    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id",referencedColumnName = "id")
    List<TaskStateEntity> taskStates = new ArrayList<>();

}
