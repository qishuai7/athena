LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE    := testNDK
LOCAL_SRC_FILES := testNDK.c
include $(BUILD_SHARED_LIBRARY)
APP_PLATFORM := android-8