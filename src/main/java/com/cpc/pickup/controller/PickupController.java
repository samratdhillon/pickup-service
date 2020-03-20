package com.cpc.pickup.controller;

import java.util.concurrent.ExecutionException;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpc.pickup.assignment.query.PickupAssignmentQuery;
import com.cpc.pickup.assignment.query.PickupAssignmentStatus;
import com.cpc.pickup.cmd.AssignPickupCmd;
import com.cpc.pickup.cmd.CompletePickupCmd;
import com.cpc.pickup.cmd.CreatePickupCmd;
import com.cpc.pickup.controller.dto.PickupAssignDTO;
import com.cpc.pickup.controller.dto.PickupCompleteDTO;
import com.cpc.pickup.controller.dto.PickupCreateDTO;
import com.cpc.pickup.history.query.PickupRouteHistoryQuery;
import com.cpc.pickup.history.query.PickupsOnRouteHistory;

@RestController
public class PickupController {
    
    private final CommandGateway gateway;
    private final QueryGateway queryGateway;
    
    public PickupController(CommandGateway gateway,QueryGateway queryGateway) {
        this.gateway = gateway;
        this.queryGateway = queryGateway;
    }



    @RequestMapping(method = RequestMethod.POST, path = "pickup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createPickup(@RequestBody PickupCreateDTO dto) {
        CreatePickupCmd cmd = CreatePickupCmd.builder().pickupId(dto.getPickupId()).pickupAddress(dto.getPickupAddress()).pickupDate(dto.getPickupDate()).build();
        gateway.send(cmd);
    }
    
    @RequestMapping(method = RequestMethod.PUT, path = "pickup/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void assignPickup(@RequestBody PickupAssignDTO dto) {
        AssignPickupCmd cmd = AssignPickupCmd.builder().pickupId(dto.getPickupId()).route(dto.getRoute()).build();
        gateway.send(cmd);
    }
    
    @RequestMapping(method = RequestMethod.PUT, path = "pickup/{id}/complete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void completePickup(@RequestBody PickupCompleteDTO dto, @PathVariable("id")  String pickupId) {
        CompletePickupCmd cmd = CompletePickupCmd.builder().dateTime(dto.getCompleteDateTime()).pickupId(pickupId).route(dto.getRoute()).build();
        gateway.send(cmd);
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "pickup/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PickupAssignmentStatus getPickup(@PathVariable("id") String pickupId) throws InterruptedException, ExecutionException {
        PickupAssignmentQuery query = new PickupAssignmentQuery(pickupId);
        return queryGateway.query(query, PickupAssignmentStatus.class).get();
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "pickup/{id}/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public PickupsOnRouteHistory getPickupsByRoute(@PathVariable("id") String pickupId) throws InterruptedException, ExecutionException {
        PickupRouteHistoryQuery query = new PickupRouteHistoryQuery(pickupId);
        return queryGateway.query(query, PickupsOnRouteHistory.class).get();
    }

}
