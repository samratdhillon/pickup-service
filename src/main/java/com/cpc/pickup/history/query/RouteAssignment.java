package com.cpc.pickup.history.query;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class RouteAssignment {
    @Id
    @GeneratedValue
    private int routeAssignmentId;
    private String route;
    private LocalDateTime assignedDateTime;

}
