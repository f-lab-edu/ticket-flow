package github.ticketflow.domian.user.dto;

import github.ticketflow.domian.user.entity.LeaveUserEntity;
import github.ticketflow.domian.user.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDTO {

    private Long userId;
    private String email;
    private String username;
    private String phoneNumber;


    public UserResponseDTO(UserEntity userEntity) {
        this.userId = userEntity.getId();
        this.email = userEntity.getEmail();
        this.username = userEntity.getUsername();
        this.phoneNumber = userEntity.getPhoneNumber();
    }

    public UserResponseDTO(LeaveUserEntity leaveUserEntity) {
        this.userId = leaveUserEntity.getUserId();
        this.email = leaveUserEntity.getEmail();
        this.username = leaveUserEntity.getUsername();
        this.phoneNumber = leaveUserEntity.getPhoneNumber();
    }
}
