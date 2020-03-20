package com.cpc.pickup.event;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@AllArgsConstructor
@Value
public class CreatePickupEvent {
    private final String pickupId;
    private final LocalDate pickupDate;
    private final String pickupAddress;

}
