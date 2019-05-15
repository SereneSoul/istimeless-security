package com.istimeless.securitydemo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.istimeless.securitydemo.dto.FileInfo;
import com.istimeless.securitydemo.dto.User;
import com.istimeless.securitydemo.dto.UserQueryCondition;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lijiayin
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(UserQueryCondition userQueryCondition, 
                            @PageableDefault(page = 1, size = 20, sort = "username.asc") Pageable pageable){
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());
        List<User> list = new ArrayList<>();
        list.add(User.builder().username("tom").password("123456").build());
        list.add(new User());
        list.add(new User());
        return list;
    }
    
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable(name = "id") String id){
        System.out.println("进入getInfo");
//        throw new RuntimeException("ex");
        return User.builder()
                .username("tom")
                .password("123456")
                .build();
    }
    
    @PostMapping
    @JsonView(User.UserSimpleView.class)
    public User create(@Valid @RequestBody User user, BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @PutMapping
    @JsonView(User.UserSimpleView.class)
    public User update(@Valid @RequestBody User user, BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().forEach(error -> {
                FieldError fieldError = (FieldError) error; 
                System.out.println(fieldError.getField() + ":" + error.getDefaultMessage());
            });
        }
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable("id") String id){
        System.out.println(id);
    }

    @PostMapping("/file")
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
           
        String path = "E:\\Dream\\istimeless-security\\security-demo\\src\\main\\resources\\file";
        File localFile = new File(path, System.currentTimeMillis() + ".txt");
        
        file.transferTo(localFile);
        return FileInfo.builder()
                .path(localFile.getAbsolutePath())
                .build();
    }

    @GetMapping("/file/{id}")
    public void download(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = "E:\\Dream\\istimeless-security\\security-demo\\src\\main\\resources\\file";
        try (InputStream inputStream = new FileInputStream(new File(path, id + ".txt"));
             OutputStream outputStream = response.getOutputStream()){
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }
}
