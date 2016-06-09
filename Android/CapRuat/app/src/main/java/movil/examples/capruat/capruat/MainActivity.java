package movil.examples.capruat.capruat;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class MainActivity extends Activity {

    public TextView lblRespuestaRest, lblRespuestaSoap;
    Button btnRegistroRest, btnRegistroSoap;
    EditText txtNombre,txtDireccion,txtTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        lblRespuestaRest = (TextView) findViewById(R.id.lblRespuestaRest);
        lblRespuestaSoap = (TextView) findViewById(R.id.lblRespuestaSoap);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNombre = (EditText)findViewById(R.id.txtNomCliente);
        txtDireccion = (EditText)findViewById(R.id.txtDirCliente);
        txtTelefono = (EditText)findViewById(R.id.txtTelCliente);
        btnRegistroRest = (Button)findViewById(R.id.btnRegistroRest);
        btnRegistroSoap = (Button)findViewById(R.id.btnRegistroSOAP);

        btnRegistroRest.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Log.e("txtNombre",txtNombre.getText().toString());
                Log.e("txtDireccion",txtDireccion.getText().toString());
                Log.e("txtTelefono",txtTelefono.getText().toString());
                TareaWSInsertar tarea = new TareaWSInsertar();
                tarea.execute(txtNombre.getText().toString(),
                                txtDireccion.getText().toString(),
                                txtTelefono.getText().toString());
                return true;

            }

        });
        btnRegistroSoap.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e("txtNombre",txtNombre.getText().toString());
                Log.e("txtDireccion",txtDireccion.getText().toString());
                Log.e("txtTelefono",txtTelefono.getText().toString());
                TareaSoapInsertar tarea = new TareaSoapInsertar();
                tarea.execute(txtNombre.getText().toString(),
                        txtDireccion.getText().toString(),
                        txtTelefono.getText().toString());
                return true;
            }
        });
    }

    private class TareaWSInsertar extends AsyncTask<String,String,String>{
        @Override
        protected void onPostExecute(String aString) {
            lblRespuestaRest = (TextView) findViewById(R.id.lblRespuestaRest);
            lblRespuestaRest.setText(aString);
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://192.168.0.101:8080/CapRuat-web/rest/cliente/registro");
            post.setHeader("content-type","application/json");
            try{
                JSONObject dato = new JSONObject();
                dato.put("nombreCliente",params[0]);
                dato.put("direccionCliente",params[1]);
                dato.put("telefonoCliente",params[2]);
                StringEntity entity = new StringEntity(dato.toString());
                post.setEntity(entity);
                HttpResponse resp = httpClient.execute(post);
                String respStr = EntityUtils.toString(resp.getEntity());
                result = respStr;
            }catch (Exception ex){
                Log.e("Servicio Rest","Error: " + ex.getMessage());
            }
            return result;
        }
    }

    private class TareaSoapInsertar extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... params) {
            String NAMESPACE = "http://soap.CapRuat/";
            String URL = "http://192.168.0.101:8080/CapRuat-web/RegistroClienteSoap";
            String METHOD_NAME = "registroCliente";
            String SOAP_ACTION = "";
            SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);
            request.addProperty("nombreCliente",params[0]);
            request.addProperty("direccionCliente",params[1]);
            request.addProperty("telefonoCliente",params[2]);

            SoapSerializationEnvelope envolpe = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envolpe.dotNet = false;
            envolpe.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL);
            String respuesta = "";

            try {
                Object obRes;

                transporte.call(SOAP_ACTION, envolpe);
                SoapObject resSoap = (SoapObject)envolpe.getResponse();
                for (int i=0;i<resSoap.getPropertyCount();i++){
                    System.out.println("*************************************VALOR*********************************************");
                    System.out.println("valor:" + i + " Respuesta: " + resSoap.getProperty(i) );
                }
                obRes = resSoap.getProperty(1);
            }catch (Exception ex){
                System.out.println("--------------------------------ERROR-----------------------------------");
                System.out.println("Error: " + ex.getMessage());
            }
            return respuesta;

        }

        @Override
        protected void onPostExecute(String s) {
            lblRespuestaSoap = (TextView) findViewById(R.id.lblRespuestaSoap);
            lblRespuestaSoap.setText(s);
        }
    }

}
