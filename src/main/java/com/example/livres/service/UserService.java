package com.example.livres.service;

import com.example.livres.entities.Role;
import com.example.livres.entities.User;

public interface UserService {
User saveUser(User user);
User updateUser(User user);
void deleteUserById(Long id);
User getUserById(Long id);
User findUserByUsername (String username);
Role addRole(Role role);
User addRoleToUser(String username, String rolename);

User ChangePassword(String oldPass ,String newPass, Long id);
}