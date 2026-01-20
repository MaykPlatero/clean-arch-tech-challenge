package br.com.fiap.clean_arch.domain.entities;

public enum EProfile {
    CLIENT("Cliente"),
    OWNER("Proprietário"),
    ADMIN("Administrador");

    private String desciption;

    EProfile(String desciption) {
        this.desciption = desciption;
    }

    public String getDesciption() {
        return desciption;
    }

}
