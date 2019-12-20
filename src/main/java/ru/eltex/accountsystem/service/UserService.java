package ru.eltex.accountsystem.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.eltex.accountsystem.model.UserRole;
import ru.eltex.accountsystem.repository.AllUserRepository;

@Service
public class UserService implements UserDetailsService {
    private final AllUserRepository allUserRepository;

    @Autowired
    public UserService(AllUserRepository allUserRepository) {
        this.allUserRepository = allUserRepository;
    }

    public UserRole getUserRole(String username) {
        return allUserRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return allUserRepository.findByUsername(username);
    }
}
