package com.AnotherFall.Arasaka.Registry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CidadaoService {

	@Autowired
	private CidadaoRepository cidadaoRepository;

	public Cidadao salvar(Cidadao cidadao) {
		if (cidadao.getId() == null) {
			String hwid = HwidGenerator.gerar(cidadao.getModeloSoc(), cidadao.getCriadoraSoc());
			cidadao.setHwidSoc(hwid);
		}
		return cidadaoRepository.save(cidadao);
	}
	
	public Optional<Cidadao> buscarPorNomeOuHwid(String query) {
	    return cidadaoRepository.findByNomeIgnoreCaseOrHwidSoc(query, query);
	}


	public Optional<Cidadao> findById(Long id) {
		return cidadaoRepository.findById(id);
	}

	public void deleteById(Long id) {
		cidadaoRepository.deleteById(id);
	}
	
	public List<Cidadao> findAll() {
        return cidadaoRepository.findAll();
    }

	public List<Cidadao> buscarPorClasseOuNivel(Classe classe, NivelAcesso nivel) {
		return cidadaoRepository.findByClasseOrNivelAcesso(classe, nivel);
	}

	public List<Cidadao> buscarPorClasse(Classe classe) {
		return cidadaoRepository.findByClasse(classe);
	}

	public List<Cidadao> buscarPorNivel(NivelAcesso nivel) {
		return cidadaoRepository.findByNivelAcesso(nivel);
	}
}