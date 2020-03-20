package com.cpc.pickup.controller.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Builder
@Value
public class PickupCompleteDTO {
    
    private final String pickupId;
    private final String route;
    private final LocalDateTime completeDateTime;

}
