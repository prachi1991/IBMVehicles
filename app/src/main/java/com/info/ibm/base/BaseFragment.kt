package com.info.ibm.base

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    lateinit var holdingActivity: BaseActivity


    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.holdingActivity = (context as BaseActivity?)!!
    }

    override fun onResume() {
        super.onResume()
    }


    /**
     * add fragment
     *
     * @param fragment
     * @param frameId
     */
    fun addFragment(fragment: BaseFragment, @IdRes frameId: Int, backstack: Boolean) {
        //AppConstants.checkNotNull(fragment);
        this.holdingActivity.addFragment(fragment, frameId, backstack)

    }


    /**
     * replace fragment
     *
     * @param fragment
     * @param frameId
     */
    fun replaceFragment(fragment: BaseFragment, @IdRes frameId: Int, backstack: Boolean) {
        // AppConstants.checkNotNull(fragment);
        holdingActivity.replaceFragment(fragment, frameId, backstack)
    }

    fun replaceNewFragment(fragment: BaseFragment, @IdRes frameId: Int, backstack: Boolean) {
        holdingActivity.replaceNewFragment(fragment, frameId, backstack)

    }

    fun openFragment(fragment: BaseFragment) {
        //holdingActivity.openFragment(fragment)
    }

    /**
     * hide fragment
     *
     * @param fragment
     */
    fun hideFragment(fragment: BaseFragment) {
        //AppConstants.checkNotNull(fragment);
        holdingActivity.hideFragment(fragment)
    }


    /**
     * show fragment
     *
     * @param fragment
     */
    protected fun showFragment(fragment: BaseFragment) {
        // AppConstants.checkNotNull(fragment);
        holdingActivity.showFragment(fragment)
    }


    /**
     * remove Fragment
     *
     * @param fragment
     */
    protected fun removeFragment(fragment: BaseFragment) {
        // AppConstants.checkNotNull(fragment);
        holdingActivity.removeFragment(fragment)

    }


    /**
     * pop fragment from the top of stack
     */
    protected fun popFragment() {
        holdingActivity.popFragment()
    }


    fun hideSoftKeyBoard() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if (imm.isAcceptingText && requireActivity().currentFocus != null) { // verify if the soft keyboard is open
            imm.hideSoftInputFromWindow(requireActivity().currentFocus!!.windowToken, 0)
        }
    }
}