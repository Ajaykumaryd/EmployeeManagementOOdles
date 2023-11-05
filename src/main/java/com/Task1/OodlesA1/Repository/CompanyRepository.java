package com.Task1.OodlesA1.Repository;

import com.Task1.OodlesA1.Domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {

}
