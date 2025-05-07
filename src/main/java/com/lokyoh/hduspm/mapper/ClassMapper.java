package com.lokyoh.hduspm.mapper;

import com.lokyoh.hduspm.entity.Classes;
import com.lokyoh.hduspm.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper {
    List<Classes> s_list(Long id);

    Classes getClasses(Long id);

    List<Member> getMembers(Long id);

    List<Classes> t_list(Long id);

    List<Classes> list();
}
