package aau.corp.android.app.iitdcomplaints;

/**
 * Created by Aman Rathi on 24-02-2016.
 */
public class Profile_data {

    public static String first_name, last_name, user_id, email_address, account_type;

    public static String getfirst_Name(){
        return first_name;
    }

    public static void setfirst_Name(String name){
        first_name=name;
    }

    public static String getlast_Name(){
        return last_name;
    }

    public static void setlast_Name(String name){
        last_name=name;
    }

    public static String getentryno(){
        return user_id;
    }

    public static void setentryno(String name){
        user_id=name;
    }

    public static String get_email(){
        return email_address;
    }

    public static void set_email(String name){
        email_address=name;
    }

    public static String getaccount_tyep(){
        return account_type;
    }

    public static void setaccount_tyep(String name){
        account_type=name;
    }





}
