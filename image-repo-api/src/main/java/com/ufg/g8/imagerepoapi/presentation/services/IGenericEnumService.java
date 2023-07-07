package com.ufg.g8.imagerepoapi.presentation.services;

import com.ufg.g8.imagerepoapi.presentation.dtos.EnumDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IGenericEnumService {

    List<EnumDto> loadEnum(String enumType);

}
