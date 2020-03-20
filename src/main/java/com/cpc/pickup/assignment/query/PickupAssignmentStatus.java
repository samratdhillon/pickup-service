package com.cpc.pickup.assignment.query;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class PickupAssignmentStatus {
    
    @Id
    private String pickupId;
    private String route;
    private boolean completed;
    private LocalDateTime completedDateTime;

}
