package com.artem.notepad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artem.notepad.adapter.DataAdapter
import com.artem.notepad.dagger.DaggerAppComponent
import com.artem.notepad.viewModel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : Fragment() {

    private lateinit var mNoteViewModel: NoteViewModel

    @Inject
    lateinit var  adapter:DataAdapter

    lateinit var recyclerView: RecyclerView
    private lateinit var rootView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView = rootView.findViewById(R.id.list_recycle_view)


        DaggerAppComponent.create().injectMainFragment(this)



        recyclerView.layoutManager = LinearLayoutManager(activity)//Вертикальный список
        recyclerView.adapter = adapter                            //Новый адаптер для установки

        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))//Линия разделение
                                                                                                   //между элементами списка

          mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
          mNoteViewModel.readAllData.observe(viewLifecycleOwner, { note -> adapter.setData(note) })

        return rootView
    }

    override fun onStart() {
        super.onStart()

        Btn_create_note.setOnClickListener{
            //Слушатель на кнопке для переключения между фрагментами
            findNavController().navigate(R.id.action_mainFragment_to_createNoteFragment)
        }
        
    }
    
}