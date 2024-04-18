package com.example.fine_coding_challenge_java_spring_boot.lead;

import com.example.fine_coding_challenge_java_spring_boot.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class LeadService {

    private final LeadRepository leadRepository;

    public LeadService(final LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    public List<LeadDTO> findAll() {
        final List<Lead> leads = leadRepository.findAll(Sort.by("id"));
        return leads.stream()
                .map(lead -> mapToDTO(lead, new LeadDTO()))
                .toList();
    }

    public LeadDTO get(final Integer id) {
        return leadRepository.findById(id)
                .map(lead -> mapToDTO(lead, new LeadDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final LeadDTO leadDTO) {
        final Lead lead = new Lead();
        mapToEntity(leadDTO, lead);
        return leadRepository.save(lead).getId();
    }

    public void update(final Integer id, final LeadDTO leadDTO) {
        final Lead lead = leadRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(leadDTO, lead);
        leadRepository.save(lead);
    }

    public void delete(final Integer id) {
        leadRepository.deleteById(id);
    }

    private LeadDTO mapToDTO(final Lead lead, final LeadDTO leadDTO) {
        leadDTO.setId(lead.getId());
        leadDTO.setFirstName(lead.getFirstName());
        leadDTO.setLastName(lead.getLastName());
        leadDTO.setAddress(lead.getAddress());
        leadDTO.setCity(lead.getCity());
        leadDTO.setZipCode(lead.getZipCode());
        leadDTO.setPhoneNumber(lead.getPhoneNumber());
        leadDTO.setEmailAddress(lead.getEmailAddress());
        return leadDTO;
    }

    private Lead mapToEntity(final LeadDTO leadDTO, final Lead lead) {
        lead.setFirstName(leadDTO.getFirstName());
        lead.setLastName(leadDTO.getLastName());
        lead.setAddress(leadDTO.getAddress());
        lead.setCity(leadDTO.getCity());
        lead.setZipCode(leadDTO.getZipCode());
        lead.setPhoneNumber(leadDTO.getPhoneNumber());
        lead.setEmailAddress(leadDTO.getEmailAddress());
        return lead;
    }

    public boolean emailAddressExists(final String emailAddress) {
        return leadRepository.existsByEmailAddressIgnoreCase(emailAddress);
    }

}
