package android.edu.permisos;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    ImageView imagenPicasso;
    final static String urlImagen ="https://cdn.elgrupoinformatico.com/Noticias/2017/12/jaja-inocentes-550x312.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String [] array_permisos=new String[3];
        array_permisos[0]=Manifest.permission.GET_ACCOUNTS;
        array_permisos[1]=Manifest.permission.WRITE_EXTERNAL_STORAGE;
        array_permisos[2]=Manifest.permission.INTERNET;
        ActivityCompat.requestPermissions(this,array_permisos,100);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (grantResults[0]==PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,permissions[0]+"--> CONCEDIDO",Toast.LENGTH_SHORT).show();
            obtenerCorreos();
        }
        else {
            Toast.makeText(this,permissions[0]+"--> DENEGADO",Toast.LENGTH_SHORT).show();
            //finish();
        }
        if (grantResults[1]==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,permissions[1]+"--> CONCEDIDO",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,permissions[1]+"--> DENEGADO",Toast.LENGTH_SHORT).show();
        }
        if (grantResults[2]==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,permissions[2]+"--> DENEGADO",Toast.LENGTH_SHORT).show();
            cargarImagenPicasso();
        }
        else {
            Toast.makeText(this,permissions[2]+"--> DENEGADO",Toast.LENGTH_SHORT).show();
        }
        //Quiero gestionarlo yo por eso quito la linea siguiente
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void obtenerCorreos ()
    {   //Obtener cuentas de usuario y las mostramos
        //Existe un objeto que ya lo gestiona que es AccountManager
        AccountManager accountManager;
        //Es un servicio del sistema
        accountManager= (AccountManager) getSystemService(ACCOUNT_SERVICE);
        //Con getAccounts saco las cuentas y viene en un array de cuentas
        Account[] array_cuentas=accountManager.getAccounts();

        for (Account cuenta: array_cuentas) //Para cada cuenta que estey en el array
        {
            Log.d ("JNG", "Nombre: "+cuenta.name);
            Log.d ("JNG", "tipo: "+cuenta.type);
        }
    }
    private void cargarImagenPicasso ()
    {   imagenPicasso =findViewById(R.id.imagen);
        Picasso.with(this)
                .load(urlImagen)
                // .resize(450,450)
                //.centerCrop()
                .into(imagenPicasso);
    }
}