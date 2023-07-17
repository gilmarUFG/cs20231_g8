package com.ufg.g8.imagerepoapi.domain.services;

import com.ufg.g8.imagerepoapi.domain.models.MediaFile;
import com.ufg.g8.imagerepoapi.domain.models.User;
import com.ufg.g8.imagerepoapi.domain.repositories.MediaFileRepository;
import com.ufg.g8.imagerepoapi.domain.repositories.UserRepository;
import com.ufg.g8.imagerepoapi.infrastructure.exceptions.ActionNotAllowedException;
import com.ufg.g8.imagerepoapi.infrastructure.exceptions.DuplicateKeyException;
import com.ufg.g8.imagerepoapi.infrastructure.exceptions.NotFoundException;
import com.ufg.g8.imagerepoapi.infrastructure.utils.EncryptUtils;
import com.ufg.g8.imagerepoapi.infrastructure.utils.mapper.AppModelMapper;
import com.ufg.g8.imagerepoapi.presentation.dtos.TokenDto;
import com.ufg.g8.imagerepoapi.presentation.dtos.UserDto;
import com.ufg.g8.imagerepoapi.presentation.services.IUserService;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService, UserDetailsService {

    private static final String USER_NOT_FOUND = "Usuário não encontrado";

    private static final String FILE_NOT_FOUND = "Arquivo não encontrado";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MediaFileRepository mediaFileRepository;

    @Autowired
    private JwtEncoder jwtEncoder;

    public TokenDto login(Authentication authentication) {
        Optional.ofNullable(authentication).orElseThrow(
                () -> new ActionNotAllowedException("Autenticação não enviada")
        );
        User user = userRepository.findByLogin(authentication.getName())
                .orElseThrow(() -> new ActionNotAllowedException("Usuário inválido ou não existe"));
        return new TokenDto(this.generateToken(user));
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
            ObjectId toBeDeletedId = user.getProfilePicture().getId();
            user.setProfilePicture(mediaFile);
            this.mediaFileRepository.deleteById(toBeDeletedId);
        }
        this.userRepository.save(user);
    }

    public void delete(ObjectId id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .orElseThrow(() -> new ActionNotAllowedException("Usuário inválido ou não existe"));
    }

    private void verifyLogin(String login) {
        if(userRepository.existsByLogin(login))
            throw new DuplicateKeyException("O Login " + login + " já está em uso");
    }

    private String generateToken(User user) {
        Instant now = Instant.now();
        String scope = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(user.getLogin())
                .claim("scope", scope)
                .build();
        JwtEncoderParameters encoderParameters = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(),
                claims
        );
        return this.jwtEncoder.encode(encoderParameters).getTokenValue();
    }

}
