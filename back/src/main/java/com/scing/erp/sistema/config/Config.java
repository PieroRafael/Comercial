package com.scing.erp.sistema.config;

import com.scing.erp.sistema.usuario.Usuario;
import com.scing.erp.sistema.usuario.UsuarioDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    configModelMapper(modelMapper);

    return modelMapper;
  }

  private void configModelMapper(ModelMapper modelMapper) {
    configUserToUserDTOMapper(modelMapper);
    configUserDTOToUserMapper(modelMapper);
  }

  private void configUserToUserDTOMapper(ModelMapper modelMapper) {
    modelMapper.typeMap(Usuario.class, UsuarioDTO.class);
  }

  private void configUserDTOToUserMapper(ModelMapper modelMapper) {
    modelMapper.typeMap(UsuarioDTO.class, Usuario.class);
  }
}
