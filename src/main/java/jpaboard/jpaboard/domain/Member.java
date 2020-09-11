package jpaboard.jpaboard.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String userName;

    @Embedded
    private Address address;

    private LocalDateTime joinDate;

    private String role;

    private String passWord;

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Reply> replies = new ArrayList<>();

    @Builder(builderClassName = "createMember", builderMethodName = "createMember")
    public Member(Long id, String userName, Address address, LocalDateTime joinDate, String role, String passWord) {
        this.id = id;
        this.userName = userName;
        this.address = address;
        this.joinDate = joinDate;
        this.role = role;
        this.passWord = passWord;
    }
}
