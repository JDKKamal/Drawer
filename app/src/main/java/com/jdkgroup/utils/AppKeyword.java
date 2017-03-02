package com.jdkgroup.utils;

/**
 * Created by kamlesh on 12/18/2016.
 */

public class AppKeyword {
    public static String APPNAME = "Basic Information";
    private static String FONT_FOLDER = "fonts/";

    public static boolean
            STATUS = true;

    public static int REQUEST_PICKUP_IMAGE_GALLERY = 1;
    public static int REQUEST_PICKUP_IMAGE_CAMERA = 0;

    public static int PROGRESSBAR_SHOW = 1,
            PROGRESSBAR_CANCEL = 2;

    public static int RECYCLERVIEW_LIST = 1,
            RECYCLERVIEW_GRID = 2;

    public static String
            FOLDER_DRAWABLE = "drawable",
            FOLDER_MIPMAP = "mipmap";

    public static String
            LOGTYPE_DEBUG = "debug",
            LOGTYPE_INFO = "info",
            LOGTYPE_WARN = "warn",
            LOGTYPE_ERROR = "error",
            LOGTYPE_VERBOSE = "verbose",
            LOGTYPE_WTF = "wtf";

    public static String keyboardtypehide = "hide",
            keyboardtypeshow = "show";

    public static String
            fontsourcesanspro_light = FONT_FOLDER + "sourcesanspro_light.ttf",
            sourcesanspro_regular = FONT_FOLDER + "sourcesanspro_regular.ttf",
            sourcesanspro_bold = FONT_FOLDER + "sourcesanspro_bold.ttf";

    public static int
            SNACKBAR_SHORT = 1,
            SNACKBARLONG = 2,
            SNACKBAR_RETRY_SHORT = 3,
            SNACKBAR_RETRY_LONG = 4;

    public static String
            //[A-Z]{3,10}$
            EXPRESSION_PASSWORD = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).{6,20})",
            EXPRESSION_MOBILE = "^[0-9]{10,10}$",
            EXPRESSION_USERNAME = "[a-zA-Z]{3,20}$",
            EXPRESSION_BIRTHDATE = "\\d{1,2}-\\d{1,2}-\\d{4}";

    public static int GSON_NEWGSON = 1;

    //TODO MENU
    public static final int REDIRECT_DRAWER_4_0 = 4_0;
    public static final int REDIRECT_HOME_4_1 = 4_1;
    public static final int REDIRECT_PROFILE_4_2 = 4_2;
    public static final int REDIRECT_CHANGE_PASSWORD_4_3 = 4_3;
    public static final int REDIRECT_SETTING_4_4 = 4_4;
    public static final int REDIRECT_CONTACT_US_4_5 = 4_5;

    public static final int REDIRECT_LOGIN_5_1 = 5_1;
    public static final int REDIRECT_REGISTER_USER_5_2 = 5_2;
    public static final int REDIRECT_FORGOT_PASSWORD_5_3 = 5_3;
    public static final int REDIRECT_OTP_5_4 = 5_4;
    public static final int REDIRECT_MAP_5_5 = 5_5;
    public static final int REDIRECT_SEARCH_HOME_5_6 = 5_6;


    public static final int REDIRECT_LOGOUT_6_0 = 6_0;


    public static int WSCODE_STATUS_200_OK = 200; //OK

    public static int WSCODE_STATUS_512_NO_RESULT = 512; //OK


    public static int WSMESSAGECODE_STATUS_227_SUCCESSFUL_LOGIN = 227;
    public static int WSMESSAGECODE_STATUS_227_SUCCESSFUL_REGISTER_USER = 227;
    public static int WSMESSAGECODE_STATUS_227_SUCCESSFUL_ = 227;


    public static int WSMESSAGECODE_STATUS_227_EMAILID_EXIST = 228;
    public static int WSMESSAGECODE_STATUS_227_MOBILENO_EXIST = 229;
    public static int WSMESSAGECODE_STATUS_227_USERNAME_EXIST = 230;

}
