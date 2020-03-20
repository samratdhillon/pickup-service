package com.cpc.pickup.cmd;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Value
@Builder
public class AssignPickupCmd {
    @TargetAggregateIdentifier
    private final String pickupId;
    private final String route;
}
