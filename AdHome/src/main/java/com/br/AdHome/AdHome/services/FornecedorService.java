package com.br.AdHome.AdHome.services;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.br.AdHome.AdHome.models.Fornecedor;
import com.br.AdHome.AdHome.repositories.FornecedorRepository;

/*
* Conhecida como camada Beans
* Service: realiza opeações de negócio.
* Um método da camada Service deve ter um SIGNIFICADO relacionado ao negócio,
* executar várias operações.Ex; requisitar[verificar estoque, salvar pedido, baixar estque,
* enviar e-mail]
*/

@Service
public class FornecedorService {
	
	final FornecedorRepository fornecedorRepository;
	
	public FornecedorService(FornecedorRepository fornecedorRepository) {
		this.fornecedorRepository = fornecedorRepository;
	}
	@Transactional
	public Fornecedor saveFornecedor(Fornecedor fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}
	public Page<Fornecedor> findAll(Pageable pageable){
		return fornecedorRepository.findAll(pageable);
	}
	public Optional<Fornecedor> findById(Long id){
		return fornecedorRepository.findById(id);
	}
	@Transactional
	public void deleteFornecedor(Fornecedor fornecedor) {
		fornecedorRepository.delete(fornecedor);
	}
}
