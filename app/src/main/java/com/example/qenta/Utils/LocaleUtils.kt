package com.example.qenta.Utils

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.preference.PreferenceManager
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class LocaleUtils {

    companion object {
        private const val SELECTED_LANGUAGE = "SelectedLanguage"
        const val EN_LANGUAGE = "en"
        const val ES_LANGUAGE = "es"
        const val FR_LANGUAGE = "fr"

        fun onAttach(context: Context): Context {
            val lang =
                getPersistedData(
                    context,
                    Locale.getDefault().language
                )
            return setLocale(
                context,
                lang
            )
        }

        fun onAttach(context: Context, defaultLanguage: String): Context {
            val lang =
                getPersistedData(
                    context,
                    defaultLanguage
                )
            return setLocale(
                context,
                lang
            )
        }

        fun setLocale(context: Context, language: String?): Context {
            persist(
                context,
                language
            )

            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                updateResources(
                    context,
                    language
                )
            } else updateResourcesLegacy(
                context,
                language
            )

        }

        fun getLocale(context: Context): String{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getString(
                SELECTED_LANGUAGE,
                ES_LANGUAGE
            )
        }

        private fun getPersistedData(context: Context, defaultLanguage: String): String {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getString(SELECTED_LANGUAGE, defaultLanguage)
        }

        private fun persist(context: Context, language: String?) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = preferences.edit()

            editor.putString(SELECTED_LANGUAGE, language)
            editor.apply()
        }

        @TargetApi(Build.VERSION_CODES.N)
        private fun updateResources(context: Context, language: String?): Context {
            val locale = Locale(language)
            Locale.setDefault(locale)

            val configuration = context.resources.configuration
            configuration.setLocale(locale)
            configuration.setLayoutDirection(locale)

            return context.createConfigurationContext(configuration)
        }

        @SuppressLint("ObsoleteSdkInt")
        private fun updateResourcesLegacy(context: Context, language: String?): Context {
            val locale = Locale(language)
            Locale.setDefault(locale)

            val resources = context.resources

            val configuration = resources.configuration
            configuration.locale = locale
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLayoutDirection(locale)
            }

            resources.updateConfiguration(configuration, resources.displayMetrics)

            return context
        }
    }
}