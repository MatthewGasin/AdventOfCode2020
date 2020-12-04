import java.io.*;
import java.util.*;

public class Day4 {

    public static void main(String[] args) {
        String[] input = DataHelper.getInput("\n", 4);
        // partOne(input);
        partTwo(input);
    }

    public static void partOne(String[] input){
        ArrayList<Passport> passports = new ArrayList<Passport>();
        Passport temp = new Passport();
        for (String data : input) {
            if (data.trim().equals("")){
                passports.add(temp);
                temp = new Passport();
                continue;
            }

            String[] stats = data.split(" ");
            for(String stat : stats){
                String key = stat.split(":")[0].trim();
                String val = stat.split(":")[1].trim();
                switch (key){
                    case "byr":
                        temp.setByr(val);
                        break;
                    case "iyr":
                        temp.setIyr(val);
                        break;
                    case "eyr":
                        temp.setEyr(val);
                        break;
                    case "hgt":
                        temp.setHgt(val);
                        break;
                    case "hcl":
                        temp.setHcl(val);
                        break;
                    case "ecl":
                        temp.setEcl(val);
                        break;
                    case "pid":
                        temp.setPid(val);
                        break;
                    case "cid":
                        temp.setCid(val);
                        break;
                }
            }
        }

        int valid = 0;

        for (Passport p : passports){
            if (p.isValid()){
                valid++;
            }
        }

        System.out.println(valid);
    }

    public static void partTwo(String[] input){
        ArrayList<Passport> passports = new ArrayList<Passport>();
        Passport temp = new Passport();
        for (String data : input) {
            if (data.trim().equals("")){
                passports.add(temp);
                temp = new Passport();
                continue;
            }

            String[] stats = data.split(" ");
            for(String stat : stats){
                String key = stat.split(":")[0].trim();
                String val = stat.split(":")[1].trim();
                switch (key){
                    case "byr":
                        temp.setByr(val);
                        break;
                    case "iyr":
                        temp.setIyr(val);
                        break;
                    case "eyr":
                        temp.setEyr(val);
                        break;
                    case "hgt":
                        temp.setHgt(val);
                        break;
                    case "hcl":
                        temp.setHcl(val);
                        break;
                    case "ecl":
                        temp.setEcl(val);
                        break;
                    case "pid":
                        temp.setPid(val);
                        break;
                    case "cid":
                        temp.setCid(val);
                        break;
                }
            }
        }

        int valid = 0;

        for (Passport p : passports){
            if (p.isValidExtra()){
                valid++;
            }
        }

        System.out.println(valid);
    }
}

class Passport {
    private String hgt, hcl, ecl, byr, iyr, eyr, pid, cid;
    private static String invalid = "~~~";

    public Passport() {
        byr = iyr = eyr = pid = cid = hgt = hcl = ecl = invalid;
    }

    public boolean isValid(){
        boolean present = (!byr.equals(invalid) && !iyr.equals(invalid) && !eyr.equals(invalid) && !hgt.equals(invalid) && !hcl.equals(invalid) && !ecl.equals(invalid) &&!pid.equals(invalid));
        return present;
    }

    public boolean isValidExtra(){
        boolean present = (!byr.equals(invalid) && !iyr.equals(invalid) && !eyr.equals(invalid) && !hgt.equals(invalid) && !hcl.equals(invalid) && !ecl.equals(invalid) &&!pid.equals(invalid));
        boolean byrValid = false, iyrValid = false, eyrValid = false, hgtValid = false, hclValid = false, eclValid = false, pidValid = false;

        if(isStrDigits(getByr())){
            int byr = Integer.parseInt(getByr());
            if(byr >= 1920 && byr <= 2002){
                byrValid = true;
            }
        }

        if(isStrDigits(getIyr())){
            int iyr = Integer.parseInt(getIyr());
            if(iyr >= 2010 && iyr <= 2020){
                iyrValid = true;
            }
        }

        if(isStrDigits(getEyr())){
            int eyr = Integer.parseInt(getEyr());
            if(eyr >= 2020 && eyr <= 2030){
                eyrValid = true;
            }
        }

        if(Character.isDigit(getHgt().charAt(0))){
            if(getHgt().contains("cm")){
                int height = Integer.parseInt(getHgt().split("cm")[0]);
                if(height >= 150 && height <= 193){
                    hgtValid = true;
                }
            } else if(getHgt().contains("in")){
                int height = Integer.parseInt(getHgt().split("in")[0]);
                if(height >= 59 && height <= 76){
                    hgtValid = true;
                }
            }
        }

        if(getHcl().matches("#([0-9]|[a-f])+") && getHcl().length() == 7){
            hclValid = true;
        }

        if(getEcl().equals("amb") || getEcl().equals("blu") || getEcl().equals("brn") || getEcl().equals("gry") || getEcl().equals("grn") || getEcl().equals("hzl") || getEcl().equals("oth")){
            eclValid = true;
        }

        if(isStrDigits(getPid()) && getPid().length()==9){
            pidValid = true;
        }

        return present && byrValid && iyrValid && eyrValid && hgtValid && hclValid && eclValid && pidValid;
    }

    private boolean isStrDigits(String text){
        return(text.matches("^[0-9]*$"));
    }

    public String getHgt() {
        return hgt;
    }

    public void setHgt(String hgt) {
        this.hgt = hgt;
    }

    public String getHcl() {
        return hcl;
    }

    public void setHcl(String hcl) {
        this.hcl = hcl;
    }

    public String getEcl() {
        return ecl;
    }

    public void setEcl(String ecl) {
        this.ecl = ecl;
    }

    public String getByr() {
        return byr;
    }

    public void setByr(String byr) {
        this.byr = byr;
    }

    public String getIyr() {
        return iyr;
    }

    public void setIyr(String iyr) {
        this.iyr = iyr;
    }

    public String getEyr() {
        return eyr;
    }

    public void setEyr(String eyr) {
        this.eyr = eyr;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String toString(){
        return byr + " " + iyr + " " + eyr + " " + hgt + " " + hcl + " " + ecl + " " + pid + " " + cid;
    }
}