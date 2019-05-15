package ar.com.gopay.service;

import ar.com.gopay.domain.Company;
import ar.com.gopay.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company getById(Long id) {
        return (Company) companyRepository.findById(id).get();
    }

}
