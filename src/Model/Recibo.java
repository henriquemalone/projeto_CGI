package Model;

import Controller.CtrRecibo;
import java.sql.Date;

public class Recibo {
    private String nome;
    private double qtd;
    private String lote;
    private double preco;
    private double total;
    private String contato;
    private String telefone;
    private String email;
    private String cliente;
    private int idrecibo;
    private Date data;
    private String previsao;
    private String status;
    CtrRecibo crecibo = new CtrRecibo();

    public String getPrevisao() {
        return previsao;
    }

    public void setPrevisao(String previsao) {
        this.previsao = previsao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public int getIdrecibo() {
        return idrecibo;
    }

    public void setIdrecibo(int idrecibo) {
        this.idrecibo = idrecibo;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getQtd() {
        return qtd;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double preco, double qtd) {
        this.total = preco*qtd;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    public boolean addRecibo(Recibo recibo){
        if(crecibo.addRecibo(recibo) == true){

            return true;
        } 
     
        return false;
    }
    
}
