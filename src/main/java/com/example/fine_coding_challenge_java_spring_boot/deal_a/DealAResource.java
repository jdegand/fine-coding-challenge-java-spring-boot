package com.example.fine_coding_challenge_java_spring_boot.deal_a;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/dealAs", produces = MediaType.APPLICATION_JSON_VALUE)
public class DealAResource {

    private final DealAService dealAService;

    public DealAResource(final DealAService dealAService) {
        this.dealAService = dealAService;
    }

    @GetMapping
    public ResponseEntity<List<DealADTO>> getAllDealAs() {
        return ResponseEntity.ok(dealAService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealADTO> getDealA(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(dealAService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createDealA(@RequestBody @Valid final DealADTO dealADTO) {
        final Long createdId = dealAService.create(dealADTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateDealA(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final DealADTO dealADTO) {
        dealAService.update(id, dealADTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealA(@PathVariable(name = "id") final Long id) {
        dealAService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
