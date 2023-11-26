package may.code.task.tracker.api.contollers;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import may.code.task.tracker.api.dto.ProjectDto;
import may.code.task.tracker.api.exeptions.BadRequestException;
import may.code.task.tracker.api.exeptions.NotFoundExeption;
import may.code.task.tracker.api.factories.ProjectDtoFactory;
import may.code.task.tracker.api.store.entities.ProjectEntity;
import may.code.task.tracker.api.store.repositories.ProjectRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public static final String CREATE_PROJECTS = "/api/projects";
    public static final String FETCH_PROJECTS = "/api/projects";
    public static final String EDIT_PROJECTS = "/api/projects/{project_id}";

    @PostMapping(FETCH_PROJECTS)
    public List<ProjectDto> fetchProject(
            @RequestParam(value = "prefix_name"
            ,required = false )Optional<String> optionalPrefixName) {

        optionalPrefixName = optionalPrefixName.filter(prefixName ->
                !prefixName
                        .trim()
                        .isEmpty());

        if (name.trim().isEmpty()){
            throw new BadRequestException("Name can`t be empty.");
        }


        projectRepository
                .findByName(name)
                .ifPresent(project -> {
                    throw new BadRequestException(String.format("Project \"s\" already exists.", name));
                });

        ProjectEntity project = projectRepository.saveAndFlush(
                ProjectEntity.builder()
                        .name(name)
                        .build()
        );

        return  projectDtoFactory.makeProjectDto(project);

    }

    @PostMapping(CREATE_PROJECTS)
    public ProjectDto createProject(
            @RequestParam String name) {

        if (name.trim().isEmpty()){
            throw new BadRequestException("Name can`t be empty.");
        }


        projectRepository
                .findByName(name)
                .ifPresent(project -> {
                    throw new BadRequestException(String.format("Project \"s\" already exists.", name));
                });

        ProjectEntity project = projectRepository.saveAndFlush(
                ProjectEntity.builder()
                        .name(name)
                        .build()
        );

        return  projectDtoFactory.makeProjectDto(project);

    }

        @PatchMapping(EDIT_PROJECTS)
        public ProjectDto editProject (
                @PathVariable("projectId") String projectId,
                @RequestParam String name){

            if (name.trim().isEmpty()){
                throw new BadRequestException("Name can`t be empty.");
            }


        ProjectEntity project = projectRepository
                .findByName(projectId)
                .orElseThrow(() ->
                                new NotFoundExeption
                                        (String.format
                                                ("Project with \"%s\" already exists",
                                                        projectId)
                                        )
                        );

            projectRepository
                    .findByName(name)
                    .filter(anotherProject -> !Objects.equals(anotherProject.getId(),projectId))
                    .ifPresent(anotherProject-> {
                        throw new BadRequestException(String.format("Project \"s\" already exists.", name));
                    });

            project = projectRepository.saveAndFlush(project);

            return  projectDtoFactory.makeProjectDto(project);
        }

}