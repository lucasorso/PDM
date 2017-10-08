package com.burguer.zap.burguer.properties;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static com.burguer.zap.burguer.properties.WS_PROPERTY.BASE_URL;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by LucasOrso on 10/5/17.
 */

@Retention(SOURCE)
@StringDef({BASE_URL})
public @interface WS_PROPERTY {
    String BASE_URL = "http://app.nextcodeapp.com.br";
}
