package com.studentms.service;

import java.util.List;
import java.util.Optional;

import com.studentms.exception.EmptyInputException;
import com.studentms.exception.ProjectNotFoundException;
import com.studentms.model.Project;
import com.studentms.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class ProjectService {

	private final ProjectRepository projectRepository;

	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	public Project saveProject(@Valid Project project) {
		return projectRepository.save(project);
	}

	public void deleteProjectById(Long id) {
		if (id == 0) {
			throw new EmptyInputException("You need to provide ID of project to be deleted. ID can not be 0.");
		}
		Optional<Project> checkIfProjecttWithIdExist = projectRepository.findById(id);
		if (checkIfProjecttWithIdExist.isEmpty()) {
			throw new ProjectNotFoundException(
					"Student can not be deleted because project with id: " + id + " does not exist.");
		}
		projectRepository.deleteById(id);
	}

}
