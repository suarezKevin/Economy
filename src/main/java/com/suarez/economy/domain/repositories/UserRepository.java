package com.suarez.economy.domain.repositories;

import com.suarez.economy.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

//    List<Client> findAllByStatusTrue();
//
//    Optional<Client> findByIdentification(String identification);
//
//    @Query("SELECT c.identification FROM Client c")
//    List<String> findAllIdentification();

    /**
     * Check if a user exists by username.
     *
     * @param username The username to check for.
     * @return A boolean value.
     */
    boolean existsByEmail(String username);

    boolean existsByIdentification(String indentification);

    /**
     * Find a user by their username, and return an Optional that contains the user
     * if found, or an
     * empty Optional if not found.
     *
     * @param username The username of the user you want to find.
     * @return Optional<User>
     */
    Optional<User> findByEmail(String username);

//    /**
//     * Find all users with a role in the given collection of roles.
//     *
//     * @param role The role to search for.
//     * @return A list of users with the specified role.
//     */
//    List<User> findByRoleIn(Collection<String> role);

}
