package com.app.controllers;

import com.app.repositories.UserRepository;
import com.app.models.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public UserEntity create(@Valid @RequestBody UserEntity user) {
        user.set_id(ObjectId.get());
        userRepository.save(user);
        return user;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyById(@PathVariable("id") ObjectId id, @Valid @RequestBody UserEntity user) {
        user.set_id(id);
        userRepository.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserEntity getById(@PathVariable("id") ObjectId id) {
        return userRepository.findBy_id(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable ObjectId id) {
        userRepository.delete(userRepository.findBy_id(id));
    }
}