package aau.corp.android.app.iitdcomplaints;

/**
 * Created by Aman Rathi on 24-02-2016.
 */
public class Profile_data {

    public static String first_name, last_name, user_id, email_address, account_type, hostel, workertype;

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

    public static String getuserid(){
        return user_id;
    }

    public static void setuserid(String name){
        user_id=name;
    }

    public static String get_email(){
        return email_address;
    }

    public static void set_email(String name){
        email_address=name;
    }

    public static String getAccount_type(){
        return account_type;
    }

    public static void setaccount_type(String name){
        String account_type_1 = "Student";
        String account_type_2 = "Warden";
        String account_type_3 = "Institute Person";
        String account_type_4 = "Worker";

        if (Integer.parseInt(name) == 1){account_type = account_type_1;}
        else if (Integer.parseInt(name) == 2){account_type = account_type_2;}
        else if (Integer.parseInt(name) == 3){account_type = account_type_3;}
        else if (Integer.parseInt(name) == 4){account_type = account_type_4;}
        else {account_type = "none";}
    }

    public static String getHostel(){
        return hostel;
    }

    public static void setHostel(String name){
        String hostel_name_1 = "Aravali";
        String hostel_name_2 = "Girnar";
        String hostel_name_3 = "Himadri";
        String hostel_name_4 = "Jwalamukhi";
        String hostel_name_5 = "Kailash";
        String hostel_name_6 = "Karakoram";
        String hostel_name_7 = "Kumaon";
        String hostel_name_8 = "Nilgiri";
        String hostel_name_9 = "Satpura";
        String hostel_name_10 = "Shivalik";
        String hostel_name_11 = "Udaigiri";
        String hostel_name_12 = "Vindhyachal";
        String hostel_name_13 = "Zanskar";

        if(Integer.parseInt(name) == 1){hostel = hostel_name_1;}
        else if (Integer.parseInt(name) == 2){hostel = hostel_name_2;}
        else if (Integer.parseInt(name) == 3){hostel = hostel_name_3;}
        else if (Integer.parseInt(name) == 4){hostel = hostel_name_4;}
        else if (Integer.parseInt(name) == 5){hostel = hostel_name_5;}
        else if (Integer.parseInt(name) == 6){hostel = hostel_name_6;}
        else if (Integer.parseInt(name) == 7){hostel = hostel_name_7;}
        else if (Integer.parseInt(name) == 8){hostel = hostel_name_8;}
        else if (Integer.parseInt(name) == 9){hostel = hostel_name_9;}
        else if (Integer.parseInt(name) == 10){hostel = hostel_name_10;}
        else if (Integer.parseInt(name) == 11){hostel = hostel_name_11;}
        else if (Integer.parseInt(name) == 12){hostel = hostel_name_12;}
        else if (Integer.parseInt(name) == 13){hostel = hostel_name_13;}
        else {hostel = "none";}
    }

    public static String getWorkertype(){
        return workertype;
    }

    public static void setWorkertype(String name){

        String worker_type_0 = "None";
        String worker_type_1 = "Electrician";
        String worker_type_2 = "Plumber";
        String worker_type_3 = "Carpenter";
        String worker_type_4 = "Internet Issues";
        String worker_type_5 = "Sweeper";
        String worker_type_6 = "Others";

        if(Integer.parseInt(name) == 1){workertype = worker_type_1;}
        else if (Integer.parseInt(name) == 2){workertype = worker_type_2;}
        else if (Integer.parseInt(name) == 3){workertype = worker_type_3;}
        else if (Integer.parseInt(name) == 4){workertype = worker_type_4;}
        else if (Integer.parseInt(name) == 5){workertype = worker_type_5;}
        else if (Integer.parseInt(name) == 6){workertype = worker_type_6;}
        else if (Integer.parseInt(name) == 0){workertype = worker_type_0;}
        else {workertype = "none";}

    }






}
