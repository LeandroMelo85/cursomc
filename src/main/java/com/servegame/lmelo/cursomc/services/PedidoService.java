package com.servegame.lmelo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servegame.lmelo.cursomc.domain.Pedido;
import com.servegame.lmelo.cursomc.repositories.PedidoRepository;
import com.servegame.lmelo.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
