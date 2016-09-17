/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.beans;

import com.udea.fnsp.buho.dao.PersonaDao;
import com.udea.fnsp.buho.dao.UsuarioDao;
import com.udea.fnsp.buho.interfaces.PersonaDaoInterface;
import com.udea.fnsp.buho.interfaces.UsuarioDaoInterface;
import com.udea.fnsp.buho.modelo.MaDepartamento;
import com.udea.fnsp.buho.modelo.MaMunicipio;
import com.udea.fnsp.buho.modelo.MaUsuario;
import com.udea.fnsp.buho.modelo.PePersona;
import com.udea.fnsp.buho.util.Rutas;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Usuario
 */
@ManagedBean
@SessionScoped
public class PersonaBean implements Serializable {

    private MaUsuario usuarioSeleccionado;
    private PePersona personaSeleccionada;
    private UploadedFile foto;
    PersonaDaoInterface personaDao = new PersonaDao();
    UsuarioDaoInterface usuarioDao = new UsuarioDao();
    Rutas rutas = new Rutas();
    private final String rutaFoto = rutas.getRutaFotoHojaDeVida();
    LoginBean loginMbean;
    CiudadesBean ciudades;
    String msg, contrasenaCambiar, contrasenaActual;
    String nombreFoto;

    public PersonaBean() {
        usuarioSeleccionado = new MaUsuario();
        personaSeleccionada = new PePersona();
//        loginMbean = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{loginBean}", LoginBean.class);
//        if (loginMbean.getUsuario().getUsId() != null) {
//            this.personaSeleccionada = this.personaDao.findByPersona(loginMbean.getUsuario().getPePersona().getPeNumeroid());
//        }
    }

    public String getContrasenaCambiar() {
        return contrasenaCambiar;
    }

    public void setContrasenaCambiar(String contrasenaCambiar) {
        this.contrasenaCambiar = contrasenaCambiar;
    }

    public String getContrasenaActual() {
        return contrasenaActual;
    }

    public void setContrasenaActual(String contrasenaActual) {
        this.contrasenaActual = contrasenaActual;
    }

    public PePersona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(PePersona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    public MaUsuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public UploadedFile getFoto() {
        return foto;
    }

    public void setFoto(UploadedFile foto) {
        this.foto = foto;
    }

    public void setUsuarioSeleccionado(MaUsuario usuarioCrear) {
        this.usuarioSeleccionado = usuarioCrear;
    }

    public void btnCreatePersonaUsuario(ActionEvent actionEvent) {
        if (ValidacionesCrearUsuario()) {
            String ClaveEncriptada;
            usuarioSeleccionado.setPePersona(this.personaSeleccionada);
            ClaveEncriptada = DigestUtils.md5Hex(this.usuarioSeleccionado.getUsClave());
            this.usuarioSeleccionado.setUsClave(ClaveEncriptada);
            this.personaSeleccionada.setPeUpdate(new Date());
            if (usuarioDao.create(this.usuarioSeleccionado, this.personaSeleccionada)) {
                msg = "Se creó correctamente el usuario";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Creación", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
                RequestContext.getCurrentInstance().execute("PF('dialogUsuarioCreate').hide()");

            } else {
                msg = "Error al crear el usuario";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Creación", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }

    }

    public void btnUpdatePersona(ActionEvent actionEvent) {

        if (ValidacionesActualizarUsuario()) {
            if (personaDao.update(this.personaSeleccionada)) {
                msg = "Se guardó correctamente la información";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                msg = "Error al guardar";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Actualización", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }

    }

    public void btnUpdateNacionalidad(ActionEvent actionEvent) {

        if (personaDao.update(this.personaSeleccionada)) {
            msg = "Se guardó correctamente la información";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.personaSeleccionada = this.personaDao.findByPersona(personaSeleccionada.getPeNumeroid());
            RequestContext.getCurrentInstance().update(":frmDatosPersona");
        } else {
            msg = "Error al guardar";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Actualización", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdateLugarResidencia(ActionEvent event) {
        ciudades = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{ciudadesBean}", CiudadesBean.class);
        if (ciudades.getCiudad() != null && ciudades.getDepartamento() != null) {
            MaMunicipio municipio = new MaMunicipio();
            MaDepartamento departamento = new MaDepartamento();
            departamento.setDptoCod(ciudades.getDepartamento());
            municipio.setCodMpio(ciudades.getCiudad());
            this.personaSeleccionada.setMaMunicipioByPeCiudadresidencia(municipio);
            this.personaSeleccionada.getMaMunicipioByPeCiudadresidencia().setMaDepartamento(departamento);
        }
        if (personaDao.update(this.personaSeleccionada)) {
            msg = "Se actualizó correctamente la ciudad de residencia";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.personaSeleccionada = this.personaDao.findByPersona(personaSeleccionada.getPeNumeroid());
            RequestContext.getCurrentInstance().update(":frmDatosPersona");
        } else {
            msg = "Error al guardar";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Actualización", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }

    public void btnUpdateLugarExpedicion(ActionEvent event) {
        ciudades = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{ciudadesBean}", CiudadesBean.class);
        if (ciudades.getCiudad() != null && ciudades.getDepartamento() != null) {
            MaMunicipio municipio = new MaMunicipio();
            MaDepartamento departamento = new MaDepartamento();
            departamento.setDptoCod(ciudades.getDepartamento());
            municipio.setCodMpio(ciudades.getCiudad());
            this.personaSeleccionada.setMaMunicipioByPeLugarexpced(municipio);
            this.personaSeleccionada.getMaMunicipioByPeLugarexpced().setMaDepartamento(departamento);

        }
        if (personaDao.update(this.personaSeleccionada)) {
            msg = "Se actualizó correctamente la ciudad de expedicion de la identificación";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.personaSeleccionada = this.personaDao.findByPersona(personaSeleccionada.getPeNumeroid());
            RequestContext.getCurrentInstance().update(":frmDatosPersona");
        } else {
            msg = "Error al guardar";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Actualización", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }

    public void btnUpdateLugarNacimiento(ActionEvent event) {
        ciudades = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{ciudadesBean}", CiudadesBean.class);
        if (ciudades.getCiudad() != null && ciudades.getDepartamento() != null) {
            MaMunicipio municipio = new MaMunicipio();
            MaDepartamento departamento = new MaDepartamento();
            departamento.setDptoCod(ciudades.getDepartamento());
            municipio.setCodMpio(ciudades.getCiudad());
            this.personaSeleccionada.setMaMunicipioByPeLugarnacimiento(municipio);
            this.personaSeleccionada.getMaMunicipioByPeLugarnacimiento().setMaDepartamento(departamento);
        }
        if (personaDao.update(this.personaSeleccionada)) {
            msg = "Se actualizó correctamente la ciudad de nacimiento";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
            this.personaSeleccionada = this.personaDao.findByPersona(personaSeleccionada.getPeNumeroid());
            RequestContext.getCurrentInstance().update(":frmDatosPersona");
        } else {
            msg = "Error al guardar";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Actualización", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
    }

    private boolean ValidacionesCrearUsuario() {
        FacesMessage message;
        String Mensaje;
        boolean flag = true;

        if ("".equals(this.personaSeleccionada.getPeNumeroid())) {
            Mensaje = "Ingrese un numero de identificación";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }
        if ("".equals(this.personaSeleccionada.getMaTipoid().getTiCod())) {
            Mensaje = "Seleccione un tipo de Identificación";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }
        if ("".equals(this.personaSeleccionada.getPeNombres())) {
            Mensaje = "Ingrese el nombre";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }
        if ("".equals(this.personaSeleccionada.getPeApellidos())) {
            Mensaje = "Ingrese los apellidos";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }
        if ("".equals(this.usuarioSeleccionado.getUsClave())) {
            Mensaje = "Ingrese una contraseña";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }
        return flag;
    }

    private boolean ValidacionesActualizarUsuario() {
        FacesMessage message;
        String Mensaje;
        boolean flag = true;

        if ("".equals(this.personaSeleccionada.getPeNombres())) {
            Mensaje = "Ingrese el nombre";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }
        if ("".equals(this.personaSeleccionada.getPeApellidos())) {
            Mensaje = "Ingrese los apellidos";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }
        if (this.personaSeleccionada.getPeFechaNac() == null) {
            Mensaje = "Ingrese una fecha de nacimiento valida";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }
        if ("".equals(this.personaSeleccionada.getPeDireccionresidencia())) {
            Mensaje = "Ingrese la direccion de su residencia";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }
        if ("".equals(this.personaSeleccionada.getPeTelefono())) {
            Mensaje = "Ingrese el telefono de su residencia";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }
        if ("".equals(this.personaSeleccionada.getPeCelular())) {
            Mensaje = "Ingrese el numero de celular";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }
        if ("".equals(this.personaSeleccionada.getPeTeloficina())) {
            Mensaje = "Ingrese un Telefono del trabajo";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }
        if ("".equals(this.personaSeleccionada.getPeEmail())) {
            Mensaje = "Ingrese un dirección de correo electronico";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }
        if ("".equals(this.personaSeleccionada.getPePerfil())) {
            Mensaje = "Ingrese su Perfil labora y Profesional";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }
        return flag;
    }

    public void CambiarContrasena(ActionEvent event) {
        if (ValidarCambioContrasena()) {
            this.contrasenaActual = DigestUtils.md5Hex(this.contrasenaActual);
            this.contrasenaCambiar = DigestUtils.md5Hex(this.contrasenaCambiar);
            if (loginMbean.getUsuario().getUsClave().equals(this.contrasenaActual)) {
                if (!this.contrasenaActual.equals(this.contrasenaCambiar)) {
                    this.usuarioSeleccionado = loginMbean.getUsuario();
                    this.usuarioSeleccionado.setUsClave(this.contrasenaCambiar);
                    if (this.usuarioDao.update(usuarioSeleccionado)) {
                        msg = "Se cambió correctamente al contraseña";
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", msg);
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    } else {
                        msg = "Error al cambiar";
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Actualización", msg);
                        FacesContext.getCurrentInstance().addMessage(null, message);
                    }
                } else {
                    msg = "La contraseña nueva no debe ser igual a la actual";
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Actualización", msg);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            } else {
                msg = "La contraseña no coincide con la actual";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Actualización", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }

    }

    private boolean ValidarCambioContrasena() {
        FacesMessage message;
        String Mensaje;
        boolean flag = true;
        if ("".equals(this.contrasenaActual)) {
            Mensaje = "Ingrese la contraseña actual";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }

        if ("".equals(this.contrasenaCambiar)) {
            Mensaje = "Ingrese la nueva contraseña";
            FacesContext.getCurrentInstance().addMessage(null, message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Validacíon", Mensaje));
            flag = false;
        }
        return flag;
    }

    public void upload() throws IOException {
        nombreFoto = this.personaSeleccionada.getPeNumeroid() + "foto.jpg";

        try {
            if (copyFile(nombreFoto, foto.getInputstream())) {
                FacesMessage msgs = new FacesMessage("foto cargada correctamente");
                FacesContext.getCurrentInstance().addMessage(null, msgs);
            } else {
                String msg = "Error al cargar foto";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Carga", msg);
                FacesContext.getCurrentInstance().addMessage(null, message);

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private boolean copyFile(String fileName, InputStream in) {
        boolean flag;
        try {
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(rutaFoto + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
            updateRutaFotoBaseDatos();
            System.out.println("New file created!");
            flag = true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            flag = false;
        }
        return flag;
    }

    private void updateRutaFotoBaseDatos() {
        this.personaSeleccionada.setPeFoto(rutaFoto + nombreFoto);
        String msg;
        if (this.personaDao.update(this.personaSeleccionada)) {
            msg = "Se modificó correctamente la informacion";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualización", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al modificar informacion";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Actualización", msg);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
}
