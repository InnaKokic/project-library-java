package member;

import fine.Fine;
import fine.FineRepository;
import member.Member;

import java.util.List;

public class MemberServices {

    MemberRepository memberRepository = new MemberRepository();
    FineRepository fineRepository = new FineRepository();


    public List<Member> showMemberProfile(String memberEmail) {

        if (memberEmail.isEmpty()) {
            throw new MemberNotFoundException(memberEmail);
        }

        List<Member> members = memberRepository.showMemberProfile(memberEmail);

        if (members.isEmpty()) {          // <-- kolla listan, inte emailsträngen
            throw new MemberNotFoundException(memberEmail);
        }

        Member member = members.get(0);
        if (member.getStatus().equalsIgnoreCase("suspended")) {
            throw new MemberSuspendedException(member.getId());
        }

        return members;
    }

    public void editProfileEmail(String memberEmail, String newEmail ) {

        if (newEmail == null || newEmail.trim().isEmpty()) {
            System.out.println("Email cannot be empty");
            return;
        }

       memberRepository.editProfileEmail(newEmail.trim(), memberEmail);
    }

    public void editProfileName(String newFirstName, String newLastName, String memberEmail ) {

       memberRepository.editProfileName(newFirstName,newLastName, memberEmail);
    }

    public void editMemberStatus(String memberEmail, String memberStatus) {

        memberRepository.editMemberStatus(memberEmail, memberStatus);
    }

    public void addMember(String firstName, String lastName, String email, String memberType) {

        //.equalsIgnoreCase = gör att input inte är känslig för små eller stora bokstäver
        if (!memberType.equalsIgnoreCase("standard") && !memberType.equalsIgnoreCase("premium")) {
            System.out.println("Invalid membership type. Choose STANDARD or PREMIUM.");
            return;
        }
        memberRepository.addMember(firstName, lastName, email, memberType);

    }

    public void suspendMember(String memberEmail) {

        memberRepository.suspendMember(memberEmail);
    }

    public List<Fine> showMemberFine(String memberEmail) {

        return fineRepository.showMemberFines(memberEmail);
    }


 }

