package springboot.jpa.booking.web.member.dto;



import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springboot.jpa.booking.core.domain.generic.Email;
import springboot.jpa.booking.core.domain.member.Role;

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