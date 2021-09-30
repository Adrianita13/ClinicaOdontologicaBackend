package com.dh.clinica.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String password = passwordEncoder.encode("password");
        userRepository.save( new AppUser("Clara","Clari","clari@digital.com", password, AppUserRoles.ADMIN));
        userRepository.save( new AppUser("Diego","dfdiaza","diego@digital.com", password, AppUserRoles.ADMIN));

    }


}
