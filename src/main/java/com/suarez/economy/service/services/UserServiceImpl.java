package com.suarez.economy.service.services;

import com.suarez.economy.api.models.requests.UserRequest;
import com.suarez.economy.api.models.responses.UserResponseDTO;
import com.suarez.economy.common.CustomAPIResponse;
import com.suarez.economy.common.CustomResponseBuilder;
import com.suarez.economy.domain.entities.Institution;
import com.suarez.economy.domain.entities.Role;
import com.suarez.economy.domain.entities.User;
import com.suarez.economy.domain.repositories.InstitutionRepository;
import com.suarez.economy.domain.repositories.RoleRepository;
import com.suarez.economy.domain.repositories.UserRepository;
import com.suarez.economy.service.abstract_services.IUserService;
import com.suarez.economy.util.mappers.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements IUserService{

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final InstitutionRepository institutionRepository;

    private final CustomResponseBuilder responseBuilder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, InstitutionRepository institutionRepository, CustomResponseBuilder responseBuilder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.institutionRepository = institutionRepository;
        this.responseBuilder = responseBuilder;
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> register(UserRequest request) {
        if (institutionRepository.existsByName(request.getName())){
            return responseBuilder.buildResponse(HttpStatus.BAD_REQUEST, "La institución con dicho nombre ya existe!");
        }
        Institution institution = new Institution();
        institution.setName(request.getName());
        if (request.getLogo() != null){
            institution.setLogo(request.getLogo());
        }
        institutionRepository.save(institution);
        Role roles = roleRepository.findByName("ADMIN").get();
        User user = UserMapper.INSTANCE.userFromUserRequest(request);
        user.setRoles(Collections.singletonList(roles));
        user.setInstitution(institution);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserResponseDTO userResponseDTO = UserMapper.INSTANCE.userResponseDTOFromUser(userRepository.save(user));
        return responseBuilder.buildResponse(HttpStatus.CREATED, "Registro exitosamente!", userResponseDTO);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> update(UUID id, UserRequest request) {
        User userToEdit = userRepository.findById(id).orElseThrow(()-> new RuntimeException("El usuario con id " + id + "no existe."));
        userToEdit.setFullname(request.getFullname());
        userToEdit.setEmail(request.getEmail());
        userToEdit.setPassword(request.getPassword());
        userToEdit.setTelephone(request.getTelephone());
        UserResponseDTO userResponseDTO = UserMapper.INSTANCE.userResponseDTOFromUser(userRepository.save(userToEdit));
        return responseBuilder.buildResponse(HttpStatus.OK, "Usuario editado exitosamente!", userResponseDTO);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> save(UserRequest request, UUID institutionId) {
        if(userRepository.existsByIdentification(request.getIdentification())){
            return responseBuilder.buildResponse(HttpStatus.BAD_REQUEST, "Ya se ha registrado un usuario con dicha cédula.");
        }
        if(institutionRepository.existsById(institutionId)){
            return responseBuilder.buildResponse(HttpStatus.BAD_REQUEST, "No se encuentra la institución.");
        }
        Institution institution = institutionRepository.findById(institutionId).orElseThrow(()-> new RuntimeException("Institución no encontrada"));
        User user = UserMapper.INSTANCE.userFromUserRequest(request);
        user.setStatus(true);
        user.setInstitution(institution);
        UserResponseDTO userResponse = UserMapper.INSTANCE.userResponseDTOFromUser(userRepository.save(user));
        return responseBuilder.buildResponse(HttpStatus.CREATED, "Usuario creado exitosamente!", userResponse);
    }

}
