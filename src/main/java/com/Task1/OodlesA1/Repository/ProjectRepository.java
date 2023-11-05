package com.Task1.OodlesA1.Repository;

import com.Task1.OodlesA1.Domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
