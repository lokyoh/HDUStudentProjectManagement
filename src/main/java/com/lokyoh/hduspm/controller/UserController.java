package com.lokyoh.hduspm.controller;

import com.lokyoh.hduspm.entity.*;
import com.lokyoh.hduspm.service.UserService;
import com.lokyoh.hduspm.utils.JwtUtil;
import com.lokyoh.hduspm.utils.ThreadLocalUtil;
import com.lokyoh.hduspm.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
@Component
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<Object> login(String username, String password) {
        Account account = userService.getAccountByName(username);
        if (account == null) {
            return Result.error("用户名错误");
        }
        if (account.getPassword().equals(password)) {
            String role = account.getRole();
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", account.getId());
            claims.put("role", role);
            String token = JwtUtil.getToken(claims);
            return Result.success(new LoginData(account, token));
        } else {
            return Result.error("密码错误");
        }
    }

    @PutMapping("/register")
    public Result<Object> register(@RequestBody StudentAccount student) {
        Account account = userService.getAccountByName(student.getStudentId());
        if (account != null)
            return Result.error("该账号已经注册");
        if (!ValidateUtil.checkPassword(student.getPassword()))
            return Result.error("密码不符合要求");
        if (!ValidateUtil.checkEmail(student.getEmail()))
            return Result.error("邮箱不符合要求");
        userService.addStudent(student);
        return Result.success();
    }

    @PutMapping("/admin/teacher/register")
    public Result<Object> tRegister(@RequestBody TeacherAccount teacher) {
        Account account = userService.getAccountByName(teacher.getTeacherId());
        if (account != null)
            return Result.error("该账号已经注册");
        if (!ValidateUtil.checkPassword(teacher.getPassword()))
            return Result.error("密码不符合要求");
        if (!ValidateUtil.checkEmail(teacher.getEmail()))
            return Result.error("邮箱不符合要求");
        userService.addTeacher(teacher);
        return Result.success();
    }

    @PostMapping("/tokenCheck")
    public Result<Object> tokenCheck(String token) {
        try{
            JwtUtil.parseToken(token);
            return Result.success();
        }catch (Exception e){
            return Result.error("token无效");
        }
    }

    @GetMapping("/student/info")
    public Result<Object> sGetInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        long id = Long.parseLong(map.get("id").toString());
        Student student = userService.sInfo(id);
        return Result.success(student);
    }

    @PostMapping("/student/info/change")
    public Result<Object> sChangeInfo(@RequestBody() Student student, @RequestParam(required = false) String p) {
        Map<String, Object> map = ThreadLocalUtil.get();
        long id = Long.parseLong(map.get("id").toString());
        Student o_s = userService.sInfo(id);
        String password = student.getPassword();
        if (password!=null && !password.isEmpty()){
            if (p==null || p.isEmpty()) return Result.error("请输入原密码");
            if (!password.equals(o_s.getPassword())){
                if(!ValidateUtil.checkPassword(password)) return Result.error("密码不符合规范");
            }
            if (!userService.checkPassword(id, p)) return Result.error("密码错误");
        }
        String email = student.getEmail();
        if (email!=null && !email.isEmpty() && (!email.equals(o_s.getEmail()))){
            if(!ValidateUtil.checkEmail(email)) return Result.error("邮箱不符合规范");
        }
        student.setAccountId(id);
        userService.sChangeInfo(student);
        return Result.success();
    }

    @GetMapping("/teacher/info")
    public Result<Object> tInfo(){
        Map<String, Object> map = ThreadLocalUtil.get();
        long id = Long.parseLong(map.get("id").toString());
        Teacher teacher = userService.tInfo(id);
        return Result.success(teacher);
    }

    @PostMapping("/teacher/info/change")
    public Result<Object> tChangeInfo(@RequestBody() Teacher teacher, @RequestParam(required = false) String p) {
        Map<String, Object> map = ThreadLocalUtil.get();
        long id = Long.parseLong(map.get("id").toString());
        Teacher o_s = userService.tInfo(id);
        String password = teacher.getPassword();
        if (password!=null && !password.isEmpty()){
            if (p==null || p.isEmpty()) return Result.error("请输入原密码");
            if (!password.equals(o_s.getPassword())){
                if(!ValidateUtil.checkPassword(password)) return Result.error("密码不符合规范");
            }
            if (!userService.checkPassword(id, p)) return Result.error("密码错误");
        }
        String email = teacher.getEmail();
        if (email!=null && !email.isEmpty() && (!email.equals(o_s.getEmail()))){
            if(!ValidateUtil.checkEmail(email)) return Result.error("邮箱不符合规范");
        }
        teacher.setAccountId(id);
        userService.tChangeInfo(teacher);
        return Result.success();
    }

    @PostMapping("/user/info")
    public Result<Object> uInfo(@RequestParam Long aid) {
        return Result.success(userService.getUserInfo(aid));
    }

    @PostMapping("/admin/user/del")
    public Result<Object> delUser(@RequestParam Long id) {
        userService.delAccount(id);
        return Result.success();
    }

    @GetMapping("/student/get")
    public Result<Object> studentGet(@RequestParam String studentId){
        return Result.success(userService.getStudent(studentId));
    }

    @GetMapping("/admin/user/get")
    public Result<Object> userGet(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize
    ){
        pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
        pageSize = pageSize == null || pageSize > 30 || pageSize < 1 ? 10 : pageSize;
        return Result.success(userService.getUsers(pageNum, pageSize));
    }

    @GetMapping("/user/teacher/param")
    public Result<Object> teacherParam(@RequestParam String teacher){
        return Result.success(userService.getTeachers(teacher));
    }

    @GetMapping("/user/student/param")
    public Result<Object> studentParam(@RequestParam String param){
        return Result.success(userService.getStudents(param));
    }
}
