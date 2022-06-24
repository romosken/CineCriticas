package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.services;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.DuplicateEntryException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.RoleRepositoryException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.repositories.RoleRepository;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.repositories.UserRepository;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db.ERole;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db.Role;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db.User;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.signinuser.port.ISignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements ISignInService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private static final String ROLE_NOT_FOUND = "Role not found in database!";
    private static final String EMAIL_ALREADY_EXISTS = "Email already exists in database!";


    @Override
    public Role getRole(ERole role) {
        try {
            return roleRepository.findByName(role).get();
        } catch (NoSuchElementException e) {
            throw new RoleRepositoryException(ROLE_NOT_FOUND);
        }
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }


    @Override
    public User createUser(User user) {
        try {
            return userRepository.save(user);
        } catch(DataIntegrityViolationException e){
            throw new DuplicateEntryException(EMAIL_ALREADY_EXISTS);
        }

    }
}
