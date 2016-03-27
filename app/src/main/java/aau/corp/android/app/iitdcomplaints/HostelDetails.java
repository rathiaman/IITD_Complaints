package aau.corp.android.app.iitdcomplaints;

/**
 * Created by Aman Rathi on 27-03-2016.
 */
public class HostelDetails {

    public static int get_Particular_hostel_id(String name){

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

        if (name == hostel_name_1){return 1;}
        else if (name == hostel_name_2){return 2;}
        else if (name == hostel_name_3){return 3;}
        else if (name == hostel_name_4){return 4;}
        else if (name == hostel_name_5){return 5;}
        else if (name == hostel_name_6){return 6;}
        else if (name == hostel_name_7){return 7;}
        else if (name == hostel_name_8){return 8;}
        else if (name == hostel_name_9){return 9;}
        else if (name == hostel_name_10){return 10;}
        else if (name == hostel_name_11){return 11;}
        else if (name == hostel_name_12){return 12;}
        else if (name == hostel_name_13){return 13;}
        else return 0;
    }



}
