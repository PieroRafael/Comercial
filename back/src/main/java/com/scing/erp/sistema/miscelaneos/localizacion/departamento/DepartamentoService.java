package com.scing.erp.sistema.miscelaneos.localizacion.departamento;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.scing.erp.sistema.miscelaneos.localizacion.pais.Pais;
import com.scing.erp.sistema.miscelaneos.localizacion.pais.PaisRepository;

@Service
public class DepartamentoService {

	private DepartamentoRepository departamentoRepository;
	private PaisRepository paisRepository;

	@Autowired
	public DepartamentoService(DepartamentoRepository departamentoRepository, PaisRepository paisRepository) {
		this.departamentoRepository = departamentoRepository;
		this.paisRepository = paisRepository;
	}

	public List<DepartamentoDTO> getAll() {

		List<Departamento> departamentos = this.departamentoRepository.findAllOrdenado();

		List<DepartamentoDTO> result = departamentos.stream().map(departamento -> {
			DepartamentoDTO departamentoDTO = new DepartamentoDTO();
			departamentoDTO.setIddepartamento(departamento.getIddepartamento());
			departamentoDTO.setNombre(departamento.getNombre());
			departamentoDTO.setEliminado(departamento.isEliminado());

			return departamentoDTO;
		}).collect(Collectors.toList());

		return result;
	}

	public List<DepartamentoDTO> listDepartamento(Long idpais) {

		if (paisRepository.findById(idpais).isPresent()) {

			Pais pais = paisRepository.findById(idpais).orElse(null);

			List<Departamento> departamentos = this.departamentoRepository.findAllByPaisOrderByEliminadoAsc(pais);

			List<DepartamentoDTO> result = departamentos.stream().map(departamento -> {

				DepartamentoDTO departamentoDTO = new DepartamentoDTO();
				departamentoDTO.setIddepartamento(departamento.getIddepartamento());
				departamentoDTO.setNombre(departamento.getNombre());
				departamentoDTO.setEliminado(departamento.isEliminado());

				return departamentoDTO;
			}).collect(Collectors.toList());

			return result;
		} else {
			return null;
		}
	}
}
