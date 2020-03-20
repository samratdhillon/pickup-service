package com.cpc.pickup.event;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Value
@Builder
public class CompletePickupEvent {
    private final String pickupId;
    private final String route;
    private final LocalDateTime dateTime;
}
