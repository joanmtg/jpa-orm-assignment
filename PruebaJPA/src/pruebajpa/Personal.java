/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebajpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author invitado
 */
@Entity
@Table(name = "personal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM Personal p")
    , @NamedQuery(name = "Personal.findByIdentificacionPersonal", query = "SELECT p FROM Personal p WHERE p.identificacionPersonal = :identificacionPersonal")
    , @NamedQuery(name = "Personal.findByNombre", query = "SELECT p FROM Personal p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Personal.findByApellido", query = "SELECT p FROM Personal p WHERE p.apellido = :apellido")})
public class Personal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "identificacion_personal")
    private String identificacionPersonal;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @JoinColumn(name = "tp_documento", referencedColumnName = "tp_id_documento")
    @ManyToOne
    private TipoDocumento tpDocumento;

    public Personal() {
    }

    public Personal(String identificacionPersonal) {
        this.identificacionPersonal = identificacionPersonal;
    }

    public String getIdentificacionPersonal() {
        return identificacionPersonal;
    }

    public void setIdentificacionPersonal(String identificacionPersonal) {
        this.identificacionPersonal = identificacionPersonal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public TipoDocumento getTpDocumento() {
        return tpDocumento;
    }

    public void setTpDocumento(TipoDocumento tpDocumento) {
        this.tpDocumento = tpDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identificacionPersonal != null ? identificacionPersonal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personal)) {
            return false;
        }
        Personal other = (Personal) object;
        if ((this.identificacionPersonal == null && other.identificacionPersonal != null) || (this.identificacionPersonal != null && !this.identificacionPersonal.equals(other.identificacionPersonal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pruebajpa.Personal[ identificacionPersonal=" + identificacionPersonal + " ]";
    }
    
}
