package com.ufg.g8.imagerepoapi.domain.services;

import com.ufg.g8.imagerepoapi.domain.models.File;
import com.ufg.g8.imagerepoapi.domain.models.User;
import com.ufg.g8.imagerepoapi.domain.repositories.FileRepository;
import com.ufg.g8.imagerepoapi.domain.repositories.UserRepository;
import com.ufg.g8.imagerepoapi.infrastructure.exceptions.NotFoundException;
import com.ufg.g8.imagerepoapi.presentation.dtos.UserDto;
import com.ufg.g8.imagerepoapi.presentation.services.IUserService;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private static final String USER_NOT_FOUND = "Usuário não encontrado";

    private static final String FILE_NOT_FOUND = "Arquivo não encontrado";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileRepository fileRepository;

    public void create(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        if(userDto.getProfilePictureId() != null) {
            File file = this.fileRepository.findById(userDto.getProfilePictureId())
                    .orElseThrow(() -> new NotFoundException(FILE_NOT_FOUND));
            user.setProfilePicture(file);
        }
        this.userRepository.save(user);
    }

    public UserDto read(ObjectId id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto,
                "profilePicture.timestamp",
                "profilePicture.date"
        );
        userDto.setId(id.toString());
        File file = user.getProfilePicture();
        if(file != null)
            userDto.setProfilePictureId(file.getId());
        userDto.setPassword("");
        return userDto;
    }

    public void update(ObjectId id, UserDto userDto) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        BeanUtils.copyProperties(userDto, user, "id");
        if(userDto.getProfilePictureId() != null && userDto.getProfilePictureId() != user.getProfilePicture().getId()) {
            File file = this.fileRepository.findById(userDto.getProfilePictureId())
                    .orElseThrow(() -> new NotFoundException(FILE_NOT_FOUND));
            user.setProfilePicture(file);
        }
        this.userRepository.save(user);
    }

    public void delete(ObjectId id) {
        this.userRepository.deleteById(id);
    }

}
