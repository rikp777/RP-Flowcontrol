package flowcontrol.transport.controllers;

import flowcontrol.transport.exception.PalletLabelException;
import flowcontrol.transport.exception.ShippingLabelException;
import flowcontrol.transport.model.request.CreatePalletLabelRequest;
import flowcontrol.transport.model.request.CreateShippingLabelRequest;
import flowcontrol.transport.model.response.exact.Creditor;
import flowcontrol.transport.model.response.exact.Order;
import flowcontrol.transport.model.response.exact.OrderedAt;
import flowcontrol.transport.model.response.exact.ShippingLabelExactExportResponse;
import flowcontrol.transport.service.PalletLabelService;
import flowcontrol.transport.service.ShippingLabelService;
import lombok.AllArgsConstructor;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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


    @GetMapping(
            value = "{shippingLabelId}/export/xml",
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity exportShippingLabelForExact(@PathVariable Long farmerId, @PathVariable Long shippingLabelId){


        List<Order> orders = Arrays.asList(
                Order.builder()
                        .description("test")
                        .orderedAt(
                                OrderedAt.builder()
                                        .creditor(new Creditor())
                                        .date("2021-02-22")
                                        .build()
                        )
                        .build(),
                Order.builder()
                        .description("test")
                        .orderedAt(
                                OrderedAt.builder()
                                        .creditor(new Creditor())
                                        .date("2021-02-22")
                                        .build()
                        )
                        .build()
        );
        ShippingLabelExactExportResponse exactExportResponse = new ShippingLabelExactExportResponse();
        exactExportResponse.setOrders(orders);


        return shippingLabelService.getAll(farmerId, shippingLabelId)
                .map(shippingLabel -> ResponseEntity.ok(
                        exactExportResponse
                ))
                .orElseThrow(() -> new ShippingLabelException("Couldn't", "Something whent wrong while getting",
                        shippingLabelId));
    }
}
