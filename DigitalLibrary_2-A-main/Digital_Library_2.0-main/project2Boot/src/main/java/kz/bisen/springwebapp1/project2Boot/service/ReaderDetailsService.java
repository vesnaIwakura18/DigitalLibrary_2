package kz.bisen.springwebapp1.project2Boot.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface ReaderDetailsService {
    UserDetails loadUserByUsername(String username);
}
