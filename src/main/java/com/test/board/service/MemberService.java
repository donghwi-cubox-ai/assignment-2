package com.test.board.service;

import com.test.board.dto.LoginRequest;
import com.test.board.dto.RegisterRequest;
import com.test.board.entity.Member;
import com.test.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void addMember(RegisterRequest registerRequest) {
        Member member = new Member();
        member.setUserId(registerRequest.getUserId());
        member.setPassword(registerRequest.getPassword());
        member.setNickname(
                registerRequest.getLastName() + registerRequest.getFirstName()
        );
        member.setCreatedAt(LocalDateTime.now());
        memberRepository.save(member);
    }

    public boolean login(LoginRequest loginRequest) {
        String userId = loginRequest.getUserId();
        String password = loginRequest.getPassword();
        Optional<Member> member = memberRepository.findByUserIdAndPassword(userId, password);
        return member.isPresent();
    }
}
