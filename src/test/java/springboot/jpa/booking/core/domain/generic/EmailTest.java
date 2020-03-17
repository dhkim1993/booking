package springboot.jpa.booking.core.domain.generic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class EmailTest {

    @Test
    public void emailTest() throws Exception {
        //given
        String str = "dddd@naver.com";
        //when
        Email email = new Email(str);
        @javax.validation.constraints.Email String value = email.getValue();
        String host = email.getHost();
        String id = email.getId();
        //then
        assertThat(host).isEqualTo("@naver.com");
        assertThat(id).isEqualTo("dddd");
        assertThat(value).isEqualTo(str);
    }
}