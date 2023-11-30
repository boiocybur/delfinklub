    package member;

    import java.time.LocalDate;
    import java.time.Period;

    public class Member {
        private String name;
        private String address;
        private LocalDate birthday;
        private int memberID;
        private String email;
        private boolean isCompetitiveSwimmer;
        private boolean isActive;

        public Member(String name, String address, LocalDate birthday, int memberID, String email, boolean membershipType, boolean isActive){
            this.name = name;
            this.address = address;
            this.birthday = birthday;
            this.memberID = memberID;
            this.email = email;
            this.isCompetitiveSwimmer = membershipType;
            this.isActive = isActive;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public LocalDate getBirthday() {
            return birthday;
        }

        public void setBirthday(LocalDate birthday) {
            this.birthday = birthday;
        }

        public int getMemberID() {
            return memberID;
        }

        public void setMemberID(int memberID) {
            this.memberID = memberID;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean isCompetitiveSwimmer() {
            return isCompetitiveSwimmer;
        }

        public boolean isJunior() {
            LocalDate now = LocalDate.now();
            return birthday.isAfter(now.minusYears(18));
        }

        public boolean isSenior() {
            LocalDate now = LocalDate.now();
            return birthday.isBefore(now.minusYears(18));
        }


        public void setCompetitiveSwimmer(boolean competitiveSwimmer) {
            this.isCompetitiveSwimmer = competitiveSwimmer;
        }

        @Override
        public String toString() {
            int age = Period.between(this.birthday, LocalDate.now()).getYears();
            return "Member{" +
                    "name='" + name + '\'' +
                    ", adresse='" + address + '\'' +
                    ", age=" + age +
                    ", medlemsnummer=" + memberID +
                    ", email='" + email + '\'' +
                    ", medlemstype=" + (isCompetitiveSwimmer ? "Competitive" : "Non Competitive") +
                    ", active=" + (isActive ? "Active" : "Passive") +
                    '}';
        }
    }
