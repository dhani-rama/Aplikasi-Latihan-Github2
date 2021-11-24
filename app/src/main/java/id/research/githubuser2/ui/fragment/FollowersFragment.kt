package id.research.githubuser2.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.research.githubuser2.R
import id.research.githubuser2.databinding.FragmentFollowersBinding


class FollowersFragment : Fragment() {

    private lateinit var mFollowersBinding : FragmentFollowersBinding

//    companion object{
//        private const val ARG_SECTION_NUMBER = "section_number"
//
//        @JvmStatic
//        fun  newInstance(index: Int) = FollowersFragment().apply {
//            arguments = Bundle().apply {
//                putInt(ARG_SECTION_NUMBER, index)
//            }
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mFollowersBinding = FragmentFollowersBinding.inflate(inflater, container, false)
        return mFollowersBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val index =  arguments?.getInt(ARG_SECTION_NUMBER, 0)
//        mFollowersBinding.sectionLabel.text = getString(R.string.content_tab_text, index)
    }

}