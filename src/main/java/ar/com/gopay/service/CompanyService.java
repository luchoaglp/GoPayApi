package ar.com.gopay.service;

import ar.com.gopay.domain.Company;
import ar.com.gopay.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company getById(Long id) {
        return (Company) companyRepository.findById(id).get();
    }

}
