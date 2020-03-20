package com.cpc.pickup.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Builder
@Value
public class PickupAssignDTO {
    
    private final String pickupId;
    private final String route;

}
