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
@NoArgsConstructor
@Builder
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


    /*If @Data ->
     @EqualsAndHashCode.Exclude
     @ToString.Exclude*/
    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY)
    List<TaskStateEntity> taskStates = new ArrayList<>();

}
