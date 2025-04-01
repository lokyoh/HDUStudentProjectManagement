package com.lokyoh.hduspm.controller.StudentController;

import com.lokyoh.hduspm.entity.Result;
import com.lokyoh.hduspm.entity.Student;
import com.lokyoh.hduspm.service.AccountService;
import com.lokyoh.hduspm.service.StudentService;
import com.lokyoh.hduspm.utils.ThreadLocalUtil;
import com.lokyoh.hduspm.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/student")
@Component
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/info")
    public Result<Object> getInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        long id = Long.parseLong(map.get("id").toString());
        Student student = studentService.info(id);
        student.setPassword(null);
        return Result.success(student);
    }

    @PostMapping("/info/change")
    public Result<Object> changeInfo(@RequestBody() Student student, @RequestParam(required = false) String p) {
        Map<String, Object> map = ThreadLocalUtil.get();
        long id = Long.parseLong(map.get("id").toString());
        Student o_s = studentService.info(id);
        String password = student.getPassword();
        if (password!=null && !password.isEmpty()){
            if (p==null || p.isEmpty()) return Result.error("请输入原密码");
            if (!password.equals(o_s.getPassword())){
                if(!ValidateUtil.checkPassword(password)) return Result.error("密码不符合规范");
            }
            if (!accountService.checkPassword(id, p)) return Result.error("密码错误");
        }
        String email = student.getEmail();
        if (email!=null && !email.isEmpty() && (!email.equals(o_s.getEmail()))){
            if(!ValidateUtil.checkEmail(email)) return Result.error("邮箱不符合规范");
        }
        student.setAccountId(id);
        studentService.changeInfo(student);
        return Result.success();
    }
}
