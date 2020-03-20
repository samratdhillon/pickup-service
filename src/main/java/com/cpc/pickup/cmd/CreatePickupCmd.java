package com.cpc.pickup.cmd;

import java.time.LocalDate;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
@AllArgsConstructor
@Value
public class CreatePickupCmd {
    @TargetAggregateIdentifier
    private final String pickupId;
    private final LocalDate pickupDate;
    private final String pickupAddress;

}
