package com.gmail.antonovich.tonya.morionkot.security;

import com.gmail.antonovich.tonya.morionkot.repository.AdminRepository;
import com.gmail.antonovich.tonya.morionkot.entity.Admin;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AdminRepository adminRepository;

    public UserDetailsServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("no user with such login not found"));
        return new User(admin.getLogin(), admin.getPassword(), emptyList());
    }
}
