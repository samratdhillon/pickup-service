package com.cpc.pickup.cmd;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.axonframework.modelling.command.TargetAggregateIdentifier;


public interface PickupCmds {

    
    record AssignPickupCmd(@TargetAggregateIdentifier String pickupId, String route) {}

    record CancelPickupCmd (@TargetAggregateIdentifier String pickupId) {}

    record CompletePickupCmd (@TargetAggregateIdentifier String pickupId, String route, LocalDateTime dateTime) {}

    record CreatePickupCmd (@TargetAggregateIdentifier String pickupId, LocalDate pickupDate, String pickupAddress) {}

    
}


