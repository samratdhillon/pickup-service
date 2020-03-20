package com.cpc.pickup.domain;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.cpc.pickup.cmd.AssignPickupCmd;
import com.cpc.pickup.cmd.CompletePickupCmd;
import com.cpc.pickup.cmd.CreatePickupCmd;
import com.cpc.pickup.event.AssignPickupEvent;
import com.cpc.pickup.event.CompletePickupEvent;
import com.cpc.pickup.event.CreatePickupEvent;

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
        apply(CreatePickupEvent.builder().pickupDate(cmd.getPickupDate()).pickupAddress(cmd.getPickupAddress()).pickupId(cmd.getPickupId()).build());
    }
    
    @EventSourcingHandler
    public void onCreateEvent(CreatePickupEvent event) {
        this.pickupId = event.getPickupId();
        this.pickupAddress = event.getPickupAddress();
        this.pickupDate = event.getPickupDate();
    }
    
    @CommandHandler
    public void assignPickup(AssignPickupCmd cmd) {
        apply(AssignPickupEvent.builder().pickupId(cmd.getPickupId()).route(cmd.getRoute()).build());
    }
    
    @EventSourcingHandler
    public void onAssignEvent(AssignPickupEvent event) {
        this.route = event.getRoute();
    }
    
    @CommandHandler
    public void completePickup(CompletePickupCmd cmd) {
        apply(CompletePickupEvent.builder().pickupId(cmd.getPickupId()).route(cmd.getRoute()).dateTime(cmd.getDateTime()).build());
    }
    
    @EventSourcingHandler
    public void onCompleteEvent(CompletePickupEvent event) {
        this.route = event.getRoute();
        this.completed = true;
        this.completeDateTime = event.getDateTime();
    }

}
