package com.example.iq_test.services;

import com.example.iq_test.models.Role;
import com.example.iq_test.models.User;
import com.example.iq_test.repositories.RoleRepository;
import com.example.iq_test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserService  implements UserDetailsService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByName(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean saveUser(User user)
    {
        User userFromDB = userRepository.findByName(user.getUsername());
        if(userFromDB != null)
        {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public User findUserById(long idUser)
    {
        return userRepository.findById(idUser).orElseThrow();
    }

    public User findUserByName(String name)
    {
        return userRepository.findByName(name);
    }

    public void deleteUserById(long idUser)
    {
        userRepository.deleteById(idUser);
    }

    public Iterable<User> findAllUsers()
    {
        return userRepository.findAll();
    }

    public List<User> findUserAdmin()
    {
        return userRepository.findByRoles(roleRepository.findByName("ROLE_ADMIN"));
    }

    public List<User> findUserUsers()
    {
        return userRepository.findByRoles(roleRepository.findByName("ROLE_USER"));
    }

    public List<User> findUserTeacher()
    {
        //userRepository.findById(1L).get().getRoles().size();
        return userRepository.findByRoles(roleRepository.findByName("ROLE_TEACHER"));
    }

    public List<User> findUserChild()
    {
        return userRepository.findByRoles(roleRepository.findByName("ROLE_CHILD"));
    }

    public void setRoleTeacher(User user)
    {
        Set<Role> roles = user.getRoles();
        roles.add(roleRepository.findByName("ROLE_TEACHER"));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void setRoleChild(User user)
    {
        Set<Role> roles = user.getRoles();
        roles.add(roleRepository.findByName("ROLE_CHILD"));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void setRoleUserOnly(User user)
    {
        Set<Role> roles = user.getRoles();
        roles.clear();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public List<User> findUndefinedUsers()
    {
        List<User> users = userRepository.findByRoles(roleRepository.findByName("ROLE_USER"));
        users.removeAll(userRepository.findByRolesIn(roleRepository.findByNameOrName("ROLE_CHILD","ROLE_TEACHER")));
        return users;
    }


}
