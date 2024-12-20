package com.waste.myfood.infrastructure.input.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waste.myfood.application.input.ManageWasteCUIntPort;
import com.waste.myfood.domain.agregates.Waste;
import com.waste.myfood.infrastructure.input.dto.request.ProductWasteDTORequest;
import com.waste.myfood.infrastructure.input.dto.request.WasteDTORequest;
import com.waste.myfood.infrastructure.input.dto.response.WasteDTOResponse;
import com.waste.myfood.infrastructure.input.mappers.MapperProductInfraestructureDomain;
import com.waste.myfood.infrastructure.input.mappers.MapperWasteInfraestructureDomain;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/v1/wastes")
@AllArgsConstructor
public class WasteRestController {
    private final ManageWasteCUIntPort domain;
    private final MapperWasteInfraestructureDomain mapper;

    @PostMapping("")
    public ResponseEntity<?> createWaste(@Valid @RequestBody WasteDTORequest request, BindingResult errors) {
        Map<String, Object> errorResponse = catchErrors(errors);
        if (!errorResponse.isEmpty())
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

        if (request.getCause() == null || request.getCause().getDescription() == null) {
            errorResponse.put("mensaje", "Cause and its description cannot be null.");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        if (request.getProduct() == null || request.getProduct().getId() == null
                || request.getProduct().getId().isEmpty()) {
            errorResponse.put("mensaje", "Product cannot be null.");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Waste waste = this.mapper.infrastructureToDomain(request);
        Waste createdWaste = this.domain.createWaste(waste);

        if (createdWaste == null) {
            return new ResponseEntity<>("Failed to create waste. Please check the product details.",
                    HttpStatus.BAD_REQUEST);
        }

        WasteDTOResponse response = this.mapper.domainToInfrastructure(createdWaste);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerExpired(@Valid @RequestBody List<ProductWasteDTORequest> expired,
            BindingResult errors) {
        Map<String, Object> errorResponse = catchErrors(errors);
        if (!errorResponse.isEmpty())
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        List<Waste> result = this.domain
                .registerExpired(MapperProductInfraestructureDomain.mapInfraestructureDomain(expired));
        return new ResponseEntity<List<WasteDTOResponse>>(this.mapper.domainToInfrastructure(result), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> updateWaste(@Valid @RequestBody WasteDTORequest request, BindingResult errors) {
        Map<String, Object> errorResponse = catchErrors(errors);
        if (!errorResponse.isEmpty())
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

        try {
            Waste waste = this.mapper.infrastructureToDomain(request);
            Waste updatedWaste = this.domain.update(waste);
            WasteDTOResponse response = this.mapper.domainToInfrastructure(updatedWaste);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            errorResponse.put("mensaje", "Error updating waste: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/identifier/{wasteId}/quantity/{quantity}")
    public ResponseEntity<?> registerAdditionalWaste(
            @PathVariable String wasteId,
            @PathVariable double quantity) {
        try {
            Waste updatedWaste = this.domain.registerAdditionalWaste(wasteId, quantity);
            if (updatedWaste == null) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("mensaje", "No se pudo actualizar el registro de desperdicio");
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
            WasteDTOResponse response = this.mapper.domainToInfrastructure(updatedWaste);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Error registering additional waste: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllWastes() {
        try {
            List<WasteDTOResponse> response = this.mapper.domainToInfrastructure(this.domain.getAllWastes());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Error retrieving wastes: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cause/{cause}")
    public ResponseEntity<?> getWasteByCause(@PathVariable String cause) {
        try {
            List<WasteDTOResponse> response = this.mapper.domainToInfrastructure(this.domain.getWasteByCause(cause));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Error retrieving wastes by cause: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/identifier/{wasteId}")
    public ResponseEntity<?> getWasteById(@PathVariable String wasteId) {
        try {
            Waste waste = this.domain.getWasteById(wasteId);
            if (waste == null) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("mensaje", "Waste not found");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

            WasteDTOResponse response = this.mapper.domainToInfrastructure(waste);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Error retrieving waste: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getWasteByProductId(@PathVariable String productId) {
        try {
            List<WasteDTOResponse> response = this.mapper
                    .domainToInfrastructure(this.domain.getWasteByProductId(productId));
            if (response.isEmpty()) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("mensaje", "No se encontraron desperdicios para el producto con ID: " + productId);
                errorResponse.put("productId", productId);
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }

            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("productId", productId);
            successResponse.put("totalRegistros", response.size());
            successResponse.put("registros", response);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Error recuperando desperdicios: " + e.getMessage());
            errorResponse.put("productId", productId);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/total-waste/{productId}")
    public ResponseEntity<?> getTotalWasteByProductId(@PathVariable String productId) {
        try {
            double totalWaste = this.domain.getTotalWasteByProductId(productId);
            List<WasteDTOResponse> registros = this.mapper.domainToInfrastructure(
                    this.domain.getWasteByProductId(productId));

            Map<String, Object> response = new HashMap<>();
            response.put("productId", productId);
            response.put("totalDesperdicio", totalWaste);
            response.put("cantidadRegistros", registros.size());
            response.put("registrosDetallados", registros);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Error calculando total de desperdicios: " + e.getMessage());
            errorResponse.put("productId", productId);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/suggestions/{wasteId}")
    public ResponseEntity<?> suggestReductionMeasures(@PathVariable String wasteId) {
        try {
            String suggestions = this.domain.suggestReductionMeasures(wasteId);
            return new ResponseEntity<>(suggestions, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Error getting reduction suggestions: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Object> catchErrors(BindingResult errors) {
        Map<String, Object> response = new HashMap<>();
        if (errors.hasErrors()) {
            List<String> listaErrores = new ArrayList<>();

            for (FieldError error : errors.getFieldErrors()) {
                listaErrores.add("The field '" + error.getField() + "‘ " + error.getDefaultMessage());
            }
            response.put("errors", listaErrores);
        }
        return response;
    }
}
