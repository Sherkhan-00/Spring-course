
package org.example.service;

import org.example.database.repository.CompanyRepository;
import org.example.database.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final UserService userService;

    public CompanyService(UserService userService) {
        this.userService = userService;
    }
}
