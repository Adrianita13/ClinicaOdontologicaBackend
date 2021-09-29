//package com.dh.clinica.login;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import java.util.Collection;
//import java.util.Collections;
//
//@Entity
//public class AppUser implements UserDetails {
//
//    public Long getId() {
//        return id;
//    }
//
//    @Id
//    @SequenceGenerator(name = "user_sequence", sequenceName= "user_sequence", allocationSize = 1)
//    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "user_sequence")
//    private Long id;
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public AppUserRoles getUsuarioRoles() {
//        return usuarioRoles;
//    }
//
//    public void setUsuarioRoles(AppUserRoles usuarioRoles) {
//        this.usuarioRoles = usuarioRoles;
//    }
//
//    private String nombre;
//    private String email;
//    private String password;
//    private String userName;
//    @Enumerated(EnumType.STRING)
//    private AppUserRoles usuarioRoles;
//
//    public AppUser(String nombre, String email, String password, String userName, AppUserRoles usuarioRoles) {
//        this.nombre = nombre;
//        this.email = email;
//        this.password = password;
//        this.userName = userName;
//        this.usuarioRoles = usuarioRoles;
//    }
//
//    public AppUser() {
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuarioRoles.name());
//        return Collections.singletonList(grantedAuthority);
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}
