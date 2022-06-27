package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.addxptouser;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.addxptouser.port.IAddXpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddXpImpl implements IAddXp {
    private final IAddXpService iAddXpService;

    @Override
    public AddXpResponse addXpToUser(AddXpRequest request) {
       var response = iAddXpService.addXpToUser(request.getUsername(), request.getXpToAdd());
       return AddXpResponse.builder().response(response).build();

    }


}
