package com.mosken.rodrigo.letscode.challenge.cinecriticas.usecases.upgradeuser;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpgradeUserRequest {

    private String username;
}
