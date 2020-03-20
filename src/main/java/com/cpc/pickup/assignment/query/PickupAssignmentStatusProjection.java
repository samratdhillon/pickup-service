package com.cpc.pickup.assignment.query;

import javax.persistence.EntityManager;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.cpc.pickup.event.AssignPickupEvent;
import com.cpc.pickup.event.CompletePickupEvent;
import com.cpc.pickup.event.CreatePickupEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PickupAssignmentStatusProjection {

    private final EntityManager entityManager;    
    
    @EventHandler
    public void onCreate(CreatePickupEvent event) {
        PickupAssignmentStatus status = new PickupAssignmentStatus();
        status.setPickupId(event.getPickupId());
        entityManager.persist(status);
    }
    
    @EventHandler
    public void onAssign(AssignPickupEvent event) {
        PickupAssignmentStatus status = entityManager.find(PickupAssignmentStatus.class, event.getPickupId());
        status.setRoute(event.getRoute());
    }
    
    @EventHandler
    public void onComplete(CompletePickupEvent event) {
        PickupAssignmentStatus status = entityManager.find(PickupAssignmentStatus.class, event.getPickupId());
        status.setRoute(event.getRoute());
        status.setCompleted(true);
        status.setCompletedDateTime(event.getDateTime());
    }
    
    @QueryHandler
    public PickupAssignmentStatus queryPickupAssignment(PickupAssignmentQuery query) {
        return entityManager.find(PickupAssignmentStatus.class, query.getPickupId());
    }
    
}
