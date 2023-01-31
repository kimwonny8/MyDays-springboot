package com.study.mydays.service;

import com.study.mydays.domain.Member;
import com.study.mydays.domain.MemberRole;
import com.study.mydays.dto.LoginDTO;
import com.study.mydays.dto.SignUpFormDTO;
import com.study.mydays.repository.MemberRepository;
import com.study.mydays.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberSeviceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public ResponseEntity signup(SignUpFormDTO formDTO) {
        // spring data jpa가 제공하는 findById() 메소드를 사용하여 Id가 동일한 객체를 가져옴
        Optional<Member> member = memberRepository.findById(formDTO.getId());

        // Member 엔티티의 @Builder 어노테이션이 빌더 패턴 클래스를 생성하게 해준다.
        // member가 없다면  컨트롤러에서 받아온 DTO 객체의 값을 이용하여 Member 객체 생성
        if (member.isEmpty()) {
            Member newMember = Member.builder()
                    .id(formDTO.getId())
                    .password(formDTO.getPassword())
                    .name(formDTO.getName())
                    .role(MemberRole.USER)
                    .build();

            // Spring Data JPA가 제공하는 save() 메소드를 이용하여 DB에 저장
            memberRepository.save(newMember);

            return new ResponseEntity("success", HttpStatus.OK);

        } else {
            return new ResponseEntity("fail", HttpStatus.OK);
        }
    }
    
    @Override
    public ResponseEntity login(LoginDTO loginDTO) {
        Optional<Member> member = memberRepository.findById(loginDTO.getId());

        // member가 null일 경우 memberEntity에 null이 할당
        Member memberEntity = member.orElse(null);

        if (memberEntity==null){
            return new ResponseEntity("해당 아이디를 가진 회원이 존재하지 않습니다.", HttpStatus.OK);
        }
        if (memberEntity.getPassword().equals(loginDTO.getPassword())){
            return new ResponseEntity("success", HttpStatus.OK);
        } else {
            return new ResponseEntity("비밀번호가 일치하지 않습니다.", HttpStatus.OK);
        }
    }
}
