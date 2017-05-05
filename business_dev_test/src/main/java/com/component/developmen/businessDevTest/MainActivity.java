package com.component.developmen.businessDevTest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String ACTION_XRZ_BUSINESS_MIAN = "xrz.intent.action.business.main";
    private ListView listView;
    private MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dev_activity_main);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new MyAdapter(this);
        listView.setAdapter(adapter);
        Intent intent = new Intent();
        intent.setAction(ACTION_XRZ_BUSINESS_MIAN);
        PackageManager pm = getPackageManager();
        List<ResolveInfo> infos = getPackageManager()
                .queryIntentActivities(intent, PackageManager.MATCH_ALL);
        if (infos == null
                || infos.size() <= 0) {
            return;
        }


        List<AppInfo> mlistAppInfo = new ArrayList<>();
        for (ResolveInfo reInfo : infos) {
            String activityName = reInfo.activityInfo.name; // 获得该应用程序的启动Activity的name
            String pkgName = reInfo.activityInfo.packageName; // 获得应用程序的包名
            String appLabel = (String) reInfo.loadLabel(pm); // 获得应用程序的Label
            Drawable icon = reInfo.loadIcon(pm); // 获得应用程序图标
            // 为应用程序的启动Activity 准备Intent
            Intent launchIntent = new Intent();
            launchIntent.setComponent(new ComponentName(pkgName,
                    activityName));
            // 创建一个AppInfo对象，并赋值
            AppInfo appInfo = new AppInfo();
            appInfo.setAppLabel(appLabel);
            appInfo.setPkgName(pkgName);
            appInfo.setAppIcon(icon);
            appInfo.setActivityName(activityName);
            appInfo.setIntent(launchIntent);
            mlistAppInfo.add(appInfo); // 添加至列表中
            System.out.println(appLabel + " activityName---" + activityName
                    + " pkgName---" + pkgName);
        }
        adapter.getList().clear();
        adapter.getList().addAll(mlistAppInfo);
        adapter.notifyDataSetChanged();
    }

    public class MyAdapter extends BaseAdapter {
        private List<AppInfo> list = new ArrayList<>();
        private LayoutInflater inflater;
        private Context context;

        public List<AppInfo> getList() {
            return list;
        }

        public MyAdapter(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public AppInfo getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHold viewHold;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.dev_item_layout, null);
                viewHold = new ViewHold();
                viewHold.tv_1 = (TextView) convertView.findViewById(R.id.tv_1);
                viewHold.tv_2 = (TextView) convertView.findViewById(R.id.tv_2);
                convertView.setTag(viewHold);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppInfo info = (AppInfo) v.getTag(R.id.dev_tag_model);
                        startActivity(info.getIntent());
                    }
                });
            } else {
                viewHold = (ViewHold) convertView.getTag();
            }
            AppInfo info = getItem(position);

            viewHold.tv_1.setText(info.getActivityName());
            viewHold.tv_2.setText(info.getAppLabel());
            convertView.setTag(R.id.dev_tag_model, info);
            return convertView;
        }

        private class ViewHold {
            protected TextView tv_1;
            protected TextView tv_2;
        }
    }

    public class AppInfo {
        private String appLabel;
        private String pkgName;
        private Drawable appIcon;
        private String activityName;
        private Intent intent;

        public Intent getIntent() {
            return intent;
        }

        public void setIntent(Intent intent) {
            this.intent = intent;
        }

        public String getAppLabel() {
            return appLabel;
        }

        public void setAppLabel(String appLabel) {
            this.appLabel = appLabel;
        }

        public String getPkgName() {
            return pkgName;
        }

        public void setPkgName(String pkgName) {
            this.pkgName = pkgName;
        }

        public Drawable getAppIcon() {
            return appIcon;
        }

        public void setAppIcon(Drawable appIcon) {
            this.appIcon = appIcon;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }
    }

}
