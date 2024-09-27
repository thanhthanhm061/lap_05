package com.example.lap_05.controller;
import com.example.lap_05.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@Controller

public class UsersController {
    List<User> usersList = new ArrayList<>();
    public UsersController() {

        User u2 = new User("0000", "Nguyen Xuan Thanh", "b@donga.edu");

        usersList.add(u2);
    }
    //1. API get all users
    @GetMapping("/user")
    @ResponseBody
    public List<User> getUsersList() {
        return usersList;
    }
    //2. API get users by id
    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String userId) {
        // Không cần @ResponseBody vì ResponseEntity đã bao gồm body
        for (User user : usersList) {
            if (user.getId().equals(userId)) {
                return ResponseEntity.status(200).body(user);
            }
        }
        return ResponseEntity.status(404).body(null);  // Trả về lỗi 404 nếu không tìm thấy
    }
    //3. API delete user by id
    @DeleteMapping("user/{id}")
    @ResponseBody
    public List<User> deleteUser(@PathVariable("id") String userId) {
        for (User user : usersList) {
            if (user.getId().equals(userId)) {
                usersList.remove(user);
                break;
            }
        }
        return usersList;
    }
    //4. API POST new user
    // CREATE: Thêm một User mới (POST /users)
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User newuser) {
        usersList.add(newuser);
        return ResponseEntity.status(201).body(newuser);
    }


    //5. API PUT user by id
    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String userId, @RequestBody User updateUser) {
        for (User user : usersList) {
            if (user.getId().equals(userId)) {
                user.setName(updateUser.getName());
                user.setEmail(updateUser.getEmail());


                return ResponseEntity.status(200).body(user);// Trả về status 200 OK và user đã được cập nhật
            }
        }


        return ResponseEntity.status(404).body(null);
    }




}
