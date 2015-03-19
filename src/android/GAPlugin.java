package com.adobe.plugins;

import com.google.analytics.tracking.android.Tracker;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.HitBuilder;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.analytics.internal.Command;
import com.google.android.gms.analytics.internal.IAnalyticsService;
//import com.phonegap.helloworld.R;
import org.apache.cordova.CordovaPlugin;          // modificato da org.apache.cordova.CordovaPlugin a org.apache.cordova.api.CordovaPlugin
import org.apache.cordova.CallbackContext;          // modificato org.apache.cordova.CallbackContext a org.apache.cordova.api.CallbackContext
import org.json.JSONArray;
import org.json.JSONException;

//import com.google.analytics.tracking.android.GAServiceManager;
//import com.google.analytics.tracking.android.GoogleAnalytics;
//import com.google.analytics.tracking.android.Tracker;

public class GAPlugin extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callback) throws JSONException{
        GoogleAnalytics ga = GoogleAnalytics.getInstance(cordova.getActivity());
		Tracker tracker = ga.getDefaultTracker(); 

 		return false;
	}
}

