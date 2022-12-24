package com.scing.erp.sistema.miscelaneos.banco;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.scing.erp.sistema.entity.ResponseMensaje;
import com.scing.erp.sistema.usuario.UserContextHolder;

@Service
public class BancoService {

	private BancoRepository bancoRepository;
	private ModelMapper modelMapper;

	@Autowired
	public BancoService(BancoRepository bancoRepository, ModelMapper modelMapper) {
		this.bancoRepository = bancoRepository;
		this.modelMapper = modelMapper;
	}

	public List<BancoDTO> listBanco() {

		List<Banco> listbanco = this.bancoRepository.findAllByOrderByEliminadoAsc();

		List<BancoDTO> listbancoDTO = listbanco.stream().map(banco -> {

			BancoDTO bancoDTO = new BancoDTO();
			bancoDTO.setIdbanco(banco.getIdbanco());
			bancoDTO.setNombre(banco.getNombre());
			bancoDTO.setEliminado(banco.isEliminado());

			return bancoDTO;
		}).collect(Collectors.toList());

		return listbancoDTO;
	}

	@Transactional
	public ResponseMensaje createBanco(BancoDTO bancoDTO) {

		if (!bancoRepository.findByNombre(bancoDTO.getNombre()).isPresent()) {

			Banco banco = new Banco();
			banco.setNombre(bancoDTO.getNombre());
			banco.setEliminado(false);

			banco
					.setUcreate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

			bancoRepository.save(banco);

			return new ResponseMensaje(200, "banco registrado");
		} else {
			return new ResponseMensaje(409,
					"banco con nombre, " + bancoDTO.getNombre() + " ya existe");
		}
	}

	public BancoDTO getBancoByIdbanco(Long idbanco) {
		Banco banco = bancoRepository.findById(idbanco).orElse(null);
		return modelMapper.map(banco, BancoDTO.class);
	}

	@Transactional
	public ResponseMensaje updateBanco(BancoDTO bancoDTO) {

		if (bancoRepository.findById(bancoDTO.getIdbanco()).isPresent()) {

			Banco banco = bancoRepository
					.findById(bancoDTO.getIdbanco()).orElse(null);

			if (banco.getNombre().equals(bancoDTO.getNombre())
					|| !bancoRepository.findByNombre(bancoDTO.getNombre()).isPresent()) {

				banco.setNombre(bancoDTO.getNombre());

				banco
						.setUupdate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

				bancoRepository.save(banco);

				return new ResponseMensaje(200, "banco actualizado");
			} else {
				return new ResponseMensaje(409,
						"banco con nombre, " + bancoDTO.getNombre() + " ya existe");
			}
		} else {
			return new ResponseMensaje(404,
					"banco con nombre, " + bancoDTO.getNombre() + " no encontrado");
		}
	}

	@Transactional
	public ResponseMensaje deleteBanco(Long idbanco) {

		if (bancoRepository.findById(idbanco).isPresent()) {

			Banco banco = bancoRepository.findById(idbanco).orElse(null);
			String msj = "banco " + banco.getNombre() + ", ha sido habilitado";

			if (banco.isEliminado()) {
				banco.setEliminado(false);
			} else {
				banco.setEliminado(true);
				msj = "banco " + banco.getNombre() + ", ha sido inhabilitado";
			}

			banco
					.setUupdate(UserContextHolder.getUser().getNombres() + UserContextHolder.getUser().getApellidos());

			bancoRepository.save(banco);

			return new ResponseMensaje(200, msj);
		} else {
			return new ResponseMensaje(404, "banco no encontrado");
		}
	}

}
