package data;

import member.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Filehandler {
    private File file = new File("Members.csv");
    private ArrayList<Member> memberFiles = new ArrayList<>();


    public ArrayList<Member> load() throws IOException {
        ArrayList<Member> temp = new ArrayList<>();
        try(Scanner myReader = new Scanner(file, StandardCharsets.ISO_8859_1)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] MemberData = data.split(",");
                if (MemberData.length == 7) {
                    String name = MemberData[0];
                    String address = MemberData[1];
                    LocalDate birthday = null;
                    try {
                        String dateText = MemberData[2].split(",")[0].trim();
                        birthday = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    } catch (DateTimeParseException e) {
                        System.out.println("Fejl ved læsning af dato: " + data);
                        e.printStackTrace();
                    }
                    int memberID = Integer.parseInt(MemberData[3]);
                    String email = MemberData[4];
                    boolean isCompetitiveSwimmer = Boolean.parseBoolean(MemberData[5]);
                    boolean isActive = Boolean.parseBoolean(MemberData[6]);

                    Member member = new Member(name, address, birthday, memberID, email, isCompetitiveSwimmer, isActive);
                    temp.add(member);
                }
            }
        }
        this.memberFiles.addAll(temp); // Add all loaded members
        return temp;
    }

    public void save(ArrayList<Member> members) {
        try (PrintStream out = new PrintStream(file)) {
            for (Member member : members) {
                out.println(member.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Member> getMemberFiles(){
        return memberFiles;
    }
}

