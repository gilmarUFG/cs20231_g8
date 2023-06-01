package com.ufg.g8.imagerepoapi.domain.services;

import com.ufg.g8.imagerepoapi.domain.models.File;
import com.ufg.g8.imagerepoapi.domain.models.User;
import com.ufg.g8.imagerepoapi.domain.repositories.FileRepository;
import com.ufg.g8.imagerepoapi.domain.repositories.UserRepository;
import com.ufg.g8.imagerepoapi.presentation.dtos.UserDto;
import com.ufg.g8.imagerepoapi.presentation.services.IUserService;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private static final String MENSAGEM = "Arquivo nao encontrado";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileRepository fileRepository;

    public void create(UserDto userDto) {
        try {
            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            if(userDto.getProfilePictureId() != null) {
                File file = this.fileRepository.findById(userDto.getProfilePictureId())
                        .orElseThrow(() -> new Exception(MENSAGEM));
                user.setProfilePicture(file);
            }
            this.userRepository.save(user);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public UserDto read(ObjectId id) throws Exception {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new Exception(MENSAGEM));
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto, "profilePicture.timestamp", "profilePicture.date");
        userDto.setId(id.toString()); // Converte o ObjectId para string
        File file = user.getProfilePicture();
        if(file != null)
            userDto.setProfilePictureId(file.getId());
        userDto.setPassword("");
        return userDto;
    }

    public void update(ObjectId id, UserDto userDto) throws Exception {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new Exception(MENSAGEM));
        BeanUtils.copyProperties(userDto, user, "id");
        if(userDto.getProfilePictureId() != null && userDto.getProfilePictureId() != user.getProfilePicture().getId()) {
            File file = this.fileRepository.findById(userDto.getProfilePictureId())
                    .orElseThrow(() -> new Exception(MENSAGEM));
            user.setProfilePicture(file);
        }
        this.userRepository.save(user);
    }

    public void delete(ObjectId id) {
        this.userRepository.deleteById(id);
    }

}
