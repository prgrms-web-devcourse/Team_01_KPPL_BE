package prgrms.project.stuti.domain.studygroup.repository.studygroup;

import java.util.List;
import java.util.Optional;

import prgrms.project.stuti.domain.studygroup.model.StudyGroup;
import prgrms.project.stuti.domain.studygroup.repository.dto.StudyGroupQueryDto;
import prgrms.project.stuti.domain.studygroup.service.dto.StudyGroupDto;

public interface CustomStudyGroupRepository {

	Optional<StudyGroup> findStudyGroupById(Long studyGroupId);

	List<StudyGroupQueryDto.StudyGroupDetailDto> findStudyGroupDetailById(Long studyGroupId);

	StudyGroupQueryDto.StudyGroupsDto findAllWithCursorPaginationByConditions(
		StudyGroupDto.FindCondition conditionDto);

	StudyGroupQueryDto.StudyGroupsDto findMembersAllWithCursorPaginationByConditions(
		StudyGroupDto.FindCondition conditionDto);
}
