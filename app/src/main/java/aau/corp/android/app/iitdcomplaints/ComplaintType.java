package aau.corp.android.app.iitdcomplaints;

/**
 * Created by Aman Rathi on 27-03-2016.
 */
public class ComplaintType {

    public static int get_Particular_complaint_type_id(String name){

        if (name == "Electricity"){return 1;}
        else if (name == "Plumber"){return 2;}
        else if (name == "Carpentry"){return 3;}
        else if (name == "Internet Issues"){return 4;}
        else if (name == "Sweeper"){return 5;}
        else if (name == "Others"){return 6;}
        else return 0;
    }

}
