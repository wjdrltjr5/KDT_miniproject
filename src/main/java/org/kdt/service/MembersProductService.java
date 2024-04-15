package org.kdt.service;

import org.kdt.dto.MembersProductDTO;

import java.util.List;

public interface MembersProductService {

    List<MembersProductDTO> findByStatusHold();
}
