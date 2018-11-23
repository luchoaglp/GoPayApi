package ar.com.gopay.service;

import ar.com.gopay.domain.Company;
import ar.com.gopay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Company getCompanyById(Long id) {
        return (Company) userRepository.findById(id).get();
    }

}
