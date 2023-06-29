package com.ufg.g8.imagerepoapi.domain.services;

import com.ufg.g8.imagerepoapi.domain.models.MediaFile;
import com.ufg.g8.imagerepoapi.domain.models.User;
import com.ufg.g8.imagerepoapi.domain.repositories.MediaFileRepository;
import com.ufg.g8.imagerepoapi.domain.repositories.UserRepository;
import com.ufg.g8.imagerepoapi.infrastructure.exceptions.ActionNotAllowedException;
import com.ufg.g8.imagerepoapi.infrastructure.exceptions.DuplicateKeyException;
import com.ufg.g8.imagerepoapi.infrastructure.exceptions.NotFoundException;
import com.ufg.g8.imagerepoapi.infrastructure.security.JwtAuthenticationProvider;
import com.ufg.g8.imagerepoapi.infrastructure.utils.EncryptUtils;
import com.ufg.g8.imagerepoapi.infrastructure.utils.mapper.AppModelMapper;
import com.ufg.g8.imagerepoapi.presentation.dtos.CredentialsDto;
import com.ufg.g8.imagerepoapi.presentation.dtos.TokenDto;
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
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MediaFileRepository mediaFileRepository;

    public TokenDto login(CredentialsDto credentials) {
        User user = userRepository.findByLogin(credentials.getLogin());
        if(user == null)
            throw new ActionNotAllowedException("Usuário inválido ou não existe");
        if(!EncryptUtils.matchTextWithEncoded(credentials.getPassword(), user.getPassword()))
            throw new ActionNotAllowedException("Senha inválida");
        return new TokenDto(jwtAuthenticationProvider.generateToken(credentials.getLogin()));
    }

    public void create(UserDto userDto) {
        User user = new User();
        this.verifyLogin(userDto.getLogin());
        BeanUtils.copyProperties(userDto, user);
        if(userDto.getProfilePictureId() != null) {
            MediaFile mediaFile = this.mediaFileRepository.findById(userDto.getProfilePictureId())
                    .orElseThrow(() -> new NotFoundException(FILE_NOT_FOUND));
            user.setProfilePicture(mediaFile);
        }
        user.setPassword(EncryptUtils.encode(user.getPassword()));
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
        MediaFile mediaFile = user.getProfilePicture();
        if(mediaFile != null)
            userDto.setProfilePictureId(mediaFile.getId());
        userDto.setPassword("");
        userDto.setReports(
                user.getReports().stream()
                        .map(AppModelMapper::mapModelToDto)
                        .toList()
        );
        return userDto;
    }

    public void update(ObjectId id, UserDto userDto) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        if(!userDto.getLogin().equalsIgnoreCase(user.getLogin()))
            this.verifyLogin(userDto.getLogin());
        BeanUtils.copyProperties(userDto, user,
                "id",
                "password",
                "profilePicture",
                "updatedAt",
                "createdAt"
        );
        if(userDto.getProfilePictureId() != null && (user.getProfilePicture() == null || userDto.getProfilePictureId() != user.getProfilePicture().getId())) {
            MediaFile mediaFile = this.mediaFileRepository.findById(userDto.getProfilePictureId())
                    .orElseThrow(() -> new NotFoundException(FILE_NOT_FOUND));
            user.setProfilePicture(mediaFile);
        }
        this.userRepository.save(user);
    }

    public void delete(ObjectId id) {
        this.userRepository.deleteById(id);
    }

    private void verifyLogin(String login) {
        if(userRepository.existsByLogin(login))
            throw new DuplicateKeyException("O Login " + login + " já está em uso");
    }

}
