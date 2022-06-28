package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.upgradeuser;

public interface IUpgradeUser {

    UpgradeUserResponse verifyAndUpgradeUser(UpgradeUserRequest request);
    UpgradeUserResponse turnUserModerator(UpgradeUserRequest request);

}
