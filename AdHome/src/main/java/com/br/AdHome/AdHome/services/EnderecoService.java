package com.br.AdHome.AdHome.services;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.br.AdHome.AdHome.models.Endereco;
import com.br.AdHome.AdHome.repositories.EnderecoRepository;

/* Conhecida como camada Beans
 * Service: realiza opeações de negócio.
 * Um método da camada Service deve ter um SIGNIFICADO relacionado ao negócio,
 * executar várias operações.Ex; requisitar[verificar estoque, salvar pedido, baixar estque,
 * enviar e-mail]
 */
@Service
public class EnderecoService {
	final EnderecoRepository enderecoRepository;
	
	public EnderecoService(EnderecoRepository enderecoRepository) {
		this.enderecoRepository = enderecoRepository;
	}
	@Transactional
	public Endereco saveEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	public Page<Endereco> findAllEndereco(Pageable pageable){
		return enderecoRepository.findAll(pageable);
	}
	public Optional<Endereco> findByIdEndereco(Long id){
		return enderecoRepository.findById(id);
	}
	@Transactional
	public void deleteEndereco(Endereco endereco) {
		enderecoRepository.delete(endereco);
	}
}
