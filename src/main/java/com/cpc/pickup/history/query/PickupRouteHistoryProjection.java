package com.cpc.pickup.history.query;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.cpc.pickup.event.AssignPickupEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PickupRouteHistoryProjection {

    private final EntityManager entityManager;    
    
    
    @EventHandler
    public void onAssign(AssignPickupEvent event) {
        PickupsOnRouteHistory onRoute = entityManager.find(PickupsOnRouteHistory.class, event.getPickupId());
        if(onRoute!=null) {
            onRoute.getRouteHistory().add(RouteAssignment.builder().route(event.getRoute()).assignedDateTime(LocalDateTime.now()).build());
        }else {
            onRoute = new PickupsOnRouteHistory();
            onRoute.setPickupId(event.getPickupId());
            onRoute.getRouteHistory().add(RouteAssignment.builder().route(event.getRoute()).assignedDateTime(LocalDateTime.now()).build());
            entityManager.persist(onRoute);
        }
        
    }
    

    @QueryHandler
    public PickupsOnRouteHistory queryPickupAssignment(PickupRouteHistoryQuery query) {
        return entityManager.find(PickupsOnRouteHistory.class, query.getPickupId());
    }
    
}
