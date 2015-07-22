Makerthings Android Application
=====================


### Introduction
Makerthings Android application is a native client for Makerthings server. It uses REST API of Makerthings to render
sitemaps of your Makerthings (also available for [openHAB](https://github.com/openhab/openhab), which is the basic framework). It also supports [OpenHab cloud service](https://my.openhab.org) including push notifications.

### Features
- See items connected by Raspberry Pi in cloud
- Built based on openHAB platform, which you can develop your own home automation
- Use your own account specifically for Makerspace
- See changes made by other peers and get notifications
- Write NFC tag, openHAB information, etc

### Download and Use
1. Download app in [Google Play Store](https://play.google.com/store/apps/details?id=me.willowcheng.makerthings)
2. Sign up and log in your account, and it can also be used by [MakerRepo](http://makerepo.com)
3. By default, you should be able to see Makerspace sitemap and you're ready to play around with items
4. If you want to use your own server, go to settings from action bar and set `Server URL` (e.g. http://192.168.0.3:8080)
5. For settings
  - Demo Mode, will set sitemaps from openHAB official demo server, you can also go to [http://demo.openhab.org:9080/ui/index.html#/control](http://demo.openhab.org:9080/ui/index.html#/control) to view it
  - Display Timer, normally appear when connection time out 
  - Full Screen, hiden status bar for main screen
  - Ring Tone, for customizing your favorite ringtone for notification
6. Voice control will be available only if Google voice is available, a voice widget for Makerthings is also available so you don't need to get access to voice from your app
7. NFC writing can be done when you long click on switch item, shutter item, etc (Please note this feature is only available for NFC enabled Android phone)
8. About screen is accessible from action bar. You're always welcome to send me the feedback.

### Development
If you want to contribute to Android application we are here to help you to set up
development environment. openHAB Android app is developed using Android studio and also can be
build with maven.

- Download and install [Android Studio](http://developer.android.com/sdk/installing/studio.html)
- After installation launch Android Studio, download and install Android SDK version 4.0.3 (Tools ->
Android -> SDK Manager)
- Check out the lasted code of develop branch from github
- Open the project in Android Studio (File -> Open, select project folder)

You are ready to contribute!

---------
Liu Cheng, University of Ottawa
