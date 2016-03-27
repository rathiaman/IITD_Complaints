package aau.corp.android.app.iitdcomplaints;

/**
 * Created by Aman Rathi on 27-03-2016.
 */
public class CheckInput {

    public static boolean check_contact_info(String contact){
        boolean answer = false;

        if(contact.length() != 10){return answer;}
        else if (contact.charAt(0) == 0){return answer;}
        else if (contact.charAt(0) < 58 & contact.charAt(0) > 48){
            if (contact.charAt(1) < 58 & contact.charAt(1) > 47){
                if (contact.charAt(2) < 58 & contact.charAt(2) > 47){
                    if (contact.charAt(3) < 58 & contact.charAt(3) > 47){
                        if (contact.charAt(4) < 58 & contact.charAt(4) > 47){
                            if (contact.charAt(5) < 58 & contact.charAt(5) > 47){
                                if (contact.charAt(6) < 58 & contact.charAt(6) > 47){
                                    if (contact.charAt(7) < 58 & contact.charAt(7) > 47){
                                        if (contact.charAt(8) < 58 & contact.charAt(8) > 47){
                                            if (contact.charAt(9) < 58 & contact.charAt(9) > 47){
                                                answer = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    return answer;
    }

}
