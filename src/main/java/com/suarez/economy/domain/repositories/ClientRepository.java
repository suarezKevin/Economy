package com.suarez.economy.domain.repositories;

import com.suarez.economy.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

//    /**
//     * Check if there is a user with the given email.
//     *
//     * @param email The email to check.
//     * @return A boolean value.
//     */
//    boolean existsByEmail(String email);
//
    /**
     * Find a client by email, and return it wrapped in an Optional.
     *
     * @param email The email of the client to be found.
     * @return Optional<Client>
     */
    Optional<Client> findByEmail(String email);
//
//    /**
//     * Find a client by its ci.
//     *
//     * @param ci The client's ci.
//     * @return Optional<Client>
//     */
//    Optional<Client> findByCi(String ci);
//
//    /**
//     * > Returns true if there is a user with the given ci
//     *
//     * @param ci The ci of the user to check.
//     * @return A boolean value.
//     */
//    boolean existsByCi(String ci);

}
