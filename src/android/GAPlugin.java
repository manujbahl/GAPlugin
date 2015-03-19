package com.adobe.plugins;

import com.google.analytics.tracking.android.Tracker;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.HitBuilders;
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

        if (action.equals("initGA")) {
            try {
                tracker = ga.newTracker(args.getString(0));
                ga.setLocalDispatchPeriod(args.getInt(1));
                callback.success("initGA - id = " + args.getString(0) + "; interval = " + args.getInt(1) + " seconds");
                return true;
            } catch (final Exception e) {
                callback.error(e.getMessage());
            }
        } else if (action.equals("exitGA")) {
            try {
                /*GAServiceManager.getInstance().dispatch(); */
                ga.dispatchLocalHits();

                callback.success("exitGA");
                return true;
            } catch (final Exception e) {
                callback.error(e.getMessage());
            }
        } else if (action.equals("trackEvent")) {
            try {
                /*tracker.sendEvent(args.getString(0), args.getString(1), args.getString(2), args.getLong(3));  */
                tracker.send(new HitBuilders.EventBuilder()
                                .setCategory(args.getString(0))
                                .setAction(args.getString(1))
                                .setLabel(args.getString(2))
                                .setValue(args.getLong(3))
                                //.setValue(args.getString(3))
                                .build());
                        //MapBuilder.createEvent(args.getString(0), args.getString(1), args.getString(2), args.getLong(3)).build());
                callback.success("trackEvent - category = " + args.getString(0) + "; action = " + args.getString(1) + "; label = " + args.getString(2) + "; value = " + args.getInt(3));
                return true;
            } catch (final Exception e) {
                callback.error(e.getMessage());
            }
        } else if (action.equals("trackPage")) {
            try {
                /*tracker.sendView(args.getString(0));*/
                //tracker.setPage(args.getString(0));
                tracker.setScreenName(args.getString(0));
                tracker.send(new HitBuilders.AppViewBuilder().build());

                        //MapBuilder.createAppView().set(Fields.SCREEN_NAME,args.getString(0)).build());
                callback.success("trackPage - url = " + args.getString(0));
                return true;
            } catch (final Exception e) {
                callback.error(e.getMessage());
            }
        } /*else if (action.equals("setVariable")) {
            try {
                *//*tracker.setCustomDimension(args.getInt(0), args.getString(1));*//*
                // tracker.set(Fields.customDimension(args.getInt(0)), args.getString(1));
                tracker.send(new HitBuilders.AppViewBuilder()
                                .setCustomMetric(args.getInt(0), args.getLong(1))
                                .build()
                );

                callback.success("setVariable passed - index = " + args.getString(0) + "; value = " + args.getString(1));
                return true;
            } catch (final Exception e) {
                callback.error(e.getMessage());
            }
        }*/
        else if (action.equals("setDimension")) {
            try {
                /*tracker.setCustomDimension(args.getInt(0), args.getString(1));*/
                tracker.send(new HitBuilders.AppViewBuilder()
                                .setCustomDimension(args.getInt(0), args.getString(1))
                                .build()
                );
                callback.success("setDimension passed - index = " + args.getInt(0) + "; value = " + args.getString(1));
                return true;
            } catch (final Exception e) {
                callback.error(e.getMessage());
            }
        }
        else if (action.equals("setMetric")) {
            try {
                /*tracker.setCustomMetric(args.getInt(0), args.getLong(1));*/
                tracker.send(new HitBuilders.AppViewBuilder()
                                .setCustomMetric(args.getInt(0), args.getLong(1))
                                .build()
                );
                callback.success("setVariable passed - index = " + args.getInt(2) + "; key = " + args.getString(0) + "; value = " + args.getString(1));
                return true;
            } catch (final Exception e) {
                callback.error(e.getMessage());
            }
        }
		return false;
	}
}

