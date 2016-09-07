package com.vathsav.lynk.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Class for app wide constants.
 */
public class Constants {

    // Intents
    public static final String intentHome = "com.vathsav.lynk.MAIN";

    // Firebase references
    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static final DatabaseReference globalsReference = database.getReference("globals");
    public static final DatabaseReference peripheralsReference = database.getReference("peripherals");

    // Names of Cores
    public static final String particleRuthlessDynamite = "ruthless_dynamite";

    // Firebase nodes
    public static final String lynkGlobalsServerTimestamp = "server_timestamp";

    // Toasts
    public static final String toastCommandCancelled = "Command cancelled";
    public static final String toastCoreOffline = "Core is offline.";
    public static final String toastIncorrectFunction = "Function doesn't exist on the Core!";
    public static final String toastUnableToFindCore = "Unable to find Core.";
    public static final String toastInvalidLoginCredentials = "Invalid login credentials.";

    // Peripheral names
    public static final String peripheralLight = "light";
    public static final String peripheralFan = "fan";
//    public static final String peripheralCommonOne = "something";
//    public static final String peripheralCommonTwo = "something";

    // Functions on the Core
    public static final String digitalWrite = "digitalWrite";
    public static final String digitalRead = "digitalRead";
    public static final String analogWrite = "analogWrite";
    public static final String analogRead = "analogRead";
    public static final String fireEmp = "skadoosh";

    // Dedicated GPIO configuration
//    public static final String userOneLight = "D0";
//    public static final String userOneFan = "A0";
//    public static final String userTwoLight = "D2";
//    public static final String userTwoFan = "A2";
//    public static final String userThreeLight = "D4";
//    public static final String userThreeFan = "A4";
//    public static final String userFourLight = "D6";
//    public static final String userFourFan = "A6";

    public static final String userOneLight = "D0";
    public static final String userOneFan = "D4";
    public static final String userTwoLight = "D1";
    public static final String userTwoFan = "D5";
    public static final String userThreeLight = "D2";
    public static final String userThreeFan = "D6";
    public static final String userFourLight = "D3";
    public static final String userFourFan = "D7";


//    public static String commonOne = "A";
//    public static String commonTwo = "A";

    // EMP code :P
    public static final String skadooshCode = "pandasareawesome";

    // Names of users
    public static final String userThree = "Gohsst";
    public static final String userTwo = "Darkside";
    public static final String userOne = "Brofessor Bobye";
    public static final String userFour = "Common";
//    public static final String userFour = "Crazy Ivan";
}