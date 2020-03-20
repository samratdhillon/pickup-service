package com.cpc.pickup.cmd;

import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.cpc.pickup.domain.Pickup;

@Component
public class PickupCommandConfiguration {

    @Bean
    public Repository<Pickup> pickupRepository(EventStore eventStore, Cache cache) {
        return EventSourcingRepository.builder(Pickup.class)
                                      .cache(cache)
                                      .eventStore(eventStore)
                                      .build();
    }

    @Bean
    public Cache cache() {
        return new WeakReferenceCache();
    }
}