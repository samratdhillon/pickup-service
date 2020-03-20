package com.cpc.pickup.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Value
@Builder
public class AssignPickupEvent {
    private final String pickupId;
    private final String route;
}
