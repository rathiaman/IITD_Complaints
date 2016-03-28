package aau.corp.android.app.iitdcomplaints;


public class InstituteComplaintDetails {


    public static String particular_institute_complaint_title, particular_institute_complaint_room_no, particular_institute_complaint_contact_info;
    public static String particular_institute_complaint_complaint_type, particular_institute_complaint_status, particular_institute_complaint_description, particular_institute_complaint_id;
    public static String particular_institute_complaint_posted_by_first_name,particular_institute_complaint_posted_by_last_name, particular_institute_complaint_institute;
    public static String particular_institute_complaint_image;
    public static String getParticular_institute_complaint_title(){
        return particular_institute_complaint_title;
    }

    public static void setparticular_institute_complaint_title(String name){
        particular_institute_complaint_title=name;
    }

    public static String getParticular_institute_complaint_room_no(){
        return particular_institute_complaint_room_no;
    }

    public static void setParticular_institute_complaint_room_no(String name){
        particular_institute_complaint_room_no=name;
    }

    public static String getParticular_institute_complaint_contact_info(){
        return particular_institute_complaint_contact_info;
    }

    public static void setParticular_institute_complaint_contact_info(String name){
        particular_institute_complaint_contact_info=name;
    }

    public static String getParticular_institute_complaint_complaint_type(){
        return particular_institute_complaint_complaint_type;
    }

    public static void setParticular_institute_complaint_complaint_type(String name){

        if (Integer.parseInt(name) == 1){particular_institute_complaint_complaint_type = "Electricity";}
        else if (Integer.parseInt(name) == 2){particular_institute_complaint_complaint_type = "Plumber";}
        else if (Integer.parseInt(name) == 3){particular_institute_complaint_complaint_type = "Carpentry";}
        else if (Integer.parseInt(name) == 4){particular_institute_complaint_complaint_type = "Internet Issues";}
        else if (Integer.parseInt(name) == 5){particular_institute_complaint_complaint_type = "Sweeper";}
        else if (Integer.parseInt(name) == 6){particular_institute_complaint_complaint_type = "Others";}
        else {particular_institute_complaint_complaint_type = "None";}
    }

    public static String getParticular_institute_complaint_status(){
        return particular_institute_complaint_status;
    }

    public static void setParticular_institute_complaint_status(String name){
        particular_institute_complaint_status=name;
    }

    public static String getParticular_institute_complaint_description(){
        return particular_institute_complaint_description;
    }

    public static void setParticular_institute_complaint_description(String name){
        particular_institute_complaint_description=name;
    }
    public static String getParticular_institute_complaint_id(){
        return particular_institute_complaint_id;
    }

    public static void setParticular_institute_complaint_id(String name){
        particular_institute_complaint_id=name;
    }

    public static String getParticular_institute_complaint_posted_by_first_name(){
        return particular_institute_complaint_posted_by_first_name;
    }

    public static void setParticular_institute_complaint_posted_by_first_name(String name){
        particular_institute_complaint_posted_by_first_name=name;
    }

    public static String getParticular_institute_complaint_posted_by_last_name(){
        return particular_institute_complaint_posted_by_last_name;
    }

    public static void setParticular_institute_complaint_posted_by_last_name(String name){
        particular_institute_complaint_posted_by_last_name=name;
    }

    public static String getParticular_institute_complaint_hostel(){
        return particular_institute_complaint_institute;
    }

    public static void setParticular_institute_complaint_hostel(String name){

        String institute_name_1 = "Aravali";
        String institute_name_2 = "Girnar";
        String institute_name_3 = "Himadri";
        String institute_name_4 = "Jwalamukhi";
        String institute_name_5 = "Kailash";
        String institute_name_6 = "Karakoram";
        String institute_name_7 = "Kumaon";
        String institute_name_8 = "Nilgiri";
        String institute_name_9 = "Satpura";
        String institute_name_10 = "Shivalik";
        String institute_name_11 = "Udaigiri";
        String institute_name_12 = "Vindhyachal";
        String institute_name_13 = "Zanskar";

        if(Integer.parseInt(name) == 1){particular_institute_complaint_institute = institute_name_1;}
        else if (Integer.parseInt(name) == 2){particular_institute_complaint_institute = institute_name_2;}
        else if (Integer.parseInt(name) == 3){particular_institute_complaint_institute = institute_name_3;}
        else if (Integer.parseInt(name) == 4){particular_institute_complaint_institute = institute_name_4;}
        else if (Integer.parseInt(name) == 5){particular_institute_complaint_institute = institute_name_5;}
        else if (Integer.parseInt(name) == 6){particular_institute_complaint_institute = institute_name_6;}
        else if (Integer.parseInt(name) == 7){particular_institute_complaint_institute = institute_name_7;}
        else if (Integer.parseInt(name) == 8){particular_institute_complaint_institute = institute_name_8;}
        else if (Integer.parseInt(name) == 9){particular_institute_complaint_institute = institute_name_9;}
        else if (Integer.parseInt(name) == 10){particular_institute_complaint_institute = institute_name_10;}
        else if (Integer.parseInt(name) == 11){particular_institute_complaint_institute = institute_name_11;}
        else if (Integer.parseInt(name) == 12){particular_institute_complaint_institute = institute_name_12;}
        else if (Integer.parseInt(name) == 13){particular_institute_complaint_institute = institute_name_13;}
        else {particular_institute_complaint_institute = "none";}

    }

    public static String getParticular_institute_complaint_image(){
        return particular_institute_complaint_image;
    }

    public static void setParticular_institute_complaint_image(String name){
        particular_institute_complaint_image = name;
    }
}

