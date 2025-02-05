package com.korit.springboot_study.repository;

import com.korit.springboot_study.entity.study.Instructor;
import com.korit.springboot_study.entity.study.Major;
import com.korit.springboot_study.exception.CustomDuplicateKeyException;
import com.korit.springboot_study.mapper.StudentStudyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class StudentStudyRepository {

    @Autowired
    private StudentStudyMapper studentStudyMapper;

    public Optional<List<Major>> findMajorAll() {
        List<Major> foundMajors = studentStudyMapper.selectMajorsAll();

        if(foundMajors.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(foundMajors);
        // Nullable은 Null까지 허용 3항연산자로도 가능
    }

    public Optional<List<Instructor>> findInstructorAll() {
        List<Instructor> foundInstructors = studentStudyMapper.selectInstructorsAll();

        if(foundInstructors.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(foundInstructors);
    }
    // 학과명 추가할때 필요한 코드
    public Optional<Major> saveMajor(Major major) throws DuplicateKeyException { // DuplicateKeyException = 중복
        try {
            studentStudyMapper.insertMajor(major);
        } catch (DuplicateKeyException e) {
            throw new CustomDuplicateKeyException(
                    e.getMessage(),
                    Map.of("majorName", "이미 존재하는 학과명입니다.")
            );
        }

        return Optional.ofNullable(new Major(major.getMajorId(), major.getMajorName()));
    }
    // 교수명 추가할 때 필요한 코드
    public Optional<Instructor> saveInstructor(Instructor instructor) throws DuplicateKeyException {
        try {
            studentStudyMapper.insertInstructor(instructor);
        } catch (DuplicateKeyException e) {
            throw new CustomDuplicateKeyException(
                    e.getMessage(),
                    Map.of("instructorName", "이미 존재하는 교수명입니다.")
            );
        }
        return Optional.ofNullable(new Instructor(instructor.getInstructorId(), instructor.getInstructorName()));


    }
}
