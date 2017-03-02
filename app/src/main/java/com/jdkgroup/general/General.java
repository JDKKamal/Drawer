package com.jdkgroup.general;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import org.json.JSONObject;
import java.util.List;
import java.util.Map;

public interface General {
    String LongDateCovert(final Long val);

    String DayName(final String date);

    String DateZeroAdd(final int number);

    List<String> alSplit(final String splipt);

    String LastCharacter(final String String);

    CharSequence CurrentDateTime(final String dataformat);

    CharSequence Uuid();

    boolean CheckInternetConnection(final Context context);

    void DisplayToast(final Activity activity, final String message, final int time, final boolean status);

    void DisplaySnackbar(final CoordinatorLayout coordinatorLayout, final String message, final String retry, final String retry_message, final int time, final int condition, final boolean status);

    void DisplayNotification(final Activity act, final String eventtext);

    void HideKeyboard(final Activity activity, final String keybordtype);

    void NavigationBar(final Activity activity);

    //Log & System
    void ConsoleLog(final String ActivityName, final String consoleprint, final String logstatus, final boolean status);

    void ConsoleSystem(final String ActivityName, final String consoleprint, final boolean status);

    //Date
    void DatePicketSet(final Activity activity, final AppCompatTextView appCompatTextView);

    String DatePicketFormat(final String dateformat);

    //Convert
    List<String[]> csv(final Activity activity, final String csvfilename);

    JSONObject ConvertMapToJsonObject(final Map map);

    String ConvertintToString(final int value);

    String ConvertString(final String convertString, final int switchcase) throws Exception;

    //Widget Font Set
    void FontAppCompatButton(final AppCompatButton appCompatButton, final String fonttype);

    void FontAppCompatTextView(final AppCompatTextView appCompatTextView, final String fonttype);

    void FontAppCompatEditText(final AppCompatEditText appCompatEditText, final String fonttype);

    //Facebook Key
    void FacebookHashKey(final Activity activity, final String packageName);

    void ImageViewSetBG(final Activity activity, final ImageView iv, final String imagename, final String foldername, final String colorbg, final String tintcolor, final int status);

    void RecyclerViewListGrid(final Activity activity, final RecyclerView recyclerView, final int numbergrid, final int status);

    boolean ValidationEmail(final String email);

    boolean ValidationBlank(final String strBlank);

    boolean ValidationisNotNullEmptyWhiteSpaceOnly(final String strAny);

    boolean ValidationExpression(final String expression, final String password);

    boolean ValidationLength(final String strLength, final int min, final int max);

    boolean ValidationOnlyNumericAllow(final String strNumericAllow);

    boolean ValidationWhitespace(final String strWhitespace);

    boolean ValidationContainNumeric(final String strNumeric);

    boolean Validation2StringEqual(final String strEquala, final String strEqualb);

    int SpaceCount(final String strvalue);

    String FirstCharacterUpperCase(final String strValue);

    List<String> AllUpperCase(final List<String> alString);

    String tolowercase(final String line);

    String MobileNoFormat(final String mobileno);

    String DMYCount(final Object datemonthyearcount) throws Exception;

    String MobileInRange(String no);

    void ClearMemory();

    String ReadFile(final Activity activity, final String path, final String extension) throws Exception;

    int dpToPx(final float dp, final Resources resources);

    void AnimationAppCompactActivity(final Activity activity, final Intent intent, boolean reverseAnimate, final boolean animate, final int requestCode, final int animationtype);

    //TODO CHANGE ACTIVITY TO FRAGMENT
    void SwitchPassdataChangeFragment(final Activity activity, final int redirect);

    String JSONPlaceServiceList();
}

