package com.burguer.zap.burguer.properties;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static com.burguer.zap.burguer.properties.APP_PROPS.MANAGER;
import static com.burguer.zap.burguer.properties.APP_PROPS.USER;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by LucasOrso on 10/5/17.
 */

@Retention(SOURCE)
@IntDef({USER, MANAGER})
public @interface APP_PROPS {
    int USER = 98;
    int MANAGER = 99;
}
