package com.educavalieri.services;

import com.educavalieri.dtos.CreateUserDto;
import com.educavalieri.dtos.UserDto;
import com.educavalieri.entities.User;
import com.educavalieri.repositories.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {

    @Inject
    private UserRepository userRepository;

    public CreateUserDto save(CreateUserDto userDto){
        User user = new User();
        user.setAge(userDto.getAge());
        user.setName(userDto.getName());
        userRepository.persist(user);
        return userDto;
    }

    public List<UserDto> findAll() {
        PanacheQuery<User> users = userRepository.findAll();
        List<UserDto> dtos = users.stream().map(x -> new UserDto(x.getId(), x.getName(), x.getAge())).collect(Collectors.toList());
        return dtos;
    }

    public UserDto findById(Long id){
        User entity = null;
        try {
            entity = userRepository.findById(id);
        } catch (RuntimeException e) {
            throw new EntityNotFoundException("Entity not found");
        }
        UserDto userDto = new UserDto();
        userDto.setAge(entity.getAge());
        userDto.setName(entity.getName());
        userDto.setId(entity.getId());
        return userDto;
    }

    public void delete(Long id){
        try {
            User user = userRepository.findById(id);
        } catch (RuntimeException e) {
            throw new EntityNotFoundException("entity not found");
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public void update(UserDto dto){
        User user = userRepository.findById(dto.getId());
        if(user == null){
            throw new EntityNotFoundException("entity not found");
        }

        user.setName(dto.getName());
        user.setAge(dto.getAge());
    }

}
