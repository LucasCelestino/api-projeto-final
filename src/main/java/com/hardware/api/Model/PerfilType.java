package com.hardware.api.Model;

public enum PerfilType
{
    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private Integer cod;
    private String descricao;

    private PerfilType(Integer cod, String descricao)
    {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() 
    {
        return this.cod;
    }

    public String getDescricao() 
    {
        return this.descricao;
    }

    public static PerfilType toEnum(Integer cod)
    {
        if (cod == null) return null;
        
        for (PerfilType x : PerfilType.values())
        {
            if (cod.equals(x.getCod())) return x;
        }

        throw new IllegalArgumentException("Código inválido: " + cod);
    }
}
