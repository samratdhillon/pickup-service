package com.cpc.pickup.cmd;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class CancelPickupCmd {
    @TargetAggregateIdentifier
    private final String pickupId;

}
