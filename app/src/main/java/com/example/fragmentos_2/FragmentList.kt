package com.example.fragmentos_2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment

class FragmentList : Fragment() {
    private lateinit var lista: ListView
    private val so = arrayOf("Windows", "MacOS", "Linux", "Debian", "Android", "Solaris", "RedHat",
        "Fedora", "CentOS", "Ubuntu", "Kali", "Tiles", "iOS", "Mint", "Windows Phone")

    private val imgso = arrayOf(R.drawable.windows_logo, R.drawable.macos_logo, R.drawable.linux_logo, R.drawable.debian_logo, R.drawable.android_logo, R.drawable.solaris_logo,
        R.drawable.redhat_logo, R.drawable.fedra_logo, R.drawable.centos_logo, R.drawable.ubuntu_logo, R.drawable.kali_logo, R.drawable.tiles_logo,
        R.drawable.ios_logo, R.drawable.mint_logo, R.drawable.winphone_logo)

    private val desc = arrayOf(
        "Un sistema operativo popular desarrollado por Microsoft.",
        "Un sistema operativo para las computadoras Macintosh de Apple.",
        "Una familia de sistemas operativos de código abierto similares a Unix.",
        "Un sistema operativo similar a Unix compuesto enteramente de software libre.",
        "Un sistema operativo móvil desarrollado por Google.",
        "Un sistema operativo Unix originalmente desarrollado por Sun Microsystems.",
        "Un sistema operativo empresarial de código abierto.",
        "Una distribución de Linux desarrollada por el proyecto comunitario Fedora.",
        "Una distribución de Linux que proporciona una plataforma informática gratuita y apoyada por la comunidad.",
        "Una distribución popular de Linux basada en Debian.",
        "Una distribución de Linux derivada de Debian diseñada para la informática forense y las pruebas de penetración.",
        "Una implementación de código abierto del marco de rastreo dinámico DTrace.",
        "Un sistema operativo móvil creado y desarrollado por Apple Inc.",
        "Una distribución de Linux impulsada por la comunidad basada en Ubuntu.",
        "Un sistema operativo móvil descontinuado desarrollado por Microsoft."
    )

    private val list: ArrayList<String> = ArrayList(so.toList())
    private val lists: ArrayList<Int> = ArrayList(imgso.toList())
    private val desclist: ArrayList<String> = ArrayList(desc.toList())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     val view = inflater.inflate(R.layout.fragment_list, container, false)
     lista = view.findViewById(R.id.main_activity_lista)

     val adapter = CustomAdapter(
         requireActivity(),
         list, lists
     )
        lista.setAdapter(adapter)
        lista.onItemClickListener = AdapterView.OnItemClickListener{ adapterView, view, i, l ->
            cargar_actividad(list[i], lists[i], desclist[i])
        }
        return view
    }

    fun cargar_actividad (item: String, imgitem: Int, descitem: String){

        val detail = requireActivity().supportFragmentManager.findFragmentById(R.id.activity_main_detail) as FragmentDetail?
        if (detail != null && detail.isInLayout){
            detail.cargar_imgdetalle(imgitem)
            detail.cargar_detalle(item)
            detail.cargar_descdetalle(descitem)
        } else {
            val intent = Intent(activity, ActivityDetail::class.java)
            intent.putExtra("ITEM", item)
            intent.putExtra("IMAGE", imgitem)
            intent.putExtra("DESC", descitem)
            startActivity(intent)
        }
    }
}