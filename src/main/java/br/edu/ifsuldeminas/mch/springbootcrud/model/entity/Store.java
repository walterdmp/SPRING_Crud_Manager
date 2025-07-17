package br.edu.ifsuldeminas.mch.springbootcrud.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "O nome da loja não pode ser vazio.")
    private String name;

    @NotBlank(message = "O CNPJ não pode ser vazio.")
    private String cnpj;

    @NotBlank(message = "O telefone não pode ser vazio.")
    private String phone;
    
    @NotBlank(message = "O endereço não pode ser vazio.")
    private String address;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Boot> boots;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Boot> getBoots() {
        return boots;
    }

    public void setBoots(List<Boot> boots) {
        this.boots = boots;
    }
}