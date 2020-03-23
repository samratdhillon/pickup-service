package com.cpc.pickup.domain;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.cpc.pickup.cmd.PickupCmds.AssignPickupCmd;
import com.cpc.pickup.cmd.PickupCmds.CompletePickupCmd;
import com.cpc.pickup.cmd.PickupCmds.CreatePickupCmd;
import com.cpc.pickup.event.PickupEvents.AssignPickupEvent;
import com.cpc.pickup.event.PickupEvents.CompletePickupEvent;
import com.cpc.pickup.event.PickupEvents.CreatePickupEvent;

import lombok.Getter;
import lombok.NoArgsConstructor;
@Aggregate
@NoArgsConstructor
@Getter
public class Pickup {
    
    @AggregateIdentifier
    private String pickupId;
    
    private LocalDate pickupDate;
    
    private String outlet;
    
    private String route;
    
    private String pickupAddress;
    
    private boolean completed;
    
    private LocalDateTime completeDateTime;
    
    @CommandHandler
    public Pickup(CreatePickupCmd cmd) {
        apply(new CreatePickupEvent(cmd.pickupId(), cmd.pickupDate(), cmd.pickupAddress()));
    }
    
    @EventSourcingHandler
    public void onCreateEvent(CreatePickupEvent event) {
        this.pickupId = event.pickupId();
        this.pickupAddress = event.pickupAddress();
        this.pickupDate = event.pickupDate();
    }
    
    @CommandHandler
    public void assignPickup(AssignPickupCmd cmd) {
        apply(new AssignPickupEvent(cmd.pickupId(), cmd.route()));
    }
    
    @EventSourcingHandler
    public void onAssignEvent(AssignPickupEvent event) {
        this.route = event.route();
    }
    
    @CommandHandler
    public void completePickup(CompletePickupCmd cmd) {
        apply(new CompletePickupEvent(cmd.pickupId(), cmd.route(), cmd.dateTime()));
    }
    
    @EventSourcingHandler
    public void onCompleteEvent(CompletePickupEvent event) {
        this.route = event.route();
        this.completed = true;
        this.completeDateTime = event.dateTime();
    }

}
