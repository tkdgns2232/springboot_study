package com.korit.springboot_study.security.principal;

import com.korit.springboot_study.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class PrincipalUser implements UserDetails {
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // GrantedAuthority의 자식을 쓸 수 있다.
        return user.getUserRoles()
                .stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getRoleName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 사용 기간 만료 여부
        return user.getIsAccountNonExpired() == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금 여부
        return user.getIsAccountNonLocked() == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 계정 인가 여부
        return user.getIsCredentialsNonExpired() == 1;
    }

    @Override
    public boolean isEnabled() {
        // 계정 활성 여부
        return user.getIsEnabled() == 1;
    }
}
