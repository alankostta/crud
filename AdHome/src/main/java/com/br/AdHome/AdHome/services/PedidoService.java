package com.br.AdHome.AdHome.services;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.br.AdHome.AdHome.models.Pedido;
import com.br.AdHome.AdHome.repositories.PedidoRepository;

/*
* Conhecida como camada Beans
* Service: realiza opeações de negócio.
* Um método da camada Service deve ter um SIGNIFICADO relacionado ao negócio,
* executar várias operações.Ex; requisitar[verificar estoque, salvar pedido, baixar estque,
* enviar e-mail]
*/

@Service
public class PedidoService {
	final PedidoRepository pedidoRepository;
	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}
	@Transactional
	public Pedido savePedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	public Page<Pedido> findAll(Pageable pageable){
		return pedidoRepository.findAll(pageable);
	}
	public Optional<Pedido> findById(Long id){
		return pedidoRepository.findById(id);
	}
	@Transactional
	public void deletePedido(Pedido pedido) {
		pedidoRepository.delete(pedido);
	}
}
