package com.counting.kidsgame.tools;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.Iterator;

public class RedirectManager {


    public static void rateUs(Context context) {
        String packageName = context.getPackageName();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName));
        intent.setFlags(268435456);
        boolean z = false;
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intent, 0).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ResolveInfo next = it.next();
            if (next.activityInfo.applicationInfo.packageName.equals("com.android.vending")) {
                ActivityInfo activityInfo = next.activityInfo;
                ComponentName componentName = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
                intent.addFlags(268435456);
                intent.addFlags(2097152);
                intent.addFlags(67108864);
                intent.setComponent(componentName);
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                }
                z = true;
            }
        }
        if (!z) {
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + packageName));
            intent2.setFlags(268435456);
            if (intent2.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent2);
            }
        }
    }

    public static void showAppInStore(Context context, String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str));
            intent.setFlags(268435456);
            boolean z = false;
            Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intent, 0).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ResolveInfo next = it.next();
                if (next.activityInfo.applicationInfo.packageName.equals("com.android.vending")) {
                    ActivityInfo activityInfo = next.activityInfo;
                    ComponentName componentName = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
                    intent.addFlags(268435456);
                    intent.addFlags(2097152);
                    intent.addFlags(67108864);
                    intent.setComponent(componentName);
                    if (intent.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(intent);
                    }
                    z = true;
                }
            }
            if (!z) {
                Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + str));
                intent2.setFlags(268435456);
                if (intent2.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
