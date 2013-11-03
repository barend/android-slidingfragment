#!/bin/sh
./gradlew assembleDebug installDebug
adb shell am start -n "nl.garvelink.oss.slidingfragment/nl.garvelink.oss.slidingfragment.MainActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER
