package may.code.task.tracker.api.contollers;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import may.code.task.tracker.api.dto.ProjectDto;
import may.code.task.tracker.api.exeptions.BadRequestException;
import may.code.task.tracker.api.factories.ProjectDtoFactory;
import may.code.task.tracker.api.store.repositories.ProjectRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
    Project Conroller

    Post - запуск логики
    Get - получение какой-то информации
    Put - замена / изменение объекта
    Patch - обновленгие объекта
    Delete - удаленеие объекта

 */

@RequiredArgsConstructor
@Transactional
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProjectController {

    ProjectRepository projectRepository;

    ProjectDtoFactory projectDtoFactory;

    public static final String CREATE_PROJECTS = "/api/projects/";

    @PostMapping(CREATE_PROJECTS)
    public ProjectDto createProject( @RequestParam String name ){

        projectRepository
                .findByName(name)
                       .ifPresent(project ->{
                        throw new BadRequestException(String.format("Project \"s\" already exists.",name));
                });

        //TODO: uncomment and insert entity in method
//        return new ProjectDtoFactory().makeProjectDto();
        return null;
    }

}
