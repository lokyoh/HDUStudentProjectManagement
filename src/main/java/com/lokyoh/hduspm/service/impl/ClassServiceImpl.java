package com.lokyoh.hduspm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lokyoh.hduspm.entity.Classes;
import com.lokyoh.hduspm.entity.ClassesInfo;
import com.lokyoh.hduspm.entity.Member;
import com.lokyoh.hduspm.entity.PageBean;
import com.lokyoh.hduspm.mapper.ClassMapper;
import com.lokyoh.hduspm.mapper.ProjectMapper;
import com.lokyoh.hduspm.mapper.UserMapper;
import com.lokyoh.hduspm.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProjectMapper projectMapper;

    /**
     * @param pageNum
     * @param pageSize
     * @param id
     * @return
     */
    @Override
    public PageBean<Classes> list(Integer pageNum, Integer pageSize, Long id, String role) {
        PageBean<Classes> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Classes> rs;
        switch (role) {
            case "student":
                rs = classMapper.s_list(id);
                break;
            case "teacher":
                rs = classMapper.t_list(id);
                break;
            case "admin":
                rs = classMapper.list();
                break;
            default:
                return null;
        }
        PageInfo<Classes> p = new PageInfo<>(rs);
        pb.setCount(p.getTotal());
        pb.setItems(p.getList());
        return pb;
    }

    /**
     * @param id
     * @param uid
     * @return
     */
    @Override
    public ClassesInfo getClassInfo(Long id, Long uid) {
        Classes classes = classMapper.getClasses(id);
        if (classes != null) {
            ClassesInfo classesInfo = new ClassesInfo(classes);
            classesInfo.setProjects(projectMapper.getClassProjects(id));
            classesInfo.setMembers(classMapper.getMembers(id));
            String role = "visitor";
            if (uid != null)
                role = userMapper.getAccountById(uid).getRole();
            switch (role) {
                case "admin":
                    classesInfo.setRole("admin");
                    break;
                case "teacher":
                    if (userMapper.teacherInfo(uid).getId().equals(classes.getTeacherId()))
                        classesInfo.setRole("teacher");
                    else classesInfo.setRole("visitor");
                    break;
                case "student":
                    classesInfo.setRole("visitor");
                    for (Member member: classesInfo.getMembers()) {
                        if (member.getId().equals(userMapper.studentInfo(uid).getId())) {
                            classesInfo.setRole("student");
                            break;
                        }
                    }
                    break;
            }
            return classesInfo;
        }
        return null;
    }

    /**
     * @param cid
     * @param sid
     */
    @Override
    public void addStudent(Long cid, Long sid) {
        classMapper.addStudent(cid, sid);
    }
}
