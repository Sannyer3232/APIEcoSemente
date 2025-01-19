package br.edu.ifam.ecosemente.ecosemente.dto;

import br.edu.ifam.ecosemente.ecosemente.model.Comprador;

public class CompradorInputDTO {

    private String nome;
    private String cpfCnpj;
    private String telefone;
    private String email;

    public CompradorInputDTO() {
    }

    public CompradorInputDTO(String nome, String cpfCnpj, String telefone, String email) {
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
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

    public Comprador build(){
        Comprador comprador = new Comprador();

        comprador.setNome(this.nome);
        comprador.setCpfCnpj(this.cpfCnpj);
        comprador.setTelefone(this.telefone);
        comprador.setEmail(this.email);

        return comprador;
    }
}
