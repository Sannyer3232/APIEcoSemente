package br.edu.ifam.ecosemente.ecosemente.dto;


import br.edu.ifam.ecosemente.ecosemente.model.Comprador;

public class CompradorOutputDTO {

    private long id;
    private String nome;
    private String cpfCnpj;
    private String telefone;
    private String email;

    public CompradorOutputDTO() {
    }

    public CompradorOutputDTO(Comprador comprador) {
        this.id = comprador.getId();
        this.nome = comprador.getNome();
        this.cpfCnpj = comprador.getCpfCnpj();
        this.telefone = comprador.getTelefone();
        this.email = comprador.getEmail();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
