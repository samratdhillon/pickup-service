package com.cpc.pickup.history.query;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.cpc.pickup.event.PickupEvents.AssignPickupEvent;
import com.cpc.pickup.query.PickupQuery.PickupRouteHistoryQuery;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PickupRouteHistoryProjection {

    private final EntityManager entityManager;    
    
    
    @EventHandler
    public void onAssign(AssignPickupEvent event) {
        PickupsOnRouteHistory onRoute = entityManager.find(PickupsOnRouteHistory.class, event.pickupId());
        if(onRoute!=null) {
            onRoute.getRouteHistory().add(RouteAssignment.builder().route(event.route()).assignedDateTime(LocalDateTime.now()).build());
        }else {
            onRoute = new PickupsOnRouteHistory();
            onRoute.setPickupId(event.pickupId());
            onRoute.getRouteHistory().add(RouteAssignment.builder().route(event.route()).assignedDateTime(LocalDateTime.now()).build());
            entityManager.persist(onRoute);
        }
        
    }
    

    @QueryHandler
    public PickupsOnRouteHistory queryPickupAssignment(PickupRouteHistoryQuery query) {
        return entityManager.find(PickupsOnRouteHistory.class, query.pickupId());
    }
    
}
