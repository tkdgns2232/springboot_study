package com.korit.springboot_study.util.db;

import com.korit.springboot_study.entity.Role;
import com.korit.springboot_study.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

// 애플리케이션 시작 시, "USER", "ADMIN", "MANAGER" 역할을 데이터베이스에 삽입하는 코드
@Component
public class AutoCreateDML implements CommandLineRunner {

    @Autowired
    private RoleMapper roleMapper;
    @Override // 서버실행할때마다 재실행되기 때문에 한번 사용하고 주석처리로 멈출 수 있다.
    public void run(String... args) throws Exception {
//        insertRole();
    }

    private void insertRole() {
        List<String> roleNames = List.of("USER", "ADMIN","MANAGER");
        roleMapper.insertAll(roleNames.stream()
                .map(name -> Role.builder()
                .roleName("ROLE_" + name).build())
                .collect(Collectors.toList())
        );
    }
}
