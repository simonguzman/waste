package com.waste.myfood.application.output;

import com.waste.myfood.domain.value_objects.ProductWaste;

public interface ManageProductGatewayIntPort {
    ProductWaste findById(String id);

    boolean existById(String id);

    ProductWaste save(ProductWaste product);

}
