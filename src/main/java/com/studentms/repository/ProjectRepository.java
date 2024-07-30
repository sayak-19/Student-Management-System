package com.studentms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentms.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
