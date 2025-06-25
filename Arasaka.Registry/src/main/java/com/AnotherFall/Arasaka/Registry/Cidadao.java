package com.AnotherFall.Arasaka.Registry;
import jakarta.persistence.*;

@Entity
public class Cidadao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;
    private String role; 
    private String password; // Armazenará o hash da senha

    @Column(unique = true, nullable = false, length = 25)
    private String hwidSoc;

    private String modeloSoc;

    @Enumerated(EnumType.STRING)
    private CriadoraSOC criadoraSoc;

    @Enumerated(EnumType.STRING)
    private Classe classe;

    @Enumerated(EnumType.STRING)
    private NivelAcesso nivelAcesso;

    private String caminhoFoto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getHwidSoc() {
		return hwidSoc;
	}

	public void setHwidSoc(String hwidSoc) {
		this.hwidSoc = hwidSoc;
	}

	public String getModeloSoc() {
		return modeloSoc;
	}

	public void setModeloSoc(String modeloSoc) {
		this.modeloSoc = modeloSoc;
	}

	public CriadoraSOC getCriadoraSoc() {
		return criadoraSoc;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCriadoraSoc(CriadoraSOC criadoraSoc) {
		this.criadoraSoc = criadoraSoc;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public NivelAcesso getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(NivelAcesso nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public String getCaminhoFoto() {
		return caminhoFoto;
	}

	public void setCaminhoFoto(String caminhoFoto) {
		this.caminhoFoto = caminhoFoto;
	}

    
}