package com.jdkgroup.general;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.joda.time.DateTime;
import org.joda.time.Days;

import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.CharMatcher;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jdkgroup.dialog.DialogLogout;
import com.jdkgroup.drawer.Drawer;
import com.jdkgroup.drawer.R;
import com.jdkgroup.fragment.FragmentHome;
import com.jdkgroup.model.Category;
import com.jdkgroup.model.GSONCategory;
import com.jdkgroup.model.PlacesService;
import com.jdkgroup.utils.AppKeyword;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;
import org.json.JSONObject;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GeneralImplement implements General {

    private Date date;
    private SimpleDateFormat simpleDateFormat;
    private CharSequence charsequence;
    private JSONObject jsonobject;
    private Iterator iterator;

    private Typeface typeface;
    private LinearLayoutManager linearLayoutManager;

    //TODO Calendar
    private Calendar calendar = Calendar.getInstance();
    private int mYear = calendar.get(Calendar.YEAR);
    private int mMonth = calendar.get(Calendar.MONTH);
    private int mDay = calendar.get(Calendar.DAY_OF_MONTH);

    private Snackbar snackbar;
    private Gson gson;

    @Override
    public String LongDateCovert(Long val) {
        date = new Date(Long.valueOf(val) * 1000);
        simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy");
        return simpleDateFormat.format(date);
    }

    @Override
    public String DayName(String date) {
        return null;
    }

    @Override
    public String DateZeroAdd(int number) {
        if (String.valueOf(number).length() < 2) {
            return String.valueOf(0 + "" + number);
        }
        return String.valueOf(number);
    }

    @Override
    public List<String> alSplit(final String splipt) {
        String arraySplit[] = splipt.split("\\s+");
        List<String> alSplit = new ArrayList<>();

        for (int i = 0; i < arraySplit.length; i++) {
            alSplit.add(arraySplit[i]);
        }
        return alSplit;
    }

    @Override
    public String LastCharacter(final String String) {
        char lastcharacter = String.charAt(String.length() - 1);
        return String.valueOf(lastcharacter);
    }

    @Override
    public CharSequence CurrentDateTime(final String dataformat) {
        simpleDateFormat = new SimpleDateFormat(dataformat);
        charsequence = simpleDateFormat.format(calendar.getTime());
        return charsequence;
    }

    @Override
    public CharSequence Uuid() {
        charsequence = java.util.UUID.randomUUID().toString();
        return charsequence;
    }

    @Override
    public JSONObject ConvertMapToJsonObject(final Map map) {
        jsonobject = new JSONObject();
        try {
            iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry) iterator.next();

                jsonobject.put(String.valueOf(pair.getKey()), String.valueOf(pair.getValue()));
            }
        } catch (Exception ex) {
        }

        return jsonobject;
    }

    @Override
    public String ConvertintToString(int value) {
        return String.valueOf(value);
    }

    @Override
    public boolean CheckInternetConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivityManager.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        } else {
            if (connectivityManager != null) {
                NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
                if (networkInfo != null)
                    for (int i = 0; i < networkInfo.length; i++)
                        if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
            }
        }

        return false;
    }

    @Override
    public void DisplayToast(final Activity activity, final String message, final int time, final boolean status) {
        if (status == true) {
            SwitchDisplayToast(activity, message, time);
        }
    }

    private void SwitchDisplayToast(final Activity activity, final String message, final int time) {
        switch (time) {
            case 1:
                Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                break;

            case 2:
                Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void DisplaySnackbar(final CoordinatorLayout coordinatorLayout, final String message, final String retry, final String retry_message, final int time, final int condition, final boolean status) {

        if (status == true) {
            SwitchDisplaySnackbar(coordinatorLayout, message, retry, retry_message, time, condition);
        }
    }

    private void SwitchDisplaySnackbar(final CoordinatorLayout coordinatorLayout, final String message, final String retry, final String retry_message, final int time, final int condition) {
        switch (condition) {
            case 1:
                snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT);
                snackbar.show();
                break;

            case 2:
                snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
                snackbar.show();
                break;

            case 3:
                snackbar = Snackbar
                        .make(coordinatorLayout, message, Snackbar.LENGTH_SHORT)
                        .setAction(retry, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbar = Snackbar.make(coordinatorLayout, retry_message, Snackbar.LENGTH_SHORT);
                                snackbar.show();
                            }
                        });
                snackbar.show();
                break;

            case 4:
                snackbar = Snackbar
                        .make(coordinatorLayout, message, Snackbar.LENGTH_LONG)
                        .setAction(retry, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbar = Snackbar.make(coordinatorLayout, retry_message, Snackbar.LENGTH_SHORT);
                                snackbar.show();
                            }
                        });
                snackbar.show();
                break;
        }
    }

    @Override
    public void DisplayNotification(Activity act, String eventtext) {

    }

    @Override
    public void DatePicketSet(final Activity act, final AppCompatTextView appCompatTextView) {
        DatePickerDialog dpd = new DatePickerDialog(act,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        if (String.valueOf(dayOfMonth).length() != 2 || String.valueOf(monthOfYear).length() != 2) {
                            appCompatTextView.setText(DateZeroAdd(dayOfMonth) + "/" + DateZeroAdd(monthOfYear + 1) + "/" + year);
                            appCompatTextView.setTextColor(Color.parseColor("#000000"));
                        } else {
                            appCompatTextView.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            appCompatTextView.setTextColor(Color.parseColor("#000000"));
                        }
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    @Override
    public String DatePicketFormat(final String dateformat) {
        switch (dateformat) {
            case "dd/mm/yyyy/zeroadd": {
                String dd_mm_yyyy_zeroadd = DateZeroAdd(mDay) + "/" + DateZeroAdd(mMonth + 1) + "/" + mYear;
                return dd_mm_yyyy_zeroadd;
            }
        }

        return null;
    }

    @Override
    public void HideKeyboard(final Activity activity, final String keybordtype) {
        SwitchHideKeyboard(activity, keybordtype);
    }

    @Override
    public void ConsoleLog(final String ActivityName, final String consoleprint, final String logstatus, final boolean status) {
        if (status == true) {
            SwitchConsoleLog(ActivityName, consoleprint, logstatus);
        }
    }

    private void SwitchConsoleLog(final String ActivityName, final String consoleprint, final String logstatus) {
        switch (logstatus) {
            case "debug":
                //The Log.d() method is used to log debug messages.
                Log.d(ActivityName, "=" + consoleprint + "=" + CurrentDateTime("DD/MM/YYYY"));
                break;

            case "info":
                //The Log.i() method is used to log informational messages.
                Log.i(ActivityName, "=" + consoleprint + "=" + CurrentDateTime("DD/MM/YYYY"));
                break;

            case "warn":
                //The Log.w() method is used to log warnings.
                Log.w(ActivityName, "=" + consoleprint + "=" + CurrentDateTime("DD/MM/YYYY"));
                break;

            case "error":
                //The Log.e() method is used to log errors.
                Log.e(ActivityName, "=" + consoleprint + "=" + CurrentDateTime("DD/MM/YYYY"));
                break;

            case "verbose":
                //The Log.v() method is used to log verbose messages.
                Log.v(ActivityName, "=" + consoleprint + "=" + CurrentDateTime("DD/MM/YYYY"));
                break;

            case "wtf":
                //The Log.wtf() method is used to log terrible failures that should never happen. ("WTF" stands for "What a Terrible Failure!" of course.)
                Log.wtf(ActivityName, "=" + consoleprint + "=" + CurrentDateTime("DD/MM/YYYY"));
                break;
        }
    }

    @Override
    public void ConsoleSystem(String ActivityName, String consoleprint, boolean status) {
        if (status == true) {
            SwitchConsoleSystem(ActivityName, consoleprint);
        }
    }

    private void SwitchConsoleSystem(final String ActivityName, final String consoleprint) {
        System.out.println(AppKeyword.APPNAME + " :: " + "(" + ActivityName + ")" + consoleprint);
    }

    private void SwitchHideKeyboard(final Activity activity, final String keybordtype) {
        View view;
        switch (keybordtype) {
            case "hide":
                view = activity.getCurrentFocus();
                if (view != null) {
                    InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                break;
        }

    }

    @Override
    public List<String[]> csv(final Activity activity, final String filename) {
        /*List<String[]> list = new ArrayList<>();
        String next[] = {};

        try {
            InputStreamReader csvStreamReader = new InputStreamReader(activity.getAssets().open(filename + ".csv"));
            CSVReader reader = new CSVReader(csvStreamReader);
            for (; ; ) {
                next = reader.readNext();
                if (next != null) {
                    list.add(next);
                } else {
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return list;*/

        return null;
    }

    @Override
    public void FontAppCompatButton(final AppCompatButton appCompatButton, final String fonttype) {
        typeface = Typeface.createFromAsset(appCompatButton.getContext().getAssets(), fonttype);
        appCompatButton.setTypeface(typeface);
    }

    @Override
    public void FontAppCompatTextView(final AppCompatTextView appCompatTextView, final String fonttype) {
        typeface = Typeface.createFromAsset(appCompatTextView.getContext().getAssets(), fonttype);
        appCompatTextView.setTypeface(typeface);
    }

    @Override
    public void FontAppCompatEditText(final AppCompatEditText appCompatEditText, final String fonttype) {
        typeface = Typeface.createFromAsset(appCompatEditText.getContext().getAssets(), fonttype);
        appCompatEditText.setTypeface(typeface);
    }

    @Override
    public void FacebookHashKey(Activity activity, String packageName) {
        PackageInfo info;
        try {
            info = activity.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String facebookkeyBase64 = new String(Base64.encode(md.digest(), 0));
                //String facebookkeyBase64new = new String(Base64.encodeBytes(md.digest()));

                ConsoleSystem("GeneralImplement", facebookkeyBase64, AppKeyword.STATUS);
                ConsoleLog("GeneralImplement", facebookkeyBase64, AppKeyword.LOGTYPE_DEBUG, AppKeyword.STATUS);
            }
        } catch (PackageManager.NameNotFoundException e1) {

        } catch (NoSuchAlgorithmException e) {

        } catch (Exception e) {

        }
    }

    @Override
    public void ImageViewSetBG(final Activity activity, final ImageView iv, final String imagename, final String foldername, final String colorbg, final String tintcolor, final int status) {
        SwitchImageViewSetBG(activity, iv, imagename, foldername, colorbg, tintcolor, status);
    }


    private void SwitchImageViewSetBG(final Activity activity, final ImageView iv, final String imagename, final String foldername, final String colorbg, final String tintcolor, final int status) {
        switch (status) {
            case 1:
                int resID = activity.getResources().getIdentifier(imagename, foldername, activity.getPackageName());
                iv.setImageResource(resID);
                break;

            case 2:
                //Tint color && Background Image
                break;

            case 3:
                //Background Image
                break;
        }
    }

    @Override
    public void RecyclerViewListGrid(final Activity activity, final RecyclerView recyclerView, final int numbergrid, final int status) {
        SwitchRecyclerViewListGrid(activity, recyclerView, numbergrid, status);
    }

    @Override
    public boolean ValidationEmail(final String email) {
        EmailValidator validator = EmailValidator.getInstance();
        if (validator.isValid(email)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean ValidationBlank(String strBlank) {
        if (StringUtils.isBlank(strBlank)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean ValidationisNotNullEmptyWhiteSpaceOnly(final String strAny) {
        return strAny != null && !strAny.isEmpty() && !strAny.trim().isEmpty();
    }

    @Override
    public boolean ValidationExpression(final String expression, final String password) {
        RegexValidator sensitive = new RegexValidator(expression);

        boolean check = sensitive.isValid(password);
        return check == true;
    }

    @Override
    public boolean ValidationLength(final String strLength, final int min, final int max) {
        boolean condition = GenericValidator.minLength(strLength, min, max);
        return condition;
    }

    @Override
    public boolean ValidationOnlyNumericAllow(final String strNumericAllow) {
        boolean condition = StringUtils.isNumeric(strNumericAllow);
        return condition;
    }

    @Override
    public boolean ValidationWhitespace(final String strWhitespace) {
        boolean condition = CharMatcher.WHITESPACE.matchesAnyOf(strWhitespace);
        return condition;
    }

    @Override
    public boolean ValidationContainNumeric(final String strNumeric) {
        boolean condition = StringUtils.containsOnly(strNumeric, "0123456789") || CharMatcher.JAVA_DIGIT.matchesAllOf(strNumeric);
        return condition;
    }

    @Override
    public boolean Validation2StringEqual(final String strEquala, final String strEqualb) {
        boolean check = StringUtils.equals(strEquala, strEqualb);
        return check;
    }

    @Override
    public int SpaceCount(final String strvalue) {
        int i, space = 0;
        char chararray[] = strvalue.toCharArray();
        for (i = 0; i < chararray.length; i++) {
            if (chararray[i] == ' ') {
                space = space + 1;
            }
        }
        return space;
    }

    @Override
    //First letter capital
    public String FirstCharacterUpperCase(final String strValue) {
        return Character.toUpperCase(strValue.charAt(0)) + strValue.substring(1);
    }

    @Override
    //Uppercase letter
    public List<String> AllUpperCase(final List<String> alString) {
        //List<String> transformeduppercase = Lists.transform(alString, (String input) -> input.toUpperCase());
        //return transformeduppercase;

        return null;
    }

    @Override
    //Lowercase letter
    public String tolowercase(final String line) {
        String result = "";
        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);
            char currentCharToLowerCase = Character.toLowerCase(currentChar);
            result = result + currentCharToLowerCase;
        }
        return result;
    }

    @Override
    //cell no specific format
    public String MobileNoFormat(final String mobileno) {
        ArrayList alcn = new ArrayList();
        alcn.add(mobileno.substring(0, 2));
        alcn.add(mobileno.substring(2, 4));
        alcn.add(mobileno.substring(4, 10));
        return alcn.get(0) + " - " + alcn.get(1) + " - " + alcn.get(2);
    }

    @Override
    public String DMYCount(final Object datemonthyearcount) throws Exception {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        Date fdate = null;
        Date cdate = null;
        fdate = formatter.parse(datemonthyearcount.toString());
        cdate = formatter.parse(formatter.format(date));

        DateTime dt1 = new DateTime(fdate);
        DateTime dt2 = new DateTime(cdate);

        if (Days.daysBetween(dt1, dt2).getDays() < 7) {
            if (Days.daysBetween(dt1, dt2).getDays() == 0) {
                return "Today";
            } else if (Days.daysBetween(dt1, dt2).getDays() > 1 && Days.daysBetween(dt1, dt2).getDays() < 7) {
                return Days.daysBetween(dt1, dt2).getDays() + " Days";
            }
        }
        return df_yyyy_MM_dd(datemonthyearcount.toString());
    }

    private String df_yyyy_MM_dd(String date) {
        String dates[] = date.split("/");
        String datey = dates[2];
        String dated = dates[0];
        String datem = dates[1];

        String detechar = getMonthChar(Integer.parseInt(datem) - 1);
        return dated + "/" + detechar + "/" + datey;
    }

    private String getMonthChar(int month) {
        String[] short_monthnames =
                {
                        "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT",
                        "NOV", "DEC"
                };

//        String[] full_monthnames =
//        {
//            "January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
//            "November", "December"
//        };
        return short_monthnames[month];
    }


    @Override
    public String MobileInRange(String no) {
        String telephonenumber = CharMatcher.inRange('0', '9').retainFrom(no);
        return telephonenumber;
    }

    @Override
    public void ClearMemory() {
        Runtime.getRuntime().freeMemory();
        Runtime.getRuntime().runFinalization();
        System.gc();
    }

    private void SwitchRecyclerViewListGrid(final Activity activity, final RecyclerView recyclerView, final int numbergrid, final int status) {
        switch (status) {
            case 1: //List Item
                linearLayoutManager = new LinearLayoutManager(activity);

                break;

            case 2: //Grid Item
                linearLayoutManager = new GridLayoutManager(activity, numbergrid);
                break;
        }

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public String ConvertString(final String convertString, final int switchcase) throws Exception {
        return SwitchStringConvert(convertString, switchcase);
    }

    private String SwitchStringConvert(final String stringValue, final int switchcase) throws Exception {
        byte[] byteArray;
        switch (switchcase) {
            case 1: //Base64Decode

            case 2:

            case 3: //Base64Decode
                byteArray = Base64.decode(stringValue, Base64.DEFAULT);
                return new String(byteArray, "UTF-8");
            case 4:
                byteArray = stringValue.getBytes("UTF-16");
                return new String(Base64.decode(Base64.encode(byteArray, Base64.DEFAULT), Base64.DEFAULT));
            default:
                break;
        }

        return null;
    }

    @Override
    public String ReadFile(final Activity activity, final String path, final String extension) throws Exception {
        String json = null;
        try {
            InputStream is = activity.getAssets().open(path + "." + extension);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    @Override
    public int dpToPx(final float dp, final Resources resources) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }

    @Override
    public void AnimationAppCompactActivity(final Activity activity, final Intent intent, boolean reverseAnimate, final boolean animate, final int requestCode, final int animationtype) {
        SwitchAnimationAppCompactActivity(activity, intent, reverseAnimate, animate, requestCode, animationtype);
    }

    private void SwitchAnimationAppCompactActivity(final Activity activity, final Intent intent, boolean reverseAnimate, final boolean animate, final int requestCode, final int animationtype) {
        switch (animationtype) {
            case 1: //startActivity
                activity.startActivity(intent);
                if (animate)
                    activity.overridePendingTransition(R.anim.animation_slide_from_right, R.anim.animation_slide_to_left);
                break;

            case 2: //startActivityForResult
                activity.startActivityForResult(intent, requestCode);
                if (animate)
                    activity.overridePendingTransition(R.anim.animation_slide_from_right, R.anim.animation_slide_to_left);
                break;

            case 3: //reverse animation
                activity.finish();
                if (animate)
                    activity.overridePendingTransition(R.anim.animation_slide_from_left, R.anim.animation_slide_to_right);
                break;

            case 4:
                activity.finish();
                if ((animate == true) && (reverseAnimate == true))
                    activity.overridePendingTransition(R.anim.animation_slide_from_left, R.anim.animation_slide_to_right);
                else if ((animate == true) && (reverseAnimate == false))
                    activity.overridePendingTransition(R.anim.animation_slide_from_left, R.anim.animation_slide_to_right);
                break;

            case 5: //startActivity
                activity.startActivity(intent);
                if ((animate == true) && (reverseAnimate == true))
                    activity.overridePendingTransition(R.anim.animation_slide_from_left, R.anim.animation_slide_to_right);
                else if ((animate == true) && (reverseAnimate == false))
                    activity.overridePendingTransition(R.anim.animation_slide_from_right, R.anim.animation_slide_to_left);
                break;
        }
    }

    @Override
    public void NavigationBar(final Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().setNavigationBarColor(activity.getResources().getColor(R.color.colorBlack));
        }
    }

    private void ChangeActivity(final Activity activity, final Class classname) {
        Intent i = new Intent(activity, classname);
        activity.startActivity(i);
    }

    private void pushFragments(Activity activity, Fragment fragment) {
        FragmentManager manager = ((Drawer) activity).getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        //ft.setCustomAnimations(R.anim.fragment_slide_left, R.anim.fragment_slide_right);
        ft.replace(R.id.containerView, fragment);

        //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void SwitchPassdataChangeFragment(final Activity activity, final int redirect) {
        Fragment fragment;

        switch (redirect) {
            case 4_0:
                ChangeActivity(activity, Drawer.class);
                break;

            case 4_1:
                fragment = new FragmentHome();
                pushFragments(activity, fragment);
                break;

            case 4_2:
                //ChangeActivity(activity, ActivityProfile.class);
                break;

            case 4_3:
                //ChangeActivity(activity, ActivityChangePassword.class);
                break;

            case 4_4:
                //Drawer.actToolbarTitle.setText("Setting");
                break;

            case 4_5:
                //Drawer.actToolbarTitle.setText("Contact Us");
                break;

            case 5_1:
                //ChangeActivity(activity, ActivityLogin.class);
                break;

            case 5_2:
                //ChangeActivity(activity, ActivitySignUp.class);
                break;

            case 5_3:
                Drawer.actToolbarTitle.setText("Forgot Password");

                break;

            case 5_4:
                Drawer.actToolbarTitle.setText("OTP"); //TODO OTP EMAIL ID

                break;

            case 5_5:
                Drawer.actToolbarTitle.setText("Map");
                //fragment = new FragmentMap();
                //pushFragments(activity, fragment);
                break;

            case 5_6:
                //ChangeActivity(activity, ActivitySearchHome.class);
                break;

            case 6_0:
                Drawer.mDrawerLayout.closeDrawers();
                DialogLogout dialogLogout = new DialogLogout(activity);
                dialogLogout.show();
                break;

            default:
                System.out.println("No available drawer menu");

        }
    }

    private Gson SwitchGson(int param) {
        switch (param) {
            case 1:
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                gson = gsonBuilder.create();
                return gson;

            case 2: //FIRST CHARACTER UPPER CAMEL
                gson = new GsonBuilder().
                        disableHtmlEscaping().
                        setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).
                        setPrettyPrinting().serializeNulls().
                        create();
                return gson;

            default:
                break;
        }

        return null;
    }

    @Override
    public String JSONPlaceServiceList() {
        GSONCategory gsonCategory = new GSONCategory();
        gsonCategory.setStatus(1);
        gsonCategory.setMessage("Data is available");

        List<PlacesService> alPlacesService = new ArrayList<>();
        List<Category> alCategories = new ArrayList<>();

        gson = SwitchGson(AppKeyword.GSON_NEWGSON);

        alCategories.add(new Category(1, "Accounting", "accounting", "accounting"));
        alCategories.add(new Category(2, "Airport", "airport", "airport"));
        alCategories.add(new Category(3, "Amusement Park", "amusement_park", "amusement_park"));
        alCategories.add(new Category(4, "Aquarium", "aquarium", "aquarium"));
        alCategories.add(new Category(5, "Art Gallery", "art_gallery", "art_gallery"));
        alCategories.add(new Category(6, "ATM", "atm", "atm"));
        alCategories.add(new Category(7, "Bakery", "bakery", "bakery"));
        alCategories.add(new Category(8, "Bank", "bank", "bank"));
        alCategories.add(new Category(9, "Bar", "bar", "bar"));
        alCategories.add(new Category(10, "Beauty Salon", "beauty_salon", "beauty_salon"));
        alCategories.add(new Category(11, "Bicycle Store", "bicycle_store", "bicycle_store"));
        alCategories.add(new Category(12, "Book Store", "book_store", "book_store"));
        alCategories.add(new Category(13, "Bowling Alley", "bowling_alley", "bowling_alley"));
        alCategories.add(new Category(14, "Bus Station", "bus_station", "bus_station"));
        alCategories.add(new Category(15, "Cafe", "cafe", "cafe"));
        alCategories.add(new Category(16, "Campground", "campground", "campground"));
        alCategories.add(new Category(17, "Car Dealer", "car_dealer", "car_dealer"));
        alCategories.add(new Category(18, "Car Rental", "car_rental", "car_rental"));
        alCategories.add(new Category(19, "Car Repair", "car_repair", "car_repair"));
        alCategories.add(new Category(20, "Car Wash", "car_wash", "car_wash"));
        alCategories.add(new Category(21, "Casino", "casino", "casino"));
        alCategories.add(new Category(22, "Cemetery", "cemetery", "cemetery"));
        alCategories.add(new Category(23, "Church", "church", "church"));
        alCategories.add(new Category(24, "City Hall", "city_hall", "city_hall"));
        alCategories.add(new Category(25, "Clothing Store", "clothing_store", "clothing_store"));
        alCategories.add(new Category(26, "Convenience Store", "convenience_store", "convenience_store"));
        alCategories.add(new Category(27, "Courthouse", "courthouse", "courthouse"));
        alCategories.add(new Category(28, "Dentist", "dentist", "dentist"));
        alCategories.add(new Category(29, "Department Store", "department_store", "department_store"));
        alCategories.add(new Category(30, "Doctor", "doctor", "doctor"));
        alCategories.add(new Category(31, "Electrician", "electrician", "electrician"));
        alCategories.add(new Category(32, "Electronics Store", "electronics_store", "electronics_store"));
        alCategories.add(new Category(33, "Embassy", "embassy", "embassy"));
        alCategories.add(new Category(34, "Establishment", "establishment (deprecated)", "establishment"));
        alCategories.add(new Category(35, "Finance", "finance (deprecated)", "finance"));
        alCategories.add(new Category(36, "Fire Station", "fire_station", "fire_station"));
        alCategories.add(new Category(37, "Florist", "florist", "florist"));
        alCategories.add(new Category(38, "Food", "food (deprecated)", "food"));
        alCategories.add(new Category(39, "Funeral Home", "funeral_home", "funeral_home"));
        alCategories.add(new Category(40, "Furniture Store", "furniture_store", "furniture_store"));
        alCategories.add(new Category(41, "Gas Station", "gas_station", "gas_station"));
        alCategories.add(new Category(42, "General Contractor", "general_contractor (deprecated)", "general_contractor"));
        alCategories.add(new Category(43, "Grocery or Supermarket", "grocery_or_supermarket (deprecated)", "grocery_or_supermarket"));
        alCategories.add(new Category(44, "GYM", "gym", "gym"));
        alCategories.add(new Category(45, "Hair Care", "hair_care", "hair_care"));
        alCategories.add(new Category(46, "Hardware Store", "hardware_store", "hardware_store"));
        alCategories.add(new Category(47, "Health", "health (deprecated)", "health"));
        alCategories.add(new Category(48, "Hindu Temple", "hindu_temple", "hindu_temple"));
        alCategories.add(new Category(49, "Home Goods Store", "home_goods_store", "home_goods_store"));
        alCategories.add(new Category(50, "Hospital", "hospital", "hospital"));
        alCategories.add(new Category(51, "Insurance Agency", "insurance_agency", "insurance_agency"));
        alCategories.add(new Category(52, "Jewelry Store", "jewelry_store", "jewelry_store"));
        alCategories.add(new Category(53, "Laundry", "laundry", "laundry"));
        alCategories.add(new Category(54, "Lawyer", "lawyer", "lawyer"));
        alCategories.add(new Category(55, "Library", "library", "library"));
        alCategories.add(new Category(56, "Liquor Store", "liquor_store", "liquor_store"));
        alCategories.add(new Category(57, "Local Government_office", "local_government_office", "local_government_office"));
        alCategories.add(new Category(58, "Locksmith", "locksmith", "locksmith"));
        alCategories.add(new Category(59, "Lodging", "lodging", "lodging"));
        alCategories.add(new Category(60, "Meal Delivery", "meal_delivery", "meal_delivery"));
        alCategories.add(new Category(61, "Meal Takeaway", "meal_takeaway", "meal_takeaway"));
        alCategories.add(new Category(62, "Mosque", "mosque", "mosque"));
        alCategories.add(new Category(63, "Movie Rental", "movie_rental", "movie_rental"));
        alCategories.add(new Category(64, "Movie Theater", "movie_theater", "movie_theater"));
        alCategories.add(new Category(65, "Moving Company", "moving_company", "moving_company"));
        alCategories.add(new Category(66, "Museum", "museum", "museum"));
        alCategories.add(new Category(67, "Night Club", "night_club", "night_club"));
        alCategories.add(new Category(68, "Painter", "painter", "painter"));
        alCategories.add(new Category(69, "Park", "park", "park"));
        alCategories.add(new Category(70, "Parking", "parking", "parking"));
        alCategories.add(new Category(71, "Pet Store", "pet_store", "pet_store"));
        alCategories.add(new Category(72, "Pharmacy", "pharmacy", "pharmacy"));
        alCategories.add(new Category(73, "Physiotherapist", "physiotherapist", "physiotherapist"));
        alCategories.add(new Category(74, "Place of Worship", "place_of_worship (deprecated)", "place_of_worship"));
        alCategories.add(new Category(75, "Plumber", "plumber", "plumber"));
        alCategories.add(new Category(76, "Police", "police", "police"));
        alCategories.add(new Category(77, "Post Office", "post_office", "post_office"));
        alCategories.add(new Category(78, "Real Estate_agency", "real_estate_agency", "real_estate_agency"));
        alCategories.add(new Category(79, "Restaurant", "restaurant", "restaurant"));
        alCategories.add(new Category(80, "Roofing Contractor", "roofing_contractor", "roofing_contractor"));
        alCategories.add(new Category(81, "RV Park", "rv_park", "rv_park"));
        alCategories.add(new Category(82, "School", "school", "school"));
        alCategories.add(new Category(83, "Shoe Store", "shoe_store", "shoe_store"));
        alCategories.add(new Category(84, "Shopping Mall", "shopping_mall", "shopping_mall"));
        alCategories.add(new Category(85, "SPA", "spa", "spa"));
        alCategories.add(new Category(87, "Stadium", "stadium", "stadium"));
        alCategories.add(new Category(88, "Storage", "storage", "storage"));
        alCategories.add(new Category(89, "Store", "store", "store"));
        alCategories.add(new Category(90, "Subway Station", "subway_station", "subway_station"));
        alCategories.add(new Category(91, "Synagogue", "synagogue", "synagogue"));
        alCategories.add(new Category(92, "Taxi Stand", "taxi_stand", "taxi_stand"));
        alCategories.add(new Category(93, "Train Station", "train_station", "train_station"));
        alCategories.add(new Category(94, "Transit Station", "transit_station", "transit_station"));
        alCategories.add(new Category(95, "Travel Agency", "travel_agency", "travel_agency"));
        alCategories.add(new Category(96, "University", "university", "university"));
        alCategories.add(new Category(97, "Veterinary Care", "veterinary_care", "veterinary_care"));
        alCategories.add(new Category(98, "Zoo", "zoo", "zoo"));

        alPlacesService.add(new PlacesService(1, "Area Level 1", "administrative_area_level_1", ""));
        alPlacesService.add(new PlacesService(2, "Area Level 2", "administrative_area_level_2", ""));
        alPlacesService.add(new PlacesService(3, "Area Level 3", "administrative_area_level_3", ""));
        alPlacesService.add(new PlacesService(4, "Area Level 4", "administrative_area_level_4", ""));
        alPlacesService.add(new PlacesService(5, "Area Level 5", "administrative_area_level_5", ""));
        alPlacesService.add(new PlacesService(6, "Colloquial_area", "colloquial_area", ""));
        alPlacesService.add(new PlacesService(7, "Country", "country", ""));
        alPlacesService.add(new PlacesService(8, "Establishment", "establishment", ""));
        alPlacesService.add(new PlacesService(9, "Finance", "finance", ""));
        alPlacesService.add(new PlacesService(10, "Floor", "floor", ""));
        alPlacesService.add(new PlacesService(11, "Food", "food", ""));
        alPlacesService.add(new PlacesService(12, "General Contractor", "general_contractor", ""));
        alPlacesService.add(new PlacesService(13, "Geocode", "geocode", ""));
        alPlacesService.add(new PlacesService(14, "Health", "health", ""));
        alPlacesService.add(new PlacesService(15, "Intersection", "intersection", ""));
        alPlacesService.add(new PlacesService(16, "Locality", "locality", ""));
        alPlacesService.add(new PlacesService(17, "Natural Feature", "natural_feature", ""));
        alPlacesService.add(new PlacesService(18, "Neighborhood", "neighborhood", ""));
        alPlacesService.add(new PlacesService(19, "Place Of Worship", "place_of_worship", ""));
        alPlacesService.add(new PlacesService(20, "Political", "political", ""));
        alPlacesService.add(new PlacesService(21, "Point Of Interest", "point_of_interest", ""));
        alPlacesService.add(new PlacesService(22, "Post Box", "post_box", ""));
        alPlacesService.add(new PlacesService(23, "Postal Code", "postal_code", ""));
        alPlacesService.add(new PlacesService(24, "Postal Code Prefix", "postal_code_prefix", ""));
        alPlacesService.add(new PlacesService(25, "Postal Code Suffix", "postal_code_suffix", ""));
        alPlacesService.add(new PlacesService(26, "Postal Town", "postal_town", ""));
        alPlacesService.add(new PlacesService(28, "Premise", "premise", ""));
        alPlacesService.add(new PlacesService(29, "Room", "room", ""));
        alPlacesService.add(new PlacesService(30, "Route", "route", ""));
        alPlacesService.add(new PlacesService(31, "Street Address", "street_address", ""));
        alPlacesService.add(new PlacesService(32, "Street Number", "street_number", ""));
        alPlacesService.add(new PlacesService(33, "Sublocality", "sublocality", ""));
        alPlacesService.add(new PlacesService(34, "Sublocality Level 1", "sublocality_level_1", ""));
        alPlacesService.add(new PlacesService(35, "Sublocality Level 2", "sublocality_level_2", ""));
        alPlacesService.add(new PlacesService(36, "Sublocality Level 3", "sublocality_level_4", ""));
        alPlacesService.add(new PlacesService(37, "Sublocality Level 4", "sublocality_level_5", ""));
        alPlacesService.add(new PlacesService(38, "Sublocality Level 1", "sublocality_level_1", ""));
        alPlacesService.add(new PlacesService(39, "Subpremise", "subpremise", ""));

        gsonCategory.setPlacesservice(alPlacesService);
        gsonCategory.setCategory(alCategories);

        return gson.toJson(gsonCategory);
    }
}
