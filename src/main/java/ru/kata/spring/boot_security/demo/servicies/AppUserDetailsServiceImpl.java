package ru.kata.spring.boot_security.demo.servicies;

import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;
import ru.kata.spring.boot_security.demo.security.AppUserDetails;

@Service
public class AppUserDetailsServiceImpl implements AppUserDetailsService {

    private final UsersRepository usersRepository;

    public AppUserDetailsServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User findByEmail(String email) {
        return usersRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User '%s' not found", email)));
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        AppUserDetails userDetails = new AppUserDetails(findByEmail(email));
        Hibernate.initialize(userDetails.getAuthorities());
        return userDetails;
    }
}
