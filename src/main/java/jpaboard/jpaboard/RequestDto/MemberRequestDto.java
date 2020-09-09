package jpaboard.jpaboard.RequestDto;

import jpaboard.jpaboard.domain.Address;
import jpaboard.jpaboard.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


@Getter
@Setter
public class MemberRequestDto {

    @NotEmpty(message = "이름을 입력해주세요.")
    private String userName;

    private LocalDateTime joinDate;
    private String city;
    private String street;
    private String zipcode;

    public Member toEntity(MemberRequestDto memberRequestDto){
        return Member.createMember()
                .userName(memberRequestDto.getUserName())
                .address(new Address(memberRequestDto.getCity(),memberRequestDto.getStreet(),memberRequestDto.getZipcode()))
                .joinDate(LocalDateTime.now())
                .build();
    }

}
