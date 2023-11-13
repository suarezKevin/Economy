package com.suarez.economy;

import com.suarez.economy.domain.entities.Amortization;
import com.suarez.economy.domain.entities.Role;
import com.suarez.economy.domain.entities.TypeOfAmortization;
import com.suarez.economy.domain.repositories.AmortizationRepository;
import com.suarez.economy.domain.repositories.RoleRepository;
import com.suarez.economy.domain.repositories.TypeOfAmortizationRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
//@SecurityScheme(name = "swagger",scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class EconomyApplication  {
	public static void main(String[] args) {

		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(EconomyApplication.class, args);
		RoleRepository roleRepository = configurableApplicationContext.getBean(RoleRepository.class);
		if (roleRepository.findAll().isEmpty()){
			List<Role> roles = new ArrayList<>(List.of(
					new Role().setName(com.suarez.economy.util.enums.Role.ADMINISTRADOR.name()),
					new Role().setName(com.suarez.economy.util.enums.Role.CLIENTE.name())
			));
			roleRepository.saveAll(roles);
		}
		TypeOfAmortizationRepository repository = configurableApplicationContext.getBean(TypeOfAmortizationRepository.class);
		if (repository.findAll().isEmpty()){
		List<TypeOfAmortization> typeOfAmortizations = new ArrayList<>(List.of(
				new TypeOfAmortization().setName(com.suarez.economy.util.enums.TypeOfAmortization.FRANCES.name()),
				new TypeOfAmortization().setName(com.suarez.economy.util.enums.TypeOfAmortization.ALEMAN.name())
		));
			repository.saveAll(typeOfAmortizations);
		}
	}


}
