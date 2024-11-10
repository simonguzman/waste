package com.waste.myfood.application.output;

import java.util.List;

import com.waste.myfood.domain.value_objects.CauseWaste;

public interface ManageCauseGatewayIntPort {
    List<CauseWaste> findByName(String name);

    CauseWaste save(CauseWaste cause);
}
