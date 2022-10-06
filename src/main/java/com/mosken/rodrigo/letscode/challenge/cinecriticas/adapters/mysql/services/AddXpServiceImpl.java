package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.services;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.exceptions.UserNotFoundException;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.repositories.UserRepository;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.addxptouser.port.IAddXpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddXpServiceImpl implements IAddXpService {

    private final UserRepository userRepository;

    private static final String USER_NOT_EXISTS = "The user does not exist!";
    private static final String SUCCESS = "Success adding xp to user: ";

    @Override
    public String addXpToUser(String username, int xpToAdd) {
        var userOptional = userRepository.findById(username);

        if(userOptional.isEmpty())
            throw new UserNotFoundException(USER_NOT_EXISTS);

        var user = userOptional.get();
        user.setXp(Integer.sum(user.getXp(), xpToAdd));
        userRepository.save(user);
        return SUCCESS + username ;
    }
}
