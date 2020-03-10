package kr.team.ticketing.web.member.dto;


import kr.team.ticketing.core.domain.generic.Email;
import kr.team.ticketing.core.domain.member.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;
    private String name;
    private String picture;
    private Email email;
    private Role role;

    @Builder
    public MemberResponseDto(Long id, String name, String picture, String email, Role role) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.email = new Email(email);
        this.role = role;
    }
}