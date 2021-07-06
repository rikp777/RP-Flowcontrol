package flowcontrol.production.controllers;

import flowcontrol.production.model.entity.Line;
import flowcontrol.production.model.meta.BasicMetaData;
import flowcontrol.production.model.response.LineResponse;
import flowcontrol.production.model.response.TicketResponse;
import flowcontrol.production.service.LineService;
import flowcontrol.production.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/lines")
@AllArgsConstructor
public class LineController {

    @Autowired
    private final LineService lineService;

    @GetMapping()
    public ResponseEntity<List<Line>> findAll(){
        return ResponseEntity.ok(lineService.getAll());
    }
}
