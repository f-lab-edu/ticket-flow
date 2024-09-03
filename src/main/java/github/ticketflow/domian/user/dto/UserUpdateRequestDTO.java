package github.ticketflow.domian.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class UserUpdateRequestDTO {

    private String username;
    private String phoneNumber;

}
