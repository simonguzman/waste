package com.waste.myfood.domain.services;

import org.springframework.stereotype.Service;

import com.waste.myfood.domain.agregates.Waste;
import com.waste.myfood.domain.value_objects.CauseWaste;
import com.waste.myfood.domain.value_objects.ProductWaste;
import com.waste.myfood.infrastructure.exceptionHandler.ownException.BusinessRoleException;
import com.waste.myfood.infrastructure.exceptionHandler.ownException.ObjectNullException;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class WasteDomainService implements IWasteDomainService{
    @Override
    public boolean registerWaste(Waste waste) {
        if(waste == null)
            throw new ObjectNullException("Waste is null...");

        return waste.getQuantityWaste().validatePositive();
    }

    @Override
    public String suggestReductionMeasures(Waste waste) {
        if (waste == null)
            throw new ObjectNullException("Waste is null...");
        
        return waste.suggestReductionMeasures();
    }

    @Override
    public boolean addQuantity(Waste waste, double quantity) {
        if (waste == null)
            throw new ObjectNullException("Waste is null...");

        if(quantity <= 0)
            throw new BusinessRoleException("Quantity must be positive...");

        return waste.getQuantityWaste().addQuantity(quantity);
    }

    @Override
    public boolean reduceQuantity(Waste waste, double quantity) {
        if(waste == null)
            throw new ObjectNullException("Waste is null...");

        if(quantity < 0)
            throw new BusinessRoleException("Quantity must be positive...");

        return waste.getQuantityWaste().reduceQuantity(quantity);
    }

    @Override
    public String getDetailsProduct(ProductWaste product) {
        if(product == null)
            throw new ObjectNullException("Prdduct is null...");

        return product.detailsProduct();
    }

    @Override
    public String selectCause(CauseWaste cause, int causeIndex) {
        if(cause == null)
            throw new ObjectNullException("Cause is null...");

        return cause.selectCause(causeIndex);
    }

    @Override
    public boolean isValidCause(CauseWaste cause) {
        if(cause == null)
            throw new ObjectNullException("Cause is null...");

        return cause.getDescription() != null && !cause.getDescription().isEmpty();
    }
}
