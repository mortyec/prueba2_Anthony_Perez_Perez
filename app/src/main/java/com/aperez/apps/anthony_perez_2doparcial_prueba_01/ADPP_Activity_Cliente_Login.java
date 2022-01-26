package com.aperez.apps.anthony_perez_2doparcial_prueba_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ADPP_Activity_Cliente_Login extends AppCompatActivity {

    private EditText editTextCodigo;
    private EditText editTextNombre;
    private EditText editTextApellido;
    private EditText editTextUsuario;
    private EditText editTextContraseña;

    private EditText editTextLoginUsuario;
    private EditText editTextLoginContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adpp_cliente_login);

        editTextCodigo = findViewById(R.id.editTextCodigo);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellido = findViewById(R.id.editTextApellido);
        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextContraseña = findViewById(R.id.editTextContraseña);

        editTextLoginUsuario = findViewById(R.id.editTextLoginUsuario);
        editTextLoginContraseña = findViewById(R.id.editTextLoginContraseña);

    }

    public void onClicInsertar(View view){
        this.InsertarConClase();
    }
    private void InsertarConClase() {

        ADPP_ClienteDAL clienteDAL = new ADPP_ClienteDAL(this);

        String nombre = editTextNombre.getText().toString();
        String apellido = editTextApellido.getText().toString();
        String usuario = editTextUsuario.getText().toString();
        String contraseña = editTextContraseña.getText().toString();

        if(!nombre.equals("") && !apellido.equals("") && !usuario.equals("")&& !contraseña.equals("")){
            ADPP_Cliente cliente = new ADPP_Cliente();
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setUsuario(usuario);
            cliente.setContraseña(contraseña);

            clienteDAL.insertDAL(cliente);

            editTextNombre.setText("");
            editTextApellido.setText("");
            editTextUsuario.setText("");
            editTextContraseña.setText("");

            Toast.makeText(this, "Cliente insertado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Todos los campos son Obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClicEditar(View view){
        this.editarConClase();

    }
    public void editarConClase(){

        ADPP_ClienteDAL clienteDAL = new ADPP_ClienteDAL(this);
        String codigo = editTextCodigo.getText().toString();
        String nombre = editTextNombre.getText().toString();
        String apellido = editTextApellido.getText().toString();
        String usuario = editTextUsuario.getText().toString();
        String contraseña = editTextContraseña.getText().toString();

        ADPP_Cliente cliente = new ADPP_Cliente();
        cliente.setCodigo(Integer.valueOf(codigo));
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setUsuario(usuario);

        int cantidad=clienteDAL.updateDAL(cliente);

        if(cantidad>0){
            Toast.makeText(this, "Registro Modificado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "El registro no se pudo Modificar", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClicEliminar(View view){
        this.eliminarConClase();
    }
    public void eliminarConClase(){

        ADPP_ClienteDAL clienteDAL = new ADPP_ClienteDAL(this);
        String codigo = editTextCodigo.getText().toString();

        int cantidad = clienteDAL.deleteDAL(Integer.valueOf(codigo));

        if(cantidad>0){
            Toast.makeText(this, "Registro eliminado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Registro no encontrado", Toast.LENGTH_SHORT).show();
        }

        editTextNombre.setText("");
        editTextApellido.setText("");
        editTextUsuario.setText("");
        editTextContraseña.setText("");
    }

    public void onClicBuscar(View view){
        this.buscarConClase();
    }
    public void buscarConClase(){

        ADPP_ClienteDAL clienteDAL = new ADPP_ClienteDAL(this);
        String codigo = editTextCodigo.getText().toString();

        ADPP_Cliente cliente = new ADPP_Cliente();
        cliente=clienteDAL.selectByCodigoDAL(Integer.valueOf(codigo));

        if(cliente!=null)
        {
            editTextNombre.setText(cliente.getNombre());
            editTextApellido.setText(cliente.getApellido());
            editTextUsuario.setText(cliente.getUsuario());
            editTextContraseña.setText(cliente.getContraseña());
        }
        else
        {
            Toast.makeText(this, "No se encontraron registros en la tabla", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClicLogin(View view) {
        if(editTextLoginUsuario.getText().toString().equals("") && editTextLoginContraseña.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese un usuario y contraseña correcta", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this, ADPP_MainActivity.class);
            startActivity(intent);
        }
    }
}