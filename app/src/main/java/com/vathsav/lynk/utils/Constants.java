package com.vathsav.lynk.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Class for app wide constants.
 */
public class Constants {

    // Intents
    public static String intentHome = "com.vathsav.lynk.MAIN";

    // Firebase references
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference globalsReference = database.getReference("globals");
    public static DatabaseReference peripheralsReference = database.getReference("peripherals");

    // Names of Cores
    public static String particleRuthlessDynamite = "ruthless_dynamite";

    // Firebase nodes
    public static String lynkGlobalsServerTimestamp = "server_timestamp";

    // Toasts
    public static String toastCoreOffline = "Core is offline.";
    public static String toastIncorrectFunction = "Function doesn't exist on the Core!";
    public static String toastUnableToFindCore = "Unable to find Core.";
    public static String toastInvalidLoginCredentials = "Invalid login credentials.";

    // Peripheral names
    public static String peripheralLight = "light";
    public static String peripheralFan = "fan";
//    public static String peripheralCommonOne = "something";
//    public static String peripheralCommonTwo = "something";

    // Functions on the Core
    public static String digitalWrite = "digitalWrite";
    public static String digitalRead = "digitalRead";
    public static String analogWrite = "analogWrite";
    public static String analogRead = "analogRead";
    public static String fireEmp = "skadoosh";

    // Dedicated GPIO configuration
    public static String userOneLight = "D0";
    public static String userOneFan = "A0";
    public static String userTwoLight = "D2";
    public static String userTwoFan = "A2";
    public static String userThreeLight = "D4";
    public static String userThreeFan = "A4";
    public static String userFourLight = "D6";
    public static String userFourFan = "A6";
//    public static String commonOne = "A";
//    public static String commonTwo = "A";

    // EMP code :P
    public static String skadooshCode = "pandasareawesome";

    // Names of users
    public static String userOne = "Sentinel";
    public static String userTwo = "El Diablo";
    public static String userThree = "Deadshot";
    public static String userFour = "Crazy Ivan";
}