package springboot.jpa.booking.config.auth.dto;

import lombok.*;
import springboot.jpa.booking.core.domain.member.Member;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class SessionUser implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String picture;

    public SessionUser(Member member){
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail().getValue();
        this.picture = member.getPicture();
    }

    @Builder
    public SessionUser(Long id, String name, String email, String picture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
}
