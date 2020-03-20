package com.cpc.pickup.history.query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class PickupsOnRouteHistory {
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("assignedDateTime DESC")
    private List<RouteAssignment> routeHistory = new ArrayList<>();
    @Id
    private String pickupId;

}
