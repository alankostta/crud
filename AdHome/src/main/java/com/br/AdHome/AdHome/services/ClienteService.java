package com.br.AdHome.AdHome.services;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.br.AdHome.AdHome.models.Cliente;
import com.br.AdHome.AdHome.repositories.ClienteRepository;

/* Conhecida como camada Beans
 * Service: realiza opeações de negócio.
 * Um método da camada Service deve ter um SIGNIFICADO relacionado ao negócio,
 * executar várias operações.Ex; requisitar[verificar estoque, salvar pedido, baixar estque,
 * enviar e-mail]
 */
@Service
public class ClienteService {
	
	final ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
		
	}
	@Transactional
	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}
	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}
	@Transactional
	public void deleteCliente(Cliente cliente) {
		clienteRepository.delete(cliente);
	}
	
}
