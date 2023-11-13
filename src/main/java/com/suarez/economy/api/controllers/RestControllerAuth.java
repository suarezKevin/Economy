//package com.suarez.economy.api.controllers;
//
//import com.suarez.economy.domain.repositories.RoleRepository;
//import com.suarez.economy.domain.repositories.UserRepository;
//import com.suarez.economy.dtos.DtoRegistro;
//import com.suarez.economy.security.jwt.JWTProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//public class RestControllerAuth {
//
//    private AuthenticationManager authenticationManager;
//    private PasswordEncoder passwordEncoder;
//    private RoleRepository roleRepository;
//    private UserRepository userRepository;
//    private JWTProvider jwtProvider;
//
//    @Autowired
//    public RestControllerAuth(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, RoleRepository rolesRepository, UserRepository usuariosRepository, JWTProvider jwtGenerador) {
//        this.authenticationManager = authenticationManager;
//        this.passwordEncoder = passwordEncoder;
//        this.roleRepository = rolesRepository;
//        this.userRepository = usuariosRepository;
//        this.jwtProvider = jwtGenerador;
//    }
//    //Método para poder registrar usuarios con role "user"
//    @PostMapping("register")
//    public ResponseEntity<String> registrar(@RequestBody DtoRegistro dtoRegistro) {
//        if (userRepository.existsByEmail(dtoRegistro.getEmail())) {
//            return new ResponseEntity<>("el usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
//        }
//        Usuarios usuarios = new Usuarios();
//        usuarios.setUsername(dtoRegistro.getUsername());
//        usuarios.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
//        Roles roles = rolesRepository.findByName("USER").get();
//        usuarios.setRoles(Collections.singletonList(roles));
//        usuariosRepository.save(usuarios);
//        return new ResponseEntity<>("Registro de usuario exitoso", HttpStatus.OK);
//    }
//
//    //Método para poder guardar usuarios de tipo ADMIN
//    @PostMapping("registerAdm")
//    public ResponseEntity<String> registrarAdmin(@RequestBody DtoRegistro dtoRegistro) {
//        if (usuariosRepository.existsByUsername(dtoRegistro.getUsername())) {
//            return new ResponseEntity<>("el usuario ya existe, intenta con otro", HttpStatus.BAD_REQUEST);
//        }
//        Usuarios usuarios = new Usuarios();
//        usuarios.setUsername(dtoRegistro.getUsername());
//        usuarios.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
//        Roles roles = rolesRepository.findByName("ADMIN").get();
//        usuarios.setRoles(Collections.singletonList(roles));
//        usuariosRepository.save(usuarios);
//        return new ResponseEntity<>("Registro de admin exitoso", HttpStatus.OK);
//    }
//
//    //Método para poder logear un usuario y obtener un token
//    @PostMapping("login")
//    public ResponseEntity<DtoAuthRespuesta> login(@RequestBody DtoLogin dtoLogin) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                dtoLogin.getUsername(), dtoLogin.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = jwtGenerador.generarToken(authentication);
//        return new ResponseEntity<>(new DtoAuthRespuesta(token), HttpStatus.OK);
//    }
//
//}
