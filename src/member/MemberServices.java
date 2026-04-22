package member;

import fine.Fine;
import fine.FineRepository;
import member.Member;

import java.util.List;

public class MemberServices {

    MemberRepository memberRepository = new MemberRepository();
    FineRepository fineRepository = new FineRepository();


    public List<Member> showMemberProfile(String memberEmail) {

        if (memberEmail.isEmpty()) { //Kollar så det inte söks med tom sträng för email i input
            throw new MemberNotFoundException(memberEmail);
        }

        List<Member> members = memberRepository.showMemberProfile(memberEmail);

        if (members.isEmpty()) { // kollar om eposten finns i listan av member som hämtats från DB
            throw new MemberNotFoundException(memberEmail);
        }

        Member member = members.getFirst(); //Hämtar det första (och enda) elementet i List
        if (member.getStatus().equalsIgnoreCase("suspended")) { //Kollar om suspended
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

