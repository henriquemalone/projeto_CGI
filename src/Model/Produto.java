package Model;

import Controller.CtrProduto;


public class Produto {
    private int id;
    private String nome;
    private String lote;
    private double valor;
    private double qtd;
    
    CtrProduto ctrproduto = new CtrProduto();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean addProduto(Produto produto){
        if(ctrproduto.addProduto(produto) == true){
            return true;
        } 
     
        return false;
    }
    
    public boolean deletaProduto(int id){
        if(ctrproduto.deletaProduto(id) == true){
            return true;
        }
        
        return false;
    }
    
    public boolean editaProduto(Produto produto){
        if(ctrproduto.editaProduto(produto) == true){
            return true;
        } 
     
        return false;
    }
}
