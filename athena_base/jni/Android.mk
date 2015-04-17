LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE    := core-1.0.0
LOCAL_SRC_FILES := core-1.0.0.c
LOCAL_LDLIBS += -L$(SYSROOT)/usr/lib -llog   # careful sequence
include $(BUILD_SHARED_LIBRARY)
APP_PLATFORM := android-8