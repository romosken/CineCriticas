package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.addxptouser;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.addxptouser.port.IAddXpService;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.upgradeuser.IUpgradeUser;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.upgradeuser.UpgradeUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddXpImpl implements IAddXp {
    private final IAddXpService iAddXpService;
    private final IUpgradeUser iUpgradeUser;

    @Override
    public AddXpResponse addXpToUser(AddXpRequest request) {
        var username = request.getUsername();
        var response = iAddXpService.addXpToUser(username, request.getXpToAdd());
        iUpgradeUser.verifyAndUpgradeUser(UpgradeUserRequest.builder().username(username).build());
        return AddXpResponse.builder().response(response).build();

    }


}
