package com.scing.erp.sistema.miscelaneos.localizacion.distrito;

import java.util.List;
import java.util.stream.Collectors;
import com.scing.erp.sistema.miscelaneos.localizacion.provincia.Provincia;
import com.scing.erp.sistema.miscelaneos.localizacion.provincia.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistritoService {

	private DistritoRepository distritoRepository;
	private ProvinciaRepository provinciaRepository;

	@Autowired
	public DistritoService(ProvinciaRepository provinciaRepository, DistritoRepository distritoRepository) {
		this.distritoRepository = distritoRepository;
		this.provinciaRepository = provinciaRepository;
	}

	public List<DistritoDTO> listDistrito(Long idprovincia) {

		if (provinciaRepository.findById(idprovincia).isPresent()) {

			Provincia provincia = provinciaRepository.findById(idprovincia).orElse(null);

			List<Distrito> listDistrito = this.distritoRepository.findAllByProvinciaOrderByEliminadoAsc(provincia);

			List<DistritoDTO> listDistritoDTO = listDistrito.stream().map(distrito -> {

				DistritoDTO DistritoDTO = new DistritoDTO();
				DistritoDTO.setIddistrito(distrito.getIddistrito());
				DistritoDTO.setNombre(distrito.getNombre());
				DistritoDTO.setEliminado(distrito.isEliminado());

				return DistritoDTO;
			}).collect(Collectors.toList());

			return listDistritoDTO;
		} else {
			return null;
		}
	}
}
