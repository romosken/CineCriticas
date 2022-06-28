package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.upgradeuser;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.domain.enums.ERole;
import com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.upgradeuser.port.IUpgradeUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpgradeUserImpl implements IUpgradeUser {

    private final IUpgradeUserService iUpgradeUserService;
    private static final String UPGRADE_USER_SUCCESS = "Success upgrading user!";
    private static final String UPGRADE_USER_FAILURE = "User does not need upgrade yet!";

    @Override
    public UpgradeUserResponse verifyAndUpgradeUser(UpgradeUserRequest request) {
        var user = iUpgradeUserService.getUser(request.getUsername());
        var correctRole = getCorrectRole(user.getXp(), user.getRole());
        if (correctRole.equals(user.getRole()))
            return buildUpgradeUserResponse(UPGRADE_USER_FAILURE);
        user.setRole(correctRole);
        iUpgradeUserService.saveUser(user);
        return buildUpgradeUserResponse(UPGRADE_USER_SUCCESS);
    }

    private UpgradeUserResponse buildUpgradeUserResponse(String msg) {
        return UpgradeUserResponse.builder().response(msg).build();
    }

    private ERole getCorrectRole(int userXp, ERole userRole) {
        if (ERole.MODERADOR.equals(userRole) || userXp >= 1000)
            return ERole.MODERADOR;
        else if (ERole.AVANCADO.equals(userRole) || userXp >= 100)
            return ERole.AVANCADO;
        else if (ERole.BASICO.equals(userRole) || userXp >= 20)
            return ERole.BASICO;
        else
            return ERole.LEITOR;
    }
}
