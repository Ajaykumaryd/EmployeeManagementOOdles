package com.Task1.OodlesA1.Repository;

import com.Task1.OodlesA1.Domain.Department;
import com.Task1.OodlesA1.Domain.Employee;
import com.Task1.OodlesA1.Enums.DepartmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    public Employee findByDepartmentType(DepartmentType departmentType);

    @Query(value = "select * from employee where age>= :value",nativeQuery = true)
    List<Employee> findEmployeeWithAgeGreater(Integer value);
}
