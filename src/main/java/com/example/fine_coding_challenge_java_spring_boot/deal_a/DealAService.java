package com.example.fine_coding_challenge_java_spring_boot.deal_a;

import com.example.fine_coding_challenge_java_spring_boot.user.User;
import com.example.fine_coding_challenge_java_spring_boot.user.UserRepository;
import com.example.fine_coding_challenge_java_spring_boot.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class DealAService {

    private final DealARepository dealARepository;
    private final UserRepository userRepository;

    public DealAService(final DealARepository dealARepository,
            final UserRepository userRepository) {
        this.dealARepository = dealARepository;
        this.userRepository = userRepository;
    }

    public List<DealADTO> findAll() {
        final List<DealA> dealAs = dealARepository.findAll(Sort.by("id"));
        return dealAs.stream()
                .map(dealA -> mapToDTO(dealA, new DealADTO()))
                .toList();
    }

    public DealADTO get(final Long id) {
        return dealARepository.findById(id)
                .map(dealA -> mapToDTO(dealA, new DealADTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final DealADTO dealADTO) {
        final DealA dealA = new DealA();
        mapToEntity(dealADTO, dealA);
        return dealARepository.save(dealA).getId();
    }

    public void update(final Long id, final DealADTO dealADTO) {
        final DealA dealA = dealARepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(dealADTO, dealA);
        dealARepository.save(dealA);
    }

    public void delete(final Long id) {
        dealARepository.deleteById(id);
    }

    private DealADTO mapToDTO(final DealA dealA, final DealADTO dealADTO) {
        dealADTO.setId(dealA.getId());
        dealADTO.setName(dealA.getName());
        dealADTO.setDescription(dealA.getDescription());
        dealADTO.setStatus(dealA.getStatus());
        dealADTO.setWithdrawals(dealA.getWithdrawals());
        dealADTO.setDeposits(dealA.getDeposits());
        dealADTO.setAmountInvolved(dealA.getAmountInvolved());
        dealADTO.setUserId(dealA.getUserId() == null ? null : dealA.getUserId().getId());
        return dealADTO;
    }

    private DealA mapToEntity(final DealADTO dealADTO, final DealA dealA) {
        dealA.setName(dealADTO.getName());
        dealA.setDescription(dealADTO.getDescription());
        dealA.setStatus(dealADTO.getStatus());
        dealA.setWithdrawals(dealADTO.getWithdrawals());
        dealA.setDeposits(dealADTO.getDeposits());
        dealA.setAmountInvolved(dealADTO.getAmountInvolved());
        final User userId = dealADTO.getUserId() == null ? null : userRepository.findById(dealADTO.getUserId())
                .orElseThrow(() -> new NotFoundException("userId not found"));
        dealA.setUserId(userId);
        return dealA;
    }

}
