package com.example.multilanguagesupport

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.preference.PreferenceManager
import androidx.annotation.StringDef
import java.util.*


 open class LocationManager {
    companion object
    {
        @Retention(AnnotationRetention.SOURCE)
          //@StringDef(ENGLISH,HINDI,SPANISH)
        public annotation class LocaleDef {
            companion object {
                var SUPPORTED_LOCALES =
                     arrayOf<String>(ENGLISH, HINDI, SPANISH)
            }
        }

        var ENGLISH = "en"
        var HINDI = "hi"
        var SPANISH = "es"
        private val LANGUAGE_KEY = "language_key"
        fun setLocale(mContext: Context?): Context? {
            return updateResources(mContext!!, getLanguagePref(mContext)!!)
        }
        fun setNewLocale(
            mContext: Context?,
            @LocaleDef language: String?
        ): Context? {
            setLanguagePref(mContext!!, language!!)
            return updateResources(mContext, language)
        }
        fun getLanguagePref(mContext: Context?): String? {
            val mPreferences =
                PreferenceManager.getDefaultSharedPreferences(mContext)
            return mPreferences.getString(LANGUAGE_KEY, ENGLISH)
        }
        private fun setLanguagePref(
            mContext: Context,
            localeKey: String
        ) {
            val mPreferences =
                PreferenceManager.getDefaultSharedPreferences(mContext)
            mPreferences.edit().putString(LANGUAGE_KEY, localeKey).apply()
        }
        private fun updateResources(
            context: Context,
            language: String
        ): Context? {
            var context = context
            val locale = Locale(language)
            Locale.setDefault(locale)
            val res: Resources = context.resources
            val config = Configuration(res.getConfiguration())
            if (Build.VERSION.SDK_INT >= 17) {
                config.setLocale(locale)
                context = context.createConfigurationContext(config)
            } else {
                config.locale = locale
                res.updateConfiguration(config, res.getDisplayMetrics())
            }
            return context
        }
        fun getLocale(res: Resources): Locale? {
            val config = res.configuration
            return if (Build.VERSION.SDK_INT >= 24) config.locales[0] else config.locale
        }
    }

}