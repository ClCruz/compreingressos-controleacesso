/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compreingressos.controleacesso;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Intuiti 04
 */
@Embeddable
public class UsuarioContratantePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "contratante")
    private int contratante;

    public UsuarioContratantePK() {
    }

    public UsuarioContratantePK(int usuario, int contratante) {
        this.usuario = usuario;
        this.contratante = contratante;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getContratante() {
        return contratante;
    }

    public void setContratante(int contratante) {
        this.contratante = contratante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) usuario;
        hash += (int) contratante;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioContratantePK)) {
            return false;
        }
        UsuarioContratantePK other = (UsuarioContratantePK) object;
        if (this.usuario != other.usuario) {
            return false;
        }
        if (this.contratante != other.contratante) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.compreingressos.controleacesso.UsuarioContratantePK[ usuario=" + usuario + ", contratante=" + contratante + " ]";
    }
    
}
