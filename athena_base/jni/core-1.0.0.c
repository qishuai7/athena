#include <stdio.h>
#include <string.h>
#include <jni.h>
#include "com_alibaba_athena_base_ALJniActivity.h"
#include <android/log.h>

jstring
Java_com_alibaba_athena_1base_ALJniActivity_stringTestNdk( JNIEnv* env,
                                                  jobject thiz )
{
    return (*env)->NewStringUTF(env, "Hello Test NDK !");
}

jstring
Java_com_alibaba_athena_1base_ALJniActivity_decompressAndUnzip
  (JNIEnv* env, jobject thiz, jstring inputStr)
{
	const char *str = (*env)->GetStringUTFChars(env, inputStr, 0);
	printf("%s", str);
	__android_log_print(ANDROID_LOG_INFO,"JNI","my = %s", str);
	(*env)->ReleaseStringUTFChars(env, inputStr, str);


	 return (*env)->NewStringUTF(env, str);
}
