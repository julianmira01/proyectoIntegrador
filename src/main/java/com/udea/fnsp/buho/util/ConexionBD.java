/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.fnsp.buho.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ConexionBD {

    private Connection conexion;

    private String error = null;

    public ConexionBD(String user, String pass, String bd, String host) throws ClassNotFoundException {

        try {

            Class.forName("com.mysql.jdbc.Driver");

            this.conexion
                    = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + bd,
                            user, pass);

        } catch (SQLException ex) {

            this.error = ex.getMessage();

        }

    }

    public String getError() {

        return this.error;

    }

    public Connection getConexion() {

        return this.conexion;

    }

    public void Cerrar() {

        try {

            this.conexion.close();

        } catch (SQLException ex) {

            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null,
                    ex);

        }

    }

}
