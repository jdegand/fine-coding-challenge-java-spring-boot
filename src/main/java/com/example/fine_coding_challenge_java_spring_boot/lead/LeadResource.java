package com.example.fine_coding_challenge_java_spring_boot.lead;

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
@RequestMapping(value = "/api/leads", produces = MediaType.APPLICATION_JSON_VALUE)
public class LeadResource {

    private final LeadService leadService;

    public LeadResource(final LeadService leadService) {
        this.leadService = leadService;
    }

    @GetMapping
    public ResponseEntity<List<LeadDTO>> getAllLeads() {
        return ResponseEntity.ok(leadService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeadDTO> getLead(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(leadService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createLead(@RequestBody @Valid final LeadDTO leadDTO) {
        final Integer createdId = leadService.create(leadDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateLead(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final LeadDTO leadDTO) {
        leadService.update(id, leadDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLead(@PathVariable(name = "id") final Integer id) {
        leadService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
