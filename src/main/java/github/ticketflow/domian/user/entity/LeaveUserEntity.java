package github.ticketflow.domian.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LeaveUser")
public class LeaveUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_user_id")
    private Long leaveUserId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name")
    private String username;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @CreatedDate
    @Column(name = "deleted_at", updatable = false)
    private LocalDateTime deletedAt;

    public LeaveUserEntity(UserEntity userEntity) {
        this.userId = userEntity.getId();
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.username = userEntity.getUsername();
        this.phoneNumber = userEntity.getPhoneNumber();
        this.joinDate = userEntity.getCreatedAt();
    }
}
