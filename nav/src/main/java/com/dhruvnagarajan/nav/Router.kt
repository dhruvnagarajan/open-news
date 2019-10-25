package com.dhruvnagarajan.nav

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * @author Dhruv
 */
abstract class Router {

    open class Request

    data class FragmentRequest<F : Fragment, A : AppCompatActivity>(
        val fragment: F,
        val containingActivity: A? = null,
        val stackAction: StackAction,
        val bundle: Bundle? = null
    ) : Request()

    /**
     * @param requestCode if expecting a result, provide request code
     */
    data class ActivityRequest<A : AppCompatActivity>(
        val activity: A,
        val requestCode: Int = 0,
        val bundle: Bundle? = null,
        val intentFilter: IntentFilter? = null
    ) : Request()

    sealed class StackAction {
        object REPLACE_CURRENT : StackAction()

        /**
         * @param targetTag either provide target fragment tag as string
         * @param targetPosition or provide desired position in the stack
         */
        data class REPALCE_TARGET(val targetTag: String? = null, val targetPosition: Int = -1) :
            StackAction()

        object ADD_TO_TOP : StackAction()

        object ADD_TO_ORIGIN : StackAction()
    }
}