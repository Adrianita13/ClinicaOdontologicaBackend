package com.dh.clinica.login;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
public class AppUser implements UserDetails {

    public Long getId() {
        return id;
    }

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName= "user_sequence", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private String nombre;
    private String email;

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String getUsername() {
        return null;
    }


    private String username;
    @Enumerated(EnumType.STRING)
    private AppUserRoles appUserRoles;

    public AppUserRoles getAppUserRoles() {
        return appUserRoles;
    }

    public void setAppUserRoles(AppUserRoles appUserRoles) {
        this.appUserRoles = appUserRoles;
    }

  public AppUser(String nombre, String username, String email, String password, AppUserRoles appUserRoles) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.username = username;
        this.appUserRoles = appUserRoles;
    }

    public AppUser() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(appUserRoles.name());
        return Collections.singletonList(grantedAuthority);
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
