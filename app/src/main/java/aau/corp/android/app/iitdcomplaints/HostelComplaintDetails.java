package aau.corp.android.app.iitdcomplaints;

/**
 * Created by Aman Rathi on 27-03-2016.
 */
public class HostelComplaintDetails {


    public static String particular_hostel_complaint_title, particular_hostel_complaint_room_no, particular_hostel_complaint_contact_info;
    public static String particular_hostel_complaint_complaint_type, particular_hostel_complaint_status, particular_hostel_complaint_description, particular_hostel_complaint_id;
    public static String particular_hostel_complaint_posted_by_first_name,particular_hostel_complaint_posted_by_last_name, particular_hostel_complaint_hostel;
    public static String getParticular_hostel_complaint_title(){
        return particular_hostel_complaint_title;
    }

    public static void setparticular_hostel_complaint_title(String name){
        particular_hostel_complaint_title=name;
    }

    public static String getParticular_hostel_complaint_room_no(){
        return particular_hostel_complaint_room_no;
    }

    public static void setParticular_hostel_complaint_room_no(String name){
        particular_hostel_complaint_room_no=name;
    }

    public static String getParticular_hostel_complaint_contact_info(){
        return particular_hostel_complaint_contact_info;
    }

    public static void setParticular_hostel_complaint_contact_info(String name){
        particular_hostel_complaint_contact_info=name;
    }

    public static String getParticular_hostel_complaint_complaint_type(){
        return particular_hostel_complaint_complaint_type;
    }

    public static void setParticular_hostel_complaint_complaint_type(String name){

        if (Integer.parseInt(name) == 1){particular_hostel_complaint_complaint_type = "Electricity";}
        else if (Integer.parseInt(name) == 2){particular_hostel_complaint_complaint_type = "Plumber";}
        else if (Integer.parseInt(name) == 3){particular_hostel_complaint_complaint_type = "Carpentry";}
        else if (Integer.parseInt(name) == 4){particular_hostel_complaint_complaint_type = "Internet Issues";}
        else if (Integer.parseInt(name) == 5){particular_hostel_complaint_complaint_type = "Sweeper";}
        else if (Integer.parseInt(name) == 6){particular_hostel_complaint_complaint_type = "Others";}
        else {particular_hostel_complaint_complaint_type = "None";}
    }

    public static String getParticular_hostel_complaint_status(){
        return particular_hostel_complaint_status;
    }

    public static void setParticular_hostel_complaint_status(String name){
        particular_hostel_complaint_status=name;
    }

    public static String getParticular_hostel_complaint_description(){
        return particular_hostel_complaint_description;
    }

    public static void setParticular_hostel_complaint_description(String name){
        particular_hostel_complaint_description=name;
    }
    public static String getParticular_hostel_complaint_id(){
        return particular_hostel_complaint_id;
    }

    public static void setParticular_hostel_complaint_id(String name){
        particular_hostel_complaint_id=name;
    }

    public static String getParticular_hostel_complaint_posted_by_first_name(){
        return particular_hostel_complaint_posted_by_first_name;
    }

    public static void setParticular_hostel_complaint_posted_by_first_name(String name){
        particular_hostel_complaint_posted_by_first_name=name;
    }

    public static String getParticular_hostel_complaint_posted_by_last_name(){
        return particular_hostel_complaint_posted_by_last_name;
    }

    public static void setParticular_hostel_complaint_posted_by_last_name(String name){
        particular_hostel_complaint_posted_by_last_name=name;
    }

    public static String getParticular_hostel_complaint_hostel(){
        return particular_hostel_complaint_hostel;
    }

    public static void setParticular_hostel_complaint_hostel(String name){

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

        if(Integer.parseInt(name) == 1){particular_hostel_complaint_hostel = hostel_name_1;}
        else if (Integer.parseInt(name) == 2){particular_hostel_complaint_hostel = hostel_name_2;}
        else if (Integer.parseInt(name) == 3){particular_hostel_complaint_hostel = hostel_name_3;}
        else if (Integer.parseInt(name) == 4){particular_hostel_complaint_hostel = hostel_name_4;}
        else if (Integer.parseInt(name) == 5){particular_hostel_complaint_hostel = hostel_name_5;}
        else if (Integer.parseInt(name) == 6){particular_hostel_complaint_hostel = hostel_name_6;}
        else if (Integer.parseInt(name) == 7){particular_hostel_complaint_hostel = hostel_name_7;}
        else if (Integer.parseInt(name) == 8){particular_hostel_complaint_hostel = hostel_name_8;}
        else if (Integer.parseInt(name) == 9){particular_hostel_complaint_hostel = hostel_name_9;}
        else if (Integer.parseInt(name) == 10){particular_hostel_complaint_hostel = hostel_name_10;}
        else if (Integer.parseInt(name) == 11){particular_hostel_complaint_hostel = hostel_name_11;}
        else if (Integer.parseInt(name) == 12){particular_hostel_complaint_hostel = hostel_name_12;}
        else if (Integer.parseInt(name) == 13){particular_hostel_complaint_hostel = hostel_name_13;}
        else {particular_hostel_complaint_hostel = "none";}

    }

}

