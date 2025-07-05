package com.AnotherFall.Arasaka.Registry; // Use o seu nome de pacote

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface CidadaoRepository extends JpaRepository<Cidadao, Long> {

    List<Cidadao> findByClasseOrNivelAcesso(Classe classe, NivelAcesso nivelAcesso);
    List<Cidadao> findByClasse(Classe classe);
    List<Cidadao> findByNivelAcesso(NivelAcesso nivelAcesso);
    Optional<Cidadao> findByNomeIgnoreCaseOrHwidSoc(String nome, String hwidSoc);

}