package com.siwoo.document_application.domain;

import java.time.LocalDateTime;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Entity @Table(name="sb_member")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@SequenceGenerator(
        name = "spring_member_generator",
        initialValue = 1,
        allocationSize = 1,
        sequenceName = "member_seq")
public class Member {

    @Id @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "spring_member_generator")
    @Column(name="member_id")
    /*id is database identity*/
    private Long id;
    /*email is application identity*/
    private String email;
    /*nickName is application name for member*/
    private String nickName;
    private String name;
    private String password;
    @CreationTimestamp
    private LocalDateTime joinDate;

    public Member(String email, String nickName, String name, String password, LocalDateTime joinDate) {
        this.email = email;
        this.nickName = nickName;
        this.name = name;
        this.password = password;
        this.joinDate = joinDate;
    }

    /* match the password argument with the password */
    public boolean isSamePassword(String password){
        return (StringUtils.hasText(password) && password.equals(this.password));
    }

}
