package com.kuehnenagel.egcitylist.mapper.city;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.kuehnenagel.egcitylist.dto.BaseDTO;
import com.kuehnenagel.egcitylist.model.BaseModel;
import com.kuehnenagel.egcitylist.payload.response.PageableResponse;

public abstract class GenericMapper<DOMAIN extends BaseModel, DTO extends BaseDTO> {
	/**
	 * Transform domain object into DTO
	 * Mappings are used to 'bind' entity fields to DTO fields (for the mapper's implementation).
	 * @param domain
	 * @return DTO
	 */
	public abstract DTO domainToDto(DOMAIN domain) throws Exception;

	/**
	 * Transform dto to domain
	 * @param domain
	 * @param dto
	 * @return
	 */
	public abstract void dtoToDomain(DTO dto, DOMAIN domain) throws Exception;

	public List<DTO> domainToDTOList(Iterable<? extends DOMAIN> domainList) throws Exception {
		if (domainList == null) {
			return new ArrayList<DTO>();
		}
		List<DTO> dtoList = new ArrayList<DTO>();

		for(DOMAIN domain : domainList){
			dtoList.add(domainToDto(domain));
		}
		return dtoList;
	}

	protected void setCommanDTOFields(DTO dto, DOMAIN domain) {
		dto.setIsDeleted(domain.getIsDeleted());
		dto.setVersion(domain.getVersion());
	}

	protected void setCommanDomainFields(DTO dto, DOMAIN domain) {
		domain.setIsDeleted(dto.getIsDeleted());
		domain.setVersion(dto.getVersion());
	}

	public Map<String, Object> pageToPageableResponse(Page<DOMAIN> content) throws Exception {

		return new PageableResponse().parse(domainToDTOList(content.getContent()), content);

	}
}
