package com.waste.myfood.application.output;

import com.waste.myfood.domain.value_objects.QuantityWaste;

public interface ManageQuantityGatewayIntPort {
    QuantityWaste save(QuantityWaste quantity);
}
