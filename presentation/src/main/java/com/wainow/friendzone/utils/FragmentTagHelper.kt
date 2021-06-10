package com.wainow.friendzone.utils

import androidx.fragment.app.FragmentManager

class FragmentTagHelper {
    companion object{
        fun getFragmentTag(manager: FragmentManager) = manager.fragments.size.toString()
    }
}