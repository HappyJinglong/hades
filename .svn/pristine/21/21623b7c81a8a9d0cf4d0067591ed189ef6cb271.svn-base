package com.flyrish.hades.service.ext;

import java.util.Date;
import java.util.List;

import com.flyrish.hades.dto.AppraisalxieDto;
import com.flyrish.hades.dto.CzGreedLengthDto;
import com.flyrish.hades.dto.FistGzTopicDto;
import com.flyrish.hades.dto.GreedDto;
import com.flyrish.hades.dto.PartInfoXieDto;
import com.flyrish.hades.dto.SchoolDto;
import com.flyrish.hades.dto.StudentxieDto;
import com.flyrish.hades.dto.UserDto;

public interface IareaStatExt {
	/**
	 * 
	 * @param likeSchoolName
	 * @param levelCode
	 * @param discode
	 * @return
	 */
	List findAllSchoolid(String likeSchoolName, String levelCode, String discode);

	SchoolDto findClassApper(Integer cim, Integer intyear, String levelCode,
			String discode, String termid);

	/**
	 * 
	 * @param cim
	 * @param intyear
	 * @param levelCode
	 * @param discode
	 * @param termid
	 * @return
	 */
	SchoolDto czFindClassApper(Integer cim, Integer intyear, String levelCode,
			String discode, String termid);
   /**
    * 
    * @param cim
    * @param levelCode
    * @param discode
    * @param topic
    * @param greed
    * @param graduateyear
    * @param intyear
    * @return
    */

	FistGzTopicDto gzFindTopicApper(Integer cim, String levelCode, String discode,
			String topic, String greed, Integer graduateyear, Integer intyear);

String findSchoolName(Integer cim, String discode);
/**
 * 
 * @param schoolName
 * @param levelCode
 * @param discode
 * @return
 */
List<CzGreedLengthDto> findCzAllSchoolid(String schoolName, String levelCode,
		String discode);
/**
 * 
 * @param cim
 * @param levelCode
 * @param discode
 * @param topic
 * @param greed
 * @param graduateyear
 * @param intyear
 * @param greedLength
 * @return
 */
FistGzTopicDto czFindTopicApper(Integer cim, String levelCode, String discode,
		String topic, String greed, Integer graduateyear, Integer intyear,
		Integer greedLength);
	
	

	
}
