package may.code.task.tracker.api.factories;

import may.code.task.tracker.api.dto.ProjectDto;
import may.code.task.tracker.api.dto.TaskStateDto;
import may.code.task.tracker.api.store.entities.ProjectEntity;
import may.code.task.tracker.api.store.entities.TaskStateEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskStaetDtoFactory {

    public TaskStateDto makeProject(TaskStateEntity entity) {

        return TaskStateDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updateAt(entity.getUpdateAt())
                .build();

    }

}
