package kz.bisen.springwebapp1.project2Boot.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface ReaderDetailsService {
    UserDetails loadUserByUsername(String username);
}
