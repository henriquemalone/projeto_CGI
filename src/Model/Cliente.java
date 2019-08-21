/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

//import Controller.CtrCliente;
import Controller.CtrCliente;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author hen_r
 */
public class Cliente {
    private int id;
    private String nomeC;
    private String contato;
    private String telefone;
    private String celular;
    private String cnpj;
    private String email;
    private String endereco;
    private String numero;
    private String cep;
    private String bairro;
    private String complemento;
    private String cidade;
    private String estado;
    
    CtrCliente ccliente = new CtrCliente();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeC() {
        return nomeC;
    }

    public void setNomeC(String nomeC) {
        this.nomeC = nomeC;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public boolean addCliente(Cliente cliente){
        if(ccliente.addCliente(cliente) == true){
            return true;
        } 
     
        return false;
    }
    
    public boolean editaCliente(Cliente cliente){
        if(ccliente.editaCliente(cliente) == true){
            return true;
        } 
     
        return false;
    }
    
    public boolean deletaCliente(int id){
        if(ccliente.deletaCliente(id) == true){
            return true;
        }
        
        return false;
    }
}
