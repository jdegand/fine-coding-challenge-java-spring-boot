package com.example.fine_coding_challenge_java_spring_boot.deal_b;

import com.example.fine_coding_challenge_java_spring_boot.user.User;
import com.example.fine_coding_challenge_java_spring_boot.user.UserRepository;
import com.example.fine_coding_challenge_java_spring_boot.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class DealBService {

    private final DealBRepository dealBRepository;
    private final UserRepository userRepository;

    public DealBService(final DealBRepository dealBRepository,
            final UserRepository userRepository) {
        this.dealBRepository = dealBRepository;
        this.userRepository = userRepository;
    }

    public List<DealBDTO> findAll() {
        final List<DealB> dealBs = dealBRepository.findAll(Sort.by("id"));
        return dealBs.stream()
                .map(dealB -> mapToDTO(dealB, new DealBDTO()))
                .toList();
    }

    public DealBDTO get(final Long id) {
        return dealBRepository.findById(id)
                .map(dealB -> mapToDTO(dealB, new DealBDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final DealBDTO dealBDTO) {
        final DealB dealB = new DealB();
        mapToEntity(dealBDTO, dealB);
        return dealBRepository.save(dealB).getId();
    }

    public void update(final Long id, final DealBDTO dealBDTO) {
        final DealB dealB = dealBRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(dealBDTO, dealB);
        dealBRepository.save(dealB);
    }

    public void delete(final Long id) {
        dealBRepository.deleteById(id);
    }

    private DealBDTO mapToDTO(final DealB dealB, final DealBDTO dealBDTO) {
        dealBDTO.setId(dealB.getId());
        dealBDTO.setName(dealB.getName());
        dealBDTO.setDescription(dealB.getDescription());
        dealBDTO.setStatus(dealB.getStatus());
        dealBDTO.setGrossSalary(dealB.getGrossSalary());
        dealBDTO.setContractStartDate(dealB.getContractStartDate());
        dealBDTO.setUserId(dealB.getUserId() == null ? null : dealB.getUserId().getId());
        return dealBDTO;
    }

    private DealB mapToEntity(final DealBDTO dealBDTO, final DealB dealB) {
        dealB.setName(dealBDTO.getName());
        dealB.setDescription(dealBDTO.getDescription());
        dealB.setStatus(dealBDTO.getStatus());
        dealB.setGrossSalary(dealBDTO.getGrossSalary());
        dealB.setContractStartDate(dealBDTO.getContractStartDate());
        final User userId = dealBDTO.getUserId() == null ? null : userRepository.findById(dealBDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("userId not found"));
        dealB.setUserId(userId);
        return dealB;
    }

}
