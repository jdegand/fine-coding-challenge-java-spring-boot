package com.example.fine_coding_challenge_java_spring_boot.deal_b;

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
@RequestMapping(value = "/api/dealBs", produces = MediaType.APPLICATION_JSON_VALUE)
public class DealBResource {

    private final DealBService dealBService;

    public DealBResource(final DealBService dealBService) {
        this.dealBService = dealBService;
    }

    @GetMapping
    public ResponseEntity<List<DealBDTO>> getAllDealBs() {
        return ResponseEntity.ok(dealBService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealBDTO> getDealB(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(dealBService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createDealB(@RequestBody @Valid final DealBDTO dealBDTO) {
        final Long createdId = dealBService.create(dealBDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateDealB(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final DealBDTO dealBDTO) {
        dealBService.update(id, dealBDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealB(@PathVariable(name = "id") final Long id) {
        dealBService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
