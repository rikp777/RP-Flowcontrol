package flowcontrol.transport.controllers;

import flowcontrol.transport.exception.PalletLabelException;
import flowcontrol.transport.exception.ShippingLabelException;
import flowcontrol.transport.model.request.CreatePalletLabelRequest;
import flowcontrol.transport.model.request.CreateShippingLabelRequest;
import flowcontrol.transport.service.PalletLabelService;
import flowcontrol.transport.service.ShippingLabelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/farmers/{farmerId}/shippinglabels")
@AllArgsConstructor
public class ShippingLabelController {


    @Autowired
    private final ShippingLabelService shippingLabelService;

    @PostMapping()
    public ResponseEntity createPalletLabel(
            @PathVariable Long farmerId,
            @RequestBody CreateShippingLabelRequest newShippingLabel
    ){
        return shippingLabelService.create(farmerId, newShippingLabel)
                .map(palletLabel -> ResponseEntity.ok(palletLabel))
                .orElseThrow(() ->
                        new ShippingLabelException("Couldn't", "Something went wrong during creating ", newShippingLabel)
                );
    }
}
