package com.HTT.company.service.commonService;

import java.util.List;

public interface CRUDService<TypeOf, IdKey> {
	List<TypeOf> getAll();

	TypeOf getById(IdKey id);

	Integer create(TypeOf entity);

	Integer delete(IdKey username);

	Integer update(TypeOf entity);

}
