package member;

import fine.Fine;
import fine.FineRepository;
import member.Member;

import java.util.List;

public class MemberServices {

    MemberRepository memberRepository = new MemberRepository();
    FineRepository fineRepository = new FineRepository();


    public List<Member> showMemberProfile(String memberEmail){

        List<Member> members = memberRepository.showMemberProfile(memberEmail);

        Member member = members.get(0);
        if (member.getStatus().equalsIgnoreCase("suspended")) {
            throw  new MemberSuspendedException(member.getId());
        }
        return members;

    }

    public void editProfileEmail(String memberEmail, String newEmail ) {

       memberRepository.editProfileEmail(newEmail, memberEmail);
    }

    public void editProfileName(String newFirstName, String newLastName, String memberEmail ) {

       memberRepository.editProfileName(newFirstName,newLastName, memberEmail);
    }

    public void editMemberSt(String memberEmail, String memberStatus) {

        memberRepository.editMemberStatus(memberEmail, memberStatus);
    }

    public void addMember(String firstName, String lastName, String email, String memberType) {

        //validering:
        // Får bara välja member type standard eller premium

        memberRepository.addMember(firstName, lastName, email, memberType);

    }

    public void suspendMember(String memberEmail) {

        //Validering
        // Varning om medlem redan är suspended

        memberRepository.suspendMember(memberEmail);
    }

    public List<Fine> showMemberFine(String memberEmail) {

        return fineRepository.showMemberFines(memberEmail);
    }


 }

