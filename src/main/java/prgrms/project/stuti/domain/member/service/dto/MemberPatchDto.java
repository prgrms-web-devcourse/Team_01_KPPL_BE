package prgrms.project.stuti.domain.member.service.dto;

import lombok.Builder;
import prgrms.project.stuti.domain.member.model.Career;
import prgrms.project.stuti.domain.member.model.Field;
import prgrms.project.stuti.domain.member.model.Mbti;

public record MemberPatchDto(
	String nickname,
	Field field,
	Career career,
	Mbti MBTI,
	String githubUrl,
	String blogUrl
) {
	@Builder
	public MemberPatchDto {
	}
}
