package com.Task1.OodlesA1.Repository;

import com.Task1.OodlesA1.Domain.Department;
import com.Task1.OodlesA1.Enums.DepartmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.lang.model.type.DeclaredType;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    public Department findByDepartmentType(DepartmentType departmentType);

}
