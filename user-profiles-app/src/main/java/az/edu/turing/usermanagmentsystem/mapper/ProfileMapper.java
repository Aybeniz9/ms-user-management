package az.edu.turing.usermanagmentsystem.mapper;

import az.edu.turing.usermanagmentsystem.model.dto.ProfileDto;
import az.edu.turing.usermanagmentsystem.model.entity.ProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")

public interface ProfileMapper {
    ProfileMapper INSTANCE= Mappers.getMapper(ProfileMapper.class);
    List<ProfileDto> entityListToDtoList(List<ProfileEntity> profileEntity);
    ProfileDto entityToDto(ProfileEntity profileEntity);
    List<ProfileEntity >dtoListToEntityList(List<ProfileDto> profileDtoList);
    ProfileEntity dtoToEntity(ProfileDto profileDto);

}
