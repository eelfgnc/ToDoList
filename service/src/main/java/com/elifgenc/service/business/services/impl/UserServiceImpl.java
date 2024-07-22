package com.elifgenc.service.business.services.impl;

import com.elifgenc.service.bean.ModelMapperBean;
import com.elifgenc.service.constant.ErrorMessage;
import com.elifgenc.service.business.dto.CreateUserDTO;
import com.elifgenc.service.business.dto.UserDTO;
import com.elifgenc.service.data.entity.User;
import com.elifgenc.service.exception.ObjectNotFoundException;
import com.elifgenc.service.data.repository.UserRepository;
import com.elifgenc.service.business.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Log4j2
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapperBean modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO findById(Long id) {
        return modelMapper.GetModelMapper().map(userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(ErrorMessage.USER_NOT_FOUND)), UserDTO.class);
    }

    @Override
    public UserDTO createUser(CreateUserDTO createUserDTO) {
        User user = modelMapper.GetModelMapper().map(createUserDTO, User.class);
        String encodedPassword = passwordEncoder.encode(createUserDTO.getPass());
        user.setPassword(encodedPassword);
        User createdUser =  userRepository.save(user);
        return modelMapper.GetModelMapper().map(createdUser, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(Long id, CreateUserDTO createUserDTO){
        UserDTO userDTO = findById(id);
        User user = modelMapper.GetModelMapper().map(userDTO, User.class);
        ModelMapper modelMapperBean = modelMapper.GetModelMapper();

        TypeMap<CreateUserDTO, User> typeMap = modelMapperBean.getTypeMap(CreateUserDTO.class, User.class);
        if (typeMap == null) {
            typeMap = modelMapperBean.createTypeMap(CreateUserDTO.class, User.class);
        }
        Provider<User> provider = p -> user;
        typeMap.setProvider(provider);

        User modifyUser = modelMapperBean.map(createUserDTO, User.class);
        User updateUser =  userRepository.save(modifyUser);
        return modelMapper.GetModelMapper().map(updateUser, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id)
    {
        userRepository.deleteById(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
