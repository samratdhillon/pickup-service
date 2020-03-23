package com.cpc.pickup.query;

public interface PickupQuery {
    
    record PickupAssignmentQuery (String pickupId) {}
    
    record PickupRouteHistoryQuery (String pickupId) {}


}
