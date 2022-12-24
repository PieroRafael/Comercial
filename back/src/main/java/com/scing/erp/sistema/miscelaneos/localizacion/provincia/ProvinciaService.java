package com.scing.erp.sistema.miscelaneos.localizacion.provincia;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.scing.erp.sistema.miscelaneos.localizacion.departamento.Departamento;
import com.scing.erp.sistema.miscelaneos.localizacion.departamento.DepartamentoRepository;

@Service
public class ProvinciaService {

	private ProvinciaRepository provinciaRepository;
	private DepartamentoRepository departamentoRepository;

	@Autowired
	public ProvinciaService(ProvinciaRepository provinciaRepository, DepartamentoRepository departamentoRepository) {
		this.provinciaRepository = provinciaRepository;
		this.departamentoRepository = departamentoRepository;
	}

	public List<ProvinciaDTO> listProvincia(Long iddepartamento) {

		if (departamentoRepository.findById(iddepartamento).isPresent()) {

			Departamento departamento = departamentoRepository.findById(iddepartamento).orElse(null);

			List<Provincia> provincias = this.provinciaRepository.findAllByDepartamentoOrderByEliminadoAsc(departamento);

			List<ProvinciaDTO> result = provincias.stream().map(provincia -> {

				ProvinciaDTO provinciaDTO = new ProvinciaDTO();
				provinciaDTO.setIdprovincia(provincia.getIdprovincia());
				provinciaDTO.setNombre(provincia.getNombre());
				provinciaDTO.setEliminado(provincia.isEliminado());

				return provinciaDTO;
			}).collect(Collectors.toList());

			return result;
		} else {
			return null;
		}
	}
}
