package com.lokyoh.hduspm.service.impl;

import com.lokyoh.hduspm.entity.Student;
import com.lokyoh.hduspm.mapper.StudentMapper;
import com.lokyoh.hduspm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;


    /**
     * @param id
     * @return
     */
    @Override
    public Student info(long id) {
        return studentMapper.info(id);
    }

    /**
     * @param student
     */
    @Override
    public void changeInfo(Student student) {
        studentMapper.changeInfo(student);
    }
}
