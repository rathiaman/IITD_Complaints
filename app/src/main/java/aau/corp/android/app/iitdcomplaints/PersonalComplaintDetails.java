package aau.corp.android.app.iitdcomplaints;

/**
 * Created by Aman Rathi on 27-03-2016.
 */
public class PersonalComplaintDetails {

    
    public static String particular_personal_complaint_title, particular_personal_complaint_room_no, particular_personal_complaint_contact_info;
    public static String particular_personal_complaint_complaint_type, particular_personal_complaint_status, particular_personal_complaint_description, particular_personal_complaint_id;

    public static String getParticular_personal_complaint_title(){
        return particular_personal_complaint_title;
    }

    public static void setparticular_personal_complaint_title(String name){
        particular_personal_complaint_title=name;
    }

    public static String getParticular_personal_complaint_room_no(){
        return particular_personal_complaint_room_no;
    }

    public static void setParticular_personal_complaint_room_no(String name){
        particular_personal_complaint_room_no=name;
    }

    public static String getParticular_personal_complaint_contact_info(){
        return particular_personal_complaint_contact_info;
    }

    public static void setParticular_personal_complaint_contact_info(String name){
        particular_personal_complaint_contact_info=name;
    }

    public static String getParticular_personal_complaint_complaint_type(){
        return particular_personal_complaint_complaint_type;
    }

    public static void setParticular_personal_complaint_complaint_type(String name){

        if (Integer.parseInt(name) == 1){particular_personal_complaint_complaint_type = "Electricity";}
        else if (Integer.parseInt(name) == 2){particular_personal_complaint_complaint_type = "Plumber";}
        else if (Integer.parseInt(name) == 3){particular_personal_complaint_complaint_type = "Carpentry";}
        else if (Integer.parseInt(name) == 4){particular_personal_complaint_complaint_type = "Internet Issues";}
        else if (Integer.parseInt(name) == 5){particular_personal_complaint_complaint_type = "Sweeper";}
        else if (Integer.parseInt(name) == 6){particular_personal_complaint_complaint_type = "Others";}
        else {particular_personal_complaint_complaint_type = "None";}
    }

    public static String getParticular_personal_complaint_status(){
        return particular_personal_complaint_status;
    }

    public static void setParticular_personal_complaint_status(String name){
        particular_personal_complaint_status=name;
    }

    public static String getParticular_personal_complaint_description(){
        return particular_personal_complaint_description;
    }

    public static void setParticular_personal_complaint_description(String name){
        particular_personal_complaint_description=name;
    }
    public static String getParticular_personal_complaint_id(){
        return particular_personal_complaint_id;
    }

    public static void setParticular_personal_complaint_id(String name){
        particular_personal_complaint_id=name;
    }

}
