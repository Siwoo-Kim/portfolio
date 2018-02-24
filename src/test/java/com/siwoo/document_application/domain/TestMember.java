package com.siwoo.document_application.domain;

import java.util.Arrays;
import java.util.List;

public class TestMember {


    /*Convention over configuration*/
    public static List<Member> createMembers(){
        return Arrays.asList(
                new Member("user1@email.com","user1","Spring Walker","1234",null),
                new Member("user2@email.com","user2","Angular Walker","1234",null),
                new Member("user3@email.com","user3","Javascript Walker","1234",null),
                new Member("user4@email.com","user4","Kotlin Walker","1234",null));
    }
}
