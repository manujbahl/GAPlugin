
package org.apache.cordova.plugin;
//package org.apache.cordova.api;

import com.google.analytics.tracking.android.*;
import org.apache.cordova.CordovaPlugin;          // modificato da org.apache.cordova.CordovaPlugin a org.apache.cordova.api.CordovaPlugin
import org.apache.cordova.CallbackContext;          // modificato org.apache.cordova.CallbackContext a org.apache.cordova.api.CallbackContext
import org.json.JSONArray;
import org.json.JSONException;

public class GAPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callback) throws JSONException{
        GoogleAnalytics ga = GoogleAnalytics.getInstance(cordova.getActivity());
        Tracker tracker = ga.getDefaultTracker();

        if (action.equals("initGA")) {
            String state = "1";
            try {
                state = "2";
                tracker = ga.getTracker(args.getString(0));
                state = "3";
                /*GAServiceManager.getInstance().setDispatchPeriod(args.getInt(1));*/
                GAServiceManager.getInstance().setLocalDispatchPeriod(args.getInt(1));
                state = "4";
                callback.success("initGA - id = " + args.getString(0) + "; interval = " + args.getInt(1) + " seconds");
                state = "5";
                return true;
            } catch (final Exception e) {
                //alert("exception e = " + e);
                callback.error("initGA error :: " + e.getMessage() + "at state - " + state);
            }
        } else if (action.equals("exitGA")) {
            try {
                //alert("exitGA");
                /*GAServiceManager.getInstance().dispatch(); */
                GAServiceManager.getInstance().dispatchLocalHits();
                callback.success("exitGA");
                return true;
            } catch (final Exception e) {
                //alert("exception e = " + e);
                callback.error("exitGA error :: " + e.getMessage());
            }
        } else if (action.equals("trackEvent")) {
            String state = "1";
            try {
                state = "2";
                //alert("trackEvent");
                /*tracker.sendEvent(args.getString(0), args.getString(1), args.getString(2), args.getLong(3));  */
                state = "3";
                tracker.send(MapBuilder.createEvent(args.getString(0), args.getString(1), args.getString(2), args.getLong(3)).build());
                state = "4";
                callback.success("trackEvent - category = " + args.getString(0) + "; action = " + args.getString(1) + "; label = " + args.getString(2) + "; value = " + args.getInt(3));
                state = "5";
                return true;
            } catch (final Exception e) {
                //alert("exception e = " + e);
                callback.error("trackEvent error :: " + e.getMessage() + "at state - " + state);
            }
        } else if (action.equals("trackPage")) {
            try {
                //alert("trackPage");
                /*tracker.sendView(args.getString(0));*/
                tracker.send(MapBuilder.createAppView().set(Fields.SCREEN_NAME,args.getString(0)).build());
                callback.success("trackPage - url = " + args.getString(0));
                return true;
            } catch (final Exception e) {
                //alert("exception e = " + e);
                callback.error("trackPage error :: " + e.getMessage());
            }
        } else if (action.equals("setVariable")) {
            try {
                /*tracker.setCustomDimension(args.getInt(0), args.getString(1));*/
                tracker.set(Fields.customDimension(args.getInt(0)), args.getString(1));

                callback.success("setVariable passed - index = " + args.getInt(0) + "; value = " + args.getString(1));
                return true;
            } catch (final Exception e) {
                //alert("exception e = " + e);
                callback.error("setVariable error :: " + e.getMessage());
            }
        }
        else if (action.equals("setDimension")) {
            try {
                /*tracker.setCustomDimension(args.getInt(0), args.getString(1));*/
                tracker.set(Fields.customDimension(args.getInt(0)), args.getString(1));
                callback.success("setDimension passed - index = " + args.getInt(0) + "; value = " + args.getString(1));
                return true;
            } catch (final Exception e) {
                //alert("exception e = " + e);
                callback.error("setDimension error :: " + e.getMessage());
            }
        }
        else if (action.equals("setMetric")) {
            try {
                /*tracker.setCustomMetric(args.getInt(0), args.getLong(1));*/
                tracker.set(Fields.customMetric(args.getInt(0)), args.getString(1));
                callback.success("setVariable passed - index = " + args.getInt(2) + "; key = " + args.getString(0) + "; value = " + args.getString(1));
                return true;
            } catch (final Exception e) {
                //alert("exception e = " + e);
                callback.error("setMetric error :: " + e.getMessage());
            }
        }
        return false;
    }
}