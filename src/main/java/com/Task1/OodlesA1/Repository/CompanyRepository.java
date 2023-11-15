package com.Task1.OodlesA1.Repository;

import com.Task1.OodlesA1.Domain.Company;
import com.Task1.OodlesA1.Domain.Department;
import com.Task1.OodlesA1.Enums.DepartmentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {

      public Company findByCompanyName(String companyName);

}
