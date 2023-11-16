package may.code.task.tracker.api.factories;

import may.code.*;
import may.code.task.tracker.api.dto.TaskDto;
import may.code.task.tracker.api.store.entities.ProjectEntity;
import may.code.task.tracker.api.store.entities.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoFactory {

    public TaskDto makeProject(TaskEntity entity) {

        return TaskDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .updateAt(entity.getUpdateAt())
                .build();

    }

}
