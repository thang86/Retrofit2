# Retrofit2

# Retrofit 2 example

Khai báo dependency
```sh
// Retrofit
 implementation 'com.squareup.retrofit2:retrofit:2.1.0'
 
// JSON Parsing
 implementation 'com.google.code.gson:gson:2.6.1'
 implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
 
// recyclerview 
 implementation 'com.android.support:recyclerview-v7:27.1.1'
```
  Tiếp theo để thực hiện cần quyền INTERNET trong AndroidManifest.xml .
```sh
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="io.github.thang86.retrofit2">
    <uses-permission android:name="android.permission.INTERNET" />
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```
----
