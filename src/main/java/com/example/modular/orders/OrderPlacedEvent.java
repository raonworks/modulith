package com.example.modular.orders;

import org.jmolecules.event.annotation.Externalized;

@Externalized(target = OrderIntegrationConfiguration.ORDER_DESTINATION)
public record OrderPlacedEvent(
        int order,
        int quantity,
        int product
) {

}
