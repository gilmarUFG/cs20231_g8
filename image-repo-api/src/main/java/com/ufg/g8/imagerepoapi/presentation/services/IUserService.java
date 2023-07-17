package com.ufg.g8.imagerepoapi.presentation.services;

import com.ufg.g8.imagerepoapi.domain.models.User;
import com.ufg.g8.imagerepoapi.presentation.dtos.CredentialsDto;
import com.ufg.g8.imagerepoapi.presentation.dtos.TokenDto;
import com.ufg.g8.imagerepoapi.presentation.dtos.UserDto;
import org.bson.types.ObjectId;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    TokenDto login(Authentication authentication);

    void create(UserDto userDto);

    UserDto read(ObjectId id) throws Exception;

    void update(ObjectId id, UserDto userDto) throws Exception;

    void delete(ObjectId id);
}
