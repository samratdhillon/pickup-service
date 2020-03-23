package com.cpc.pickup.event;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface PickupEvents {
    
    record AssignPickupEvent (String pickupId, String route) {}
    
    record CompletePickupEvent (String pickupId, String route, LocalDateTime dateTime) {}
    
    record CreatePickupEvent (String pickupId, LocalDate pickupDate, String pickupAddress) {}
    

}
