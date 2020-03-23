package com.cpc.pickup.assignment.query;

import javax.persistence.EntityManager;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.cpc.pickup.event.PickupEvents.AssignPickupEvent;
import com.cpc.pickup.event.PickupEvents.CompletePickupEvent;
import com.cpc.pickup.event.PickupEvents.CreatePickupEvent;
import com.cpc.pickup.query.PickupQuery.PickupAssignmentQuery;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PickupAssignmentStatusProjection {

    private final EntityManager entityManager;    
    
    @EventHandler
    public void onCreate(CreatePickupEvent event) {
        PickupAssignmentStatus status = new PickupAssignmentStatus();
        status.setPickupId(event.pickupId());
        entityManager.persist(status);
    }
    
    @EventHandler
    public void onAssign(AssignPickupEvent event) {
        PickupAssignmentStatus status = entityManager.find(PickupAssignmentStatus.class, event.pickupId());
        status.setRoute(event.route());
    }
    
    @EventHandler
    public void onComplete(CompletePickupEvent event) {
        PickupAssignmentStatus status = entityManager.find(PickupAssignmentStatus.class, event.pickupId());
        status.setRoute(event.route());
        status.setCompleted(true);
        status.setCompletedDateTime(event.dateTime());
    }
    
    @QueryHandler
    public PickupAssignmentStatus queryPickupAssignment(PickupAssignmentQuery query) {
        return entityManager.find(PickupAssignmentStatus.class, query.pickupId());
    }
    
}
