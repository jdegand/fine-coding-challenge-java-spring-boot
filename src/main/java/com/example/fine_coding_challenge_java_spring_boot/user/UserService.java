package com.example.fine_coding_challenge_java_spring_boot.user;

import com.example.fine_coding_challenge_java_spring_boot.deal_a.DealA;
import com.example.fine_coding_challenge_java_spring_boot.deal_a.DealARepository;
import com.example.fine_coding_challenge_java_spring_boot.deal_b.DealB;
import com.example.fine_coding_challenge_java_spring_boot.deal_b.DealBRepository;
import com.example.fine_coding_challenge_java_spring_boot.util.NotFoundException;
import com.example.fine_coding_challenge_java_spring_boot.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final DealARepository dealARepository;
    private final DealBRepository dealBRepository;

    public UserService(final UserRepository userRepository, final DealARepository dealARepository,
            final DealBRepository dealBRepository) {
        this.userRepository = userRepository;
        this.dealARepository = dealARepository;
        this.dealBRepository = dealBRepository;
    }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("id"));
        return users.stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .toList();
    }

    public UserDTO get(final Integer id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }

    public void update(final Integer id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Integer id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setAddress(user.getAddress());
        userDTO.setCity(user.getCity());
        userDTO.setZipCode(user.getZipCode());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmailAddress(user.getEmailAddress());
        userDTO.setState(user.getState());
        userDTO.setCourt(user.getCourt());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());
        user.setZipCode(userDTO.getZipCode());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmailAddress(userDTO.getEmailAddress());
        user.setState(userDTO.getState());
        user.setCourt(userDTO.getCourt());
        return user;
    }

    public boolean emailAddressExists(final String emailAddress) {
        return userRepository.existsByEmailAddressIgnoreCase(emailAddress);
    }

    public ReferencedWarning getReferencedWarning(final Integer id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final DealA userIdDealA = dealARepository.findFirstByUserId(user);
        if (userIdDealA != null) {
            referencedWarning.setKey("user.dealA.userId.referenced");
            referencedWarning.addParam(userIdDealA.getId());
            return referencedWarning;
        }
        final DealB userIdDealB = dealBRepository.findFirstByUserId(user);
        if (userIdDealB != null) {
            referencedWarning.setKey("user.dealB.userId.referenced");
            referencedWarning.addParam(userIdDealB.getId());
            return referencedWarning;
        }
        return null;
    }

}
