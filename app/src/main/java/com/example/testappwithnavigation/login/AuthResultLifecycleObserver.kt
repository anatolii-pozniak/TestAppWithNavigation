package com.example.testappwithnavigation.login

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class AuthResultLifecycleObserver(
    private val getContent: ActivityResultLauncher<Intent>,
) : DefaultLifecycleObserver {

    fun launchAuthActivity(context: Context) {
        getContent.launch(LoginActivity.callingIntent(context))
    }

    class Delegate(
        fragment: Fragment,
        activityResultCallback: ActivityResultCallback<ActivityResult>
    ) : ReadOnlyProperty<Fragment, AuthResultLifecycleObserver> {

        private val getContent = fragment.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            activityResultCallback
        )

        override fun getValue(
            thisRef: Fragment,
            property: KProperty<*>
        ): AuthResultLifecycleObserver {
            return AuthResultLifecycleObserver(getContent).also {
                thisRef.lifecycle.addObserver(it)
            }
        }
    }
}

fun Fragment.authResultLifecycleObserver(
    activityResultCallback: ActivityResultCallback<ActivityResult>
) = AuthResultLifecycleObserver.Delegate(this, activityResultCallback)
