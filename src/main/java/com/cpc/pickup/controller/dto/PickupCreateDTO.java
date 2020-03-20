package com.cpc.pickup.controller.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@AllArgsConstructor
@Value
public class PickupCreateDTO {
    private final String pickupId;
    private final String pickupAddress;
    private final LocalDate pickupDate;
}
