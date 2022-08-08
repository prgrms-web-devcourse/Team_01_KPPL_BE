package prgrms.project.stuti.domain.studygroup.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import prgrms.project.stuti.domain.studygroup.service.StudyGroupMemberService;
import prgrms.project.stuti.domain.studygroup.service.dto.StudyGroupMemberDto;
import prgrms.project.stuti.domain.studygroup.service.response.StudyGroupMemberIdResponse;
import prgrms.project.stuti.domain.studygroup.service.response.StudyGroupMembersResponse;

@RestController
@RequestMapping("/api/v1/study-groups/{studyGroupId}/members")
@RequiredArgsConstructor
public class StudyGroupMemberRestController {

	private final StudyGroupMemberService studyGroupMemberService;

	@PostMapping
	public ResponseEntity<StudyGroupMemberIdResponse> applyForJoinStudyGroup(
		@AuthenticationPrincipal Long memberId, @PathVariable Long studyGroupId
	) {
		StudyGroupMemberDto.CreateDto createDto =
			StudyGroupMemberMapper.toStudyGroupMemberCreateDto(memberId, studyGroupId);
		StudyGroupMemberIdResponse idResponse = studyGroupMemberService.applyForJoinStudyGroup(createDto);

		return ResponseEntity.ok(idResponse);
	}

	@GetMapping
	public ResponseEntity<StudyGroupMembersResponse> getStudyGroupMembers(
		@AuthenticationPrincipal Long memberId, @PathVariable Long studyGroupId
	) {
		StudyGroupMemberDto.ReadDto readDto = StudyGroupMemberMapper.toStudyGroupMemberReadDto(memberId, studyGroupId);
		StudyGroupMembersResponse studyGroupMembersResponse = studyGroupMemberService.getStudyGroupMembers(readDto);

		return ResponseEntity.ok(studyGroupMembersResponse);
	}

	@PatchMapping("/{studyGroupMemberId}")
	public ResponseEntity<StudyGroupMemberIdResponse> acceptRequestForJoin(
		@AuthenticationPrincipal Long memberId, @PathVariable Long studyGroupId, @PathVariable Long studyGroupMemberId
	) {
		StudyGroupMemberDto.UpdateDto updateDto =
			StudyGroupMemberMapper.toStudyGroupMemberUpdateDto(memberId, studyGroupId, studyGroupMemberId);
		StudyGroupMemberIdResponse idResponse = studyGroupMemberService.acceptRequestForJoin(updateDto);

		return ResponseEntity.ok(idResponse);
	}

	@DeleteMapping(value = "/{studyGroupMemberId}")
	public ResponseEntity<Void> deleteStudyGroupMember(
		@AuthenticationPrincipal Long memberId, @PathVariable Long studyGroupId, @PathVariable Long studyGroupMemberId
	) {
		StudyGroupMemberDto.DeleteDto deleteDto =
			StudyGroupMemberMapper.toStudyGroupMemberDeleteDto(memberId, studyGroupId, studyGroupMemberId);
		studyGroupMemberService.deleteStudyGroupMember(deleteDto);

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build();
	}
}
