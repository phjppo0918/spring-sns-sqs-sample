package com.example.publishmemberservice;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MemberService {
    MemberRepository memberRepository;
    SnsClient snsClient;
    String arn;

    public MemberService(final MemberRepository memberRepository,
                         final SnsClient snsClient,
                         @Value("${aws.sns-arn}") final String arn) {
        this.memberRepository = memberRepository;
        this.snsClient = snsClient;
        this.arn = arn;
    }

    public void save(final Member member) {
        final Member saveResult = memberRepository.save(member);
        final PublishRequest publishRequest = PublishRequest.builder()
                .topicArn(arn)
                .messageGroupId("member-group")
                .message(saveResult.getId().toString())
                .build();
        log.info("전송한다잉 {}", saveResult.getId());
        snsClient.publish(publishRequest);
    }

    public Member findById(final Long id) {
        return memberRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
