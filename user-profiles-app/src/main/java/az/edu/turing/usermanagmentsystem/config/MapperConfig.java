package az.edu.turing.usermanagmentsystem.config;

import az.edu.turing.usermanagmentsystem.mapper.ProfileMapper;
import az.edu.turing.usermanagmentsystem.mapper.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

public class MapperConfig {
    @Bean
    public UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }
    @Bean
    public ProfileMapper profileMapper() {
        return Mappers.getMapper(ProfileMapper.class);
    }
}
