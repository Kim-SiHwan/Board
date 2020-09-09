package jpaboard.jpaboard;

import jpaboard.jpaboard.domain.Address;
import jpaboard.jpaboard.domain.Member;
import jpaboard.jpaboard.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    public void joinTest (){
    //given
        Member member= Member.createMember()
                .userName("오이")
                .address(new Address("감자","고구마","가지"))
                .build();
        memberService.join(member);
    //when
        Member getMember = memberService.findOne(member.getId());
    //then
        assertEquals(member,getMember);
    }

    @Test(expected = IllegalStateException.class)
    public void joinDuplicateTest (){
    //given
        Member member = Member.createMember()
                .userName("오이")
                .build();
        Member member1 = Member.createMember()
                .userName("오이")
                .build();
    //when
        memberService.join(member);
        memberService.join(member1);
    //then
        fail("IllegalStateException 발생해야함.");
    }

    @Test
    public void memberListTest (){
    //given
        for (int i = 0; i < 10; i++) {
            Member member = Member.createMember()
                    .userName("오이"+i)
                    .address(new Address("감자"+i,"고구마"+i,"가지"+i))
                    .build();
            memberService.join(member);
        }
    //when
        List<Member> list = memberService.findAll();
    //then
        assertEquals(10,list.size());

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getUserName());
        }

    }


}
