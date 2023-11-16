package may.code.task.tracker.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.sql.Insert;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskDto {

    Long id;

    String name;

    @JsonProperty("created_At")
    Instant createdAt = Instant.now();

    @NonNull
    @JsonProperty("update_at")
    Instant updateAt;

    String description;

}
