package com.flyrish.hades.service.ext;

import java.util.List;

import com.flyrish.hades.dto.AppraisalTypeDto;

public interface SeniorExt {
	
	public List<AppraisalTypeDto> queryAppraisalTypeDto();

	public List<AppraisalTypeDto> queryAppraisalList();
	
}
