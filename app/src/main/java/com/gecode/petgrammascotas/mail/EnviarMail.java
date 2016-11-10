package com.gecode.petgrammascotas.mail;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.gecode.petgrammascotas.R;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by gregorybr on 03-11-16.
 */

public class EnviarMail extends AsyncTask<Void,Void,Void> {
    // 1. Declaramos las variables Globales:

    private Context context;
    private Session session;
    private String email, nombre, mensaje;
    private ProgressDialog pDialog;

    private String usuario = ""; // Introdusca su cuenta de gmail.
    private String passwordUsuario = ""; // Indrodusca su contraseña.

    // 2. Creamos un constructor:
    public EnviarMail(Context context, String email, String nombre, String mensaje) {
        this.context = context;
        this.email = email;
        this.nombre = nombre;
        this.mensaje = mensaje;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = ProgressDialog.show(context,"Enviando email","Por favor, espere ...",false,false);
    }

    @Override
    protected void onPostExecute(Void avoid) {
        super.onPostExecute(avoid);
        pDialog.dismiss();
        Toast.makeText(context, R.string.msg_send, Toast.LENGTH_LONG).show();


    }

    @Override
    protected Void doInBackground(Void... params) {
        // 3. Configurar las propiedades del Envio mediante la clase Properties.
        Properties props = new Properties();
        // 4. Para incorporar una propiedad utilizamos el método .put(propiedad,valor);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        // 5. Establecer la sesion con el servidor del correo:
        session =Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario,passwordUsuario);
            }
        });

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(usuario));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            msg.setSubject(nombre);
            msg.setText(mensaje);

            Transport.send(msg);

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {

            e.printStackTrace();
        }

        return null;
    }

}
