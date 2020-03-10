package kr.team.ticketing.config.auth.dto;

import kr.team.ticketing.core.domain.member.Member;
import lombok.Getter;

import java.io.Serializable;


@Getter
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
}
