package may.code.task.tracker.api.store.repositories;

import may.code.task.tracker.api.store.entities.TaskStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStateRepository  extends JpaRepository<TaskStateEntity,Long> {

}
