package com.example.publishmemberservice;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("members")
public class MemberController {

    MemberService memberService;
    @PostMapping
    public void save(@RequestBody Member member) {
        memberService.save(member);
    }

    @GetMapping("{id}")
    public Member findById(@PathVariable Long id) {
        return memberService.findById(id);
    }
}
