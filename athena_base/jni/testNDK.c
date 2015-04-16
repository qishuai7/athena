#include <string.h>
#include <jni.h>

jstring
Java_com_alibaba_athena_1base_ALJniActivity_stringTestNdk( JNIEnv* env,
                                                  jobject thiz )
{
    return (*env)->NewStringUTF(env, "Hello Test NDK !");
}
