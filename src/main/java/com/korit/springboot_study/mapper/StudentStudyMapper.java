package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.study.Instructor;
import com.korit.springboot_study.entity.study.Major;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper // mapper는 인터페이스로 만듦
public interface StudentStudyMapper {

    List<Major> selectMajorsAll();
    List<Instructor> selectInstructorsAll();

    int insertMajor(Major major);
    int insertInstructor(Instructor instructor);
    int updateMajor(Major major);
}
