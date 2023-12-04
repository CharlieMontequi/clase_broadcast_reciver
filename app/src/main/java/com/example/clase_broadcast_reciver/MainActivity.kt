package com.example.clase_broadcast_reciver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.aware.WifiAwareManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var mostrarEstadoConexion: TextView
    // lo primero es crear el objeto que va a tener asociado al broadcastereciver
    // para ello se instancia el objeto en concreto usando una isntancia anonima del broacastereciver
    // dentro del propio  broadcast tiene el metodo onRecive para establecer el intent que señalara a la conexion en este caso
    private val estadoConexionWifi= object :BroadcastReceiver(){

        // sobreescribir el metodo on recive que establece el intent con el que se obtendrá la información

        override fun onReceive(p0: Context?, intento: Intent?) {
            mostrarEstadoConexion.text="sfsdfwefrwerwerwer al Wifi"
            val conectado : Boolean = intento!!.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, true)
                //.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, true)
                //intent.getBooleanExtra("conectado", false)

            if(conectado==true){
                mostrarEstadoConexion.text="Conectado al Wifi"
            }else{
                mostrarEstadoConexion.text="No hay conexión"
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mostrarEstadoConexion = findViewById(R.id.txtV_estadoConexion)


        //se establece la suscripción(conexion) al wifi para ver si esta conectado o no y si se tiene los permisos correspondientes
        // OJO- supuestamente estan dados en el manifest
        // RE OJO!!!!!- SIEMPRE QUE SE HAGA UNA SUSCRIPCION A ALGUNA ELEMENTO EXTERNO HAY UE DESCONECTARSE DESPUES EN EL ON DESTROY
      //  registerReceiver(estadoConexionWifi, IntentFilter(ConnectivityManager.EXTRA_NETWORK))
        registerReceiver(estadoConexionWifi, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))// con esto al menos entra en elbradcastreciver pero esta deprecated
    }

    override fun onDestroy() {
        super.onDestroy()
        // anula el registro de la conexion, es decir, deja se saber el estado de la conecion y quita el puntero que lo señala
        unregisterReceiver(estadoConexionWifi)
    }
}