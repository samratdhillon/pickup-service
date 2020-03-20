package com.cpc.pickup.cmd;

import java.time.LocalDateTime;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Value
@Builder
public class CompletePickupCmd {
    @TargetAggregateIdentifier
    private final String pickupId;
    private final String route;
    private final LocalDateTime dateTime;
}
